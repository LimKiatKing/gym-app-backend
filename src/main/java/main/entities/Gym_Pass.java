package main.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
//@Table(name = "gym_pass")
public class Gym_Pass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pass_id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate expiry_date;
	private int balance;
	
	@ManyToOne
	@JoinColumn(name="gym_id")
	private Gym gym;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy = "gym_pass")
	@JsonIgnore
	private List<Booking> bookings;

	public int getPass_id() {
		return pass_id;
	}

	public void setPass_id(int pass_id) {
		this.pass_id = pass_id;
	}

	public LocalDate getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(LocalDate expiry_date) {
		this.expiry_date = expiry_date;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	
}
