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

@Entity
//@Table(name = "timeslots")
public class Timeslot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int slot_id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate slot_date;
	
	private int slot_number;
	private String description;
	private int size;
	
	@ManyToOne
	@JoinColumn(name="gym_id")
	private Gym gym;
	
	@OneToMany(mappedBy = "timeslot")
	@JsonBackReference
	private List<Booking> bookings;

	public int getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
	}

	public LocalDate getSlot_date() {
		return slot_date;
	}

	public void setSlot_date(LocalDate slot_date) {
		this.slot_date = slot_date;
	}

	public int getSlot_number() {
		return slot_number;
	}

	public void setSlot_number(int slot_number) {
		this.slot_number = slot_number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
