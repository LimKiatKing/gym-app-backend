package main.entities;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
//@Table(name = "friend_list")
@IdClass(FriendshipId.class)
public class Friend_List implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int person_id;

	@Id
	private int friend_id;
	
	@Enumerated(EnumType.STRING)
	private Status_Code status_code;

	// Approved (A), Pending user 1 (P1), Pending user 1 (P2), None (N)
	// For frontend only - Requested (R), For approval (F)
	public enum Status_Code {
		A, P1, P2, N, R, F;
	}
	
//	@ManyToMany(mappedBy="friends")
//	@JsonBackReference
//	private List<User> people;
//	
//	@ManyToMany(mappedBy="friendOf")
//	@JsonBackReference
//	private List<User> friends;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="person_id", insertable = false, updatable = false)
	private User person;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="friend_id", insertable = false, updatable = false)
	private User friend;

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, friend_id);
    }

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


//	public List<User> getPeople() {
//		return people;
//	}
//
//	public void setPeople(List<User> people) {
//		this.people = people;
//	}
//
//	public List<User> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<User> friends) {
//		this.friends = friends;
//	}

	public Status_Code getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Status_Code status_code) {
		this.status_code = status_code;
	}

	public User getPerson() {
		return person;
	}

	public void setPerson(User person) {
		this.person = person;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

}
