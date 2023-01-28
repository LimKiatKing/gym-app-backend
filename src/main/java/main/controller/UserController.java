package main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.entities.Friend_List.Status_Code;
import main.entities.Gym;
import main.entities.Timeslot;
import main.entities.TimeslotResult;
import main.entities.User;
import main.entities.UserResult;
//import main.entities.Car;
//import main.entities.Owner;
import main.repository.UserRepo;
import main.service.UserService;

//NOTES
//Need to check on the friend_list serializable

@RestController
@CrossOrigin(origins = "http://localhost:3000/" , allowedHeaders = "true" , allowCredentials = "true")
//@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/ping")
	public ResponseEntity<?> ping() {

		return ResponseEntity.ok().body("Service Online");
	}

	@GetMapping("/login/{id}")
	public ResponseEntity<?> loginUser(@PathVariable(value = "id") int user_id) {

		Optional<User> user = null;
		
		if (userService.userExist(user_id)) {
			userService.login(user_id);
			
			 user = userService.getLoginInfo();
			 
			return ResponseEntity.ok().body(user);
		} else {
			String msg = "User does not exist";
			return ResponseEntity.badRequest().body(msg);
		}
	}

	@GetMapping("/logout/")
	public ResponseEntity<?> logout() {
		
		userService.logout();
		return ResponseEntity.ok().body("Logout successful");

	}

	@GetMapping("/reload-user/")
	public ResponseEntity<?> reloadUser() {

		Optional<User> user = userService.getLoginInfo();

		if (Objects.nonNull(user)) {
			return ResponseEntity.ok().body(user);
		} else {
			String msg = "User does not exist";
			return ResponseEntity.badRequest().body(msg);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> searchUser(@PathVariable(value = "id") int user_id) {

		UserResult user = userService.searchUser(user_id);
		if (Objects.isNull(user)) {
			String msg = "Invalid input";
			return ResponseEntity.badRequest().body(msg);
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/friend-request/{id}")
	public ResponseEntity<?> friendRequest(@PathVariable(value = "id") int user_id) {
		String msg = "";
		Boolean flag = userService.friendRequest(user_id);
		if (flag) {
			msg = "Friend request sent";
			return ResponseEntity.ok().body(msg);
		} else {
			msg = "Error occured while processing friend request";
			return ResponseEntity.badRequest().body(msg);
		}
	}

	@PutMapping("/friend-accept/{id}")
	public ResponseEntity<?> friendAccept(@PathVariable(value = "id") int user_id) {

		userService.updateFriend(user_id, Status_Code.A);
		String msg = "Friend request accepted";
		return ResponseEntity.ok().body(msg);
	}

	@GetMapping("/generate-Friendlist/{code}")
	public ResponseEntity<?> generateFriendList(@PathVariable(value = "code") Status_Code code) {

		return ResponseEntity.ok().body(userService.generateFriendList(code));
	}

	@GetMapping("/gym")
	public ResponseEntity<?> listGym() {

		List<Gym> list = userService.getGym();

		return ResponseEntity.ok().body(list);
	}

//	@GetMapping("/friend-slot/{timeslot_id}")
//	public ResponseEntity<?> friendSlot(@PathVariable(value = "timeslot_id") int timeslot_id) {
//
//		List<String> list = userService.friendSlot(timeslot_id);
//
//		return ResponseEntity.ok().body(list);
//	}

	@PutMapping("/purchase-credit/{credit}")
	public ResponseEntity<?> purchaseCredit(@PathVariable(value = "credit") int credit) {

		String msg = userService.updateCredit(credit);
		if (msg == "Insufficent credit") {
			return ResponseEntity.badRequest().body(msg);
		} else {
			return ResponseEntity.ok().body(msg);
		}
	}

	@PostMapping("/purchase-pass/{gym_id}")
	public ResponseEntity<?> purchasePass(@PathVariable(value = "gym_id") String gym_id) {

		String msg = userService.purchasePass(gym_id);

		return ResponseEntity.ok().body(msg);
	}

	@GetMapping("/view-schedule/{gym_id}/{slot_date}")
	public ResponseEntity<?> viewSchedule(@PathVariable(value = "gym_id") String gym_id,
			@PathVariable(value = "slot_date") String slot_date) {

		List<TimeslotResult> list = userService.getSchedule(gym_id, LocalDate.parse(slot_date));

		return ResponseEntity.ok().body(list);
	}

	@PostMapping("/book/{slot_id}")
	public ResponseEntity<?> makeBooking(@PathVariable(value = "slot_id") int slot_id) {

		String msg = userService.slotBooking(slot_id);
		if (msg == "No pass available") {
			return ResponseEntity.badRequest().body(msg);
		} else {
			return ResponseEntity.ok().body(msg);
		}

	}

}
