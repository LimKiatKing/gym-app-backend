package main.entities;

import java.util.List;

public class TimeslotResult {
	
	private int slot_id;
	private int slot_number;
	private String description;
	private Boolean isBooked;
	private List<String> slotFriends;

	public TimeslotResult(int slot_id, int slot_number, String description, Boolean isBooked, List<String> slotFriends) {
		this.slot_id = slot_id;
		this.slot_number = slot_number;
		this.description = description;
		this.isBooked = isBooked;
		this.slotFriends = slotFriends;
	}

	public int getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(int slot_id) {
		this.slot_id = slot_id;
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

	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}

	public List<String> getSlotFriends() {
		return slotFriends;
	}

	public void setSlotFriends(List<String> slotFriends) {
		this.slotFriends = slotFriends;
	}
	
	
}
