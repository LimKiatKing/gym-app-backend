package main.entities;



import java.io.Serializable;

public class FriendshipId implements Serializable {
	
	private int person_id;
	private int friend_id;
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

}
