package main.entities;

import main.entities.Friend_List.Status_Code;
import main.entities.User.Gender;

public class UserResult {
	
	private int user_id;
	private String name;
	private Gender gender;
	private Status_Code status_code;


	public UserResult(int user_id, String name, Gender gender, Status_Code status_code) {
		this.user_id = user_id;
		this.name = name;
		this.gender = gender;
		this.status_code = status_code;
	}


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


	public Status_Code getStatus_code() {
		return status_code;
	}


	public void setStatus_code(Status_Code status_code) {
		this.status_code = status_code;
	}

}
