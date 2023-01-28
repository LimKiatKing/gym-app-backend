package main.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public enum Gender {
		M, F;
	}

	private int credit;

	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Gym_Pass> gym_passes;

//		//doesn't seem like need to be fetch eager, can still pull out friends list via getFriend()
//		@ManyToMany(/*fetch = FetchType.EAGER*/)
//		@JsonBackReference
//		@JoinTable(name="friend_list",
//		 joinColumns=@JoinColumn(name="person_id"),
//		 inverseJoinColumns=@JoinColumn(name="friend_id")
//		)
//		private List<User> friends;
//
//		@ManyToMany
//		@JsonBackReference
//		@JoinTable(name="friend_list",
//		 joinColumns=@JoinColumn(name="friend_id"),
//		 inverseJoinColumns=@JoinColumn(name="person_id")
//		)
//		private List<User> friendOf;

	@OneToMany(mappedBy = "person")
	private List<Friend_List> friendList1;

	// Not possible to ignore one of these friend list, tried both backref and
	// ignore. might consider doing 1 data per friend request
	@OneToMany(mappedBy = "friend")
	private List<Friend_List> friendList2;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Gym_Pass> getGym_passes() {
		return gym_passes;
	}

	public void setGym_passes(List<Gym_Pass> gym_passes) {
		this.gym_passes = gym_passes;
	}

	public List<Friend_List> getFriendList1() {
		return friendList1;
	}

	public void setFriendList1(List<Friend_List> friendList1) {
		this.friendList1 = friendList1;
	}

	public List<Friend_List> getFriendList2() {
		return friendList2;
	}

	public void setFriendList2(List<Friend_List> friendList2) {
		this.friendList2 = friendList2;
	}



//		public List<User> getFriends() {
//			return friends;
//		}
//
//		public void setFriends(List<User> friends) {
//			this.friends = friends;
//		}
//
//		public List<User> getFriendOf() {
//			return friendOf;
//		}
//
//		public void setFriendOf(List<User> friendOf) {
//			this.friendOf = friendOf;
//		}
}
