package main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import main.entities.Booking;
import main.entities.Friend_List;
import main.entities.Friend_List.Status_Code;
import main.entities.Gym;
import main.entities.Gym_Pass;
import main.entities.Timeslot;
import main.entities.TimeslotResult;
import main.entities.User;
import main.entities.UserResult;
import main.repository.BookingRepo;
import main.repository.FriendListRepo;
import main.repository.GymPassRepo;
import main.repository.GymRepo;
import main.repository.TimeslotRepo;
import main.repository.UserRepo;

@Service
public class UserService {

//	private final int currUser = 1001;

	private LoggedUserService logged;
	private final UserRepo userRepo;
	private final GymPassRepo gymPassRepo;
	private final TimeslotRepo timeslotRepo;
	private final BookingRepo bookingRepo;
	private final GymRepo gymRepo;
	private final FriendListRepo friendListRepo;	
	private String msg = "";

	public UserService(LoggedUserService logged, UserRepo userRepo, GymPassRepo gymPassRepo, TimeslotRepo timeslotRepo,
			BookingRepo bookingRepo, GymRepo gymRepo, FriendListRepo friendListRepo) {

		this.logged = logged;
		this.userRepo = userRepo;
		this.gymPassRepo = gymPassRepo;
		this.timeslotRepo = timeslotRepo;
		this.bookingRepo = bookingRepo;
		this.gymRepo = gymRepo;
		this.friendListRepo = friendListRepo;
	}

	public void login(int user_id) {

		logged.setCurrUser(user_id);
		return;
	}

	public void logout() {
		logged.setCurrUser(-1);
		return;
	}

	public Optional<User> getLoginInfo() {

		if (userExist(logged.getCurrUser())) {
			return userRepo.findById(logged.getCurrUser());
		} else {
			return null;
		}

	}

	public Boolean userExist(int user_id) {

		return userRepo.existsById(user_id);
	}

	public User findUser(int user_id) {

		return userRepo.getById(user_id);
	}

	// for test
//	public User getUser(int user_id) {
//
//		Boolean flag = userRepo.existsById(user_id);
//
//		System.out.println("Flag is: " + user_id + flag);
//
//		System.out.println("before if");
//		if (flag) {
//			System.out.println("inside if");
//			User user = userRepo.getById(user_id);
//			return user;
//
//		} else {
//
//			return null;
//		}
//
//	}

	public UserResult searchUser(int user_id) {
		// Prevent currUser self search
		User user = null;
		if (!userExist(logged.getCurrUser()) || !userExist(user_id) || user_id == logged.getCurrUser()) {
			return null;
		} else {
			user = userRepo.getById(user_id);
		}

		Status_Code code = Status_Code.N;

		Friend_List temp = findFriendList(logged.getCurrUser(), user_id);

		if (Objects.nonNull(temp)) {
			code = temp.getStatus_code();

			if (code == Status_Code.A) {

			} else if ((logged.getCurrUser() < user_id && code == Status_Code.P2)
					|| (user_id < logged.getCurrUser() && code == Status_Code.P1)) {
				code = Status_Code.R;
			} else {
				code = Status_Code.F;
			}
		}
//			return new UserResult(user_id, msg, null, code);
		return new UserResult(user.getUser_id(), user.getName(), user.getGender(), code);

//			// Old code without findFriendList helper
//			for (Friend_List item : user.getFriendList1()) {
//				if (currUser == item.getFriend_id()) {
//					code = item.getStatus_code();
//				}
//			}
//
//			for (Friend_List item : user.getFriendList2()) {
//				if (currUser == item.getPerson_id()) {
//					code = item.getStatus_code();
//				}
//			}

	}

	@Transactional
	public Boolean friendRequest(int user_id) {

//		User user1 = userRepo.findUser(currUser);
//		User user2 = userRepo.findUser(user_id);

		if (user_id == logged.getCurrUser()) {
			return false;
		}

		Friend_List friendList = new Friend_List();

		// To ensure that the Friend_List table constraints are met (user1_id <
		// user2_id)
		if (logged.getCurrUser() < user_id) {
			friendList.setPerson_id(logged.getCurrUser());
			friendList.setFriend_id(user_id);
			friendList.setStatus_code(Status_Code.P2);
		} else {
			friendList.setPerson_id(user_id);
			friendList.setFriend_id(logged.getCurrUser());
			friendList.setStatus_code(Status_Code.P1);
		}
		friendListRepo.save(friendList);
		return true;

	}

	// may consider delete friend next time
	@Transactional
	public void updateFriend(int user_id, Status_Code code) {

		if (user_id == logged.getCurrUser()) {
			return;
		}
		Friend_List temp = findFriendList(logged.getCurrUser(), user_id);
//		System.out.println(temp.getPerson_id() + " " + temp.getFriend_id()+ " " + temp.getStatus_code());
		friendListRepo.updateFriend(temp.getPerson_id(), temp.getFriend_id(), code);
	}

	// Currently set to a fixed amount
	public String updateCredit(int credit) {

		User user = userRepo.findUser(logged.getCurrUser());
		int newCredit = 0;

		// might not happen
		if (Objects.isNull(user)) {
			msg = "Error, unable to top-up credit";

		} else if ((credit < 0) && (user.getCredit() < credit)) {

			msg = "Insufficent credits";

		} else {
			newCredit = user.getCredit() + credit;
			userRepo.updateCredit(logged.getCurrUser(), newCredit);
			msg = "Credit updated sucessfully";
		}

		return msg;
	}

	public void deductPass(Gym_Pass pass) {

//		List<Gym_Pass> list = gymPassRepo.findPass(user.getUser_id(), slot.getGym().getGym_id());

//		if (!list.isEmpty()) {
//			Gym_Pass pass = list.get(0);
		gymPassRepo.updatePass(pass.getPass_id(), pass.getBalance() - 1);
//			return true;
//		} else {
//			return false;
//		}
	}

	@Transactional
	public void addPass(String gym_id) {
		Gym_Pass temp = new Gym_Pass();
		temp.setGym(gymRepo.getById(gym_id));
		temp.setUser(userRepo.getById(logged.getCurrUser()));
		temp.setExpiry_date(LocalDate.now().plusMonths(6));
		temp.setBalance(10);
		gymPassRepo.save(temp);
	}

	@Transactional
	public String purchasePass(String gym_id) {
		User user = userRepo.getById(logged.getCurrUser());
		Gym gym = gymRepo.getById(gym_id);

		if (user.getCredit() < gym.getPass_price()) {
			msg = "Insufficent credit";
			return msg;
		} else {
			System.out.println("credit to deduct" + (gym.getPass_price() * -1));
			updateCredit(gym.getPass_price() * -1);
			addPass(gym_id);
			msg = "Qty 10 pass purchased from: " + gym.getGym_name();
			return msg;
		}
	}

	public List<Gym> getGym() {
		return gymRepo.getGym();
	}

	public Friend_List findFriendList(int user1_id, int user2_id) {

		User user = userRepo.findUser(user1_id);
		Friend_List temp = null;

		for (Friend_List item : user.getFriendList1()) {
			if (user2_id == item.getFriend_id()) {
				temp = item;
			}
		}

		for (Friend_List item : user.getFriendList2()) {
			if (user2_id == item.getPerson_id()) {
				temp = item;
			}
		}
		return temp;
	}

	public List<UserResult> generateFriendList(Status_Code code) {

		User user = userRepo.findUser(logged.getCurrUser());
		List<UserResult> temp = new ArrayList<>();
		Status_Code Code1 = null;
		Status_Code Code2 = null;

		switch (code) {
		case A:
			Code1 = code;
			Code2 = code;
			break;

		case P1:
			Code1 = Status_Code.F;
			Code2 = Status_Code.R;
			break;

		case P2:
			Code1 = Status_Code.R;
			Code2 = Status_Code.F;
			break;

		default:
			break;
		}

		for (Friend_List item : user.getFriendList1()) {
			if (code == item.getStatus_code()) {
				User newUser = item.getFriend();
				temp.add(new UserResult(newUser.getUser_id(), newUser.getName(), newUser.getGender(), Code1));
			}
		}

		for (Friend_List item : user.getFriendList2()) {
			if (code == item.getStatus_code()) {
				User newUser = item.getPerson();
				temp.add(new UserResult(newUser.getUser_id(), newUser.getName(), newUser.getGender(), Code2));
			}
		}
		return temp;

	}

	public Boolean isFriend(int user1_id, int user2_id) {
		Boolean flag = false;
		Friend_List temp = findFriendList(user1_id, user2_id);

		if (Objects.nonNull(temp) && temp.getStatus_code() == Status_Code.A) {
			flag = true;
		}

//		User user = userRepo.findUser(user1_id);

//		for (Friend_List item : user.getFriendList1()) {
//			if (item.getStatus_code() == Status_Code.A && user2_id == item.getFriend_id()) {
//				flag = true;
//			}
//		}
//
//		for (Friend_List item : user.getFriendList2()) {
//			if (item.getStatus_code() == Status_Code.A && user2_id == item.getPerson_id()) {
//				flag = true;
//			}
//		}
		return flag;
	}

	public List<String> friendSlot(int timeslot_id) {
		List<User> timeslotUser = bookingRepo.getUser(timeslot_id);
		List<String> userIdList = new ArrayList<>();

		for (User item : timeslotUser) {
			if (isFriend(logged.getCurrUser(), item.getUser_id())) {
				userIdList.add(item.getName());
			}
		}
		return userIdList;
	}

	public Boolean isBooked(int slot_id) {

		Boolean flag = false;
		List<Booking> list = userRepo.findUser(logged.getCurrUser()).getBookings();

		for (Booking item : list) {
			if (item.getTimeslot().getSlot_id() == slot_id) {
				flag = true;
			}
		}
		return flag;
	}

	public List<TimeslotResult> getSchedule(String gym_id, LocalDate slot_date) {

		List<Timeslot> list = timeslotRepo.getTimeslot(gym_id, slot_date);
		List<TimeslotResult> resultList = new ArrayList<>();
		for (Timeslot item : list) {

			resultList.add(new TimeslotResult(item.getSlot_id(), item.getSlot_number(), item.getDescription(),
					isBooked(item.getSlot_id()), friendSlot(item.getSlot_id())));
		}
//		TimeslotResult result = new TimeslotResult( slot_id,  slot_number,  description,  isBooked)

		return resultList;
	}

	public void createBooking(User user, Timeslot slot, Gym_Pass pass) {
		Booking booking = new Booking();
		booking.setUser(user);
		booking.setTimeslot(slot);
		booking.setGym_pass(pass);
		bookingRepo.save(booking);
	}

	public Gym_Pass getValidPass(List<Gym_Pass> list, LocalDate slot_date) {
		for (Gym_Pass item : list) {
			if (item.getBalance() > 0 && !item.getExpiry_date().isBefore(slot_date)) {
				return item;
			}
		}
		return null;
	}

	// Need to check for existing booking status
	@Transactional
	public String slotBooking(int slot_id) {

		User user = userRepo.findUser(logged.getCurrUser());
		User passHolder = user;
		Gym_Pass pass = null;
		Timeslot slot = timeslotRepo.getById(slot_id);

		List<Gym_Pass> list = gymPassRepo.findPass(logged.getCurrUser(), slot.getGym().getGym_id());
		pass = getValidPass(list, slot.getSlot_date());

		// Checking if user have pass
		if (Objects.isNull(pass)) {

			// checking if user's friend have pass
			for (Friend_List item : user.getFriendList1()) {
				if (item.getStatus_code() == Status_Code.A) {
					list = gymPassRepo.findPass(item.getFriend_id(), slot.getGym().getGym_id());
					pass = getValidPass(list, slot.getSlot_date());
				}
				if (Objects.nonNull(pass)) {
					passHolder = item.getFriend();
					break;
				}
			}

			if (Objects.isNull(pass)) {
				for (Friend_List item : user.getFriendList2()) {
					if (item.getStatus_code() == Status_Code.A) {
						list = gymPassRepo.findPass(item.getPerson_id(), slot.getGym().getGym_id());
						pass = getValidPass(list, slot.getSlot_date());
					}
					if (Objects.nonNull(pass)) {
						passHolder = item.getPerson();
						break;
					}
				}
			}
		}

		if (Objects.nonNull(pass)) {
			deductPass(pass);
			createBooking(user, slot, pass);
			msg = "Pass deducted from: " + passHolder.getName();
		} else {
			msg = "No pass available";
		}

		return msg;
	}

}
