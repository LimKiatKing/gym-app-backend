package main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
//@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int booking_id;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "slot_id")
	private Timeslot timeslot;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "pass_id")
	private Gym_Pass gym_pass;

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timeslot getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}

	public Gym_Pass getGym_pass() {
		return gym_pass;
	}

	public void setGym_pass(Gym_Pass gym_pass) {
		this.gym_pass = gym_pass;
	}

}
