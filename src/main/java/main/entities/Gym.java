package main.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
//@Table(name = "gyms")
public class Gym {

	@Id
	private String gym_id;
	private String gym_name;
	private String address;
	private int pass_price;

	@OneToMany(mappedBy = "gym")
	@JsonBackReference
	private List<Timeslot> timeslots;
	
	@OneToMany(mappedBy = "gym")
	@JsonBackReference
	private List<Gym_Pass> gym_passes;

	public String getGym_id() {
		return gym_id;
	}

	public void setGym_id(String gym_id) {
		this.gym_id = gym_id;
	}

	public String getGym_name() {
		return gym_name;
	}

	public void setGym_name(String gym_name) {
		this.gym_name = gym_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPass_price() {
		return pass_price;
	}

	public void setPass_price(int pass_price) {
		this.pass_price = pass_price;
	}

	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}
	

}
