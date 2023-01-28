package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.Friend_List;
import main.entities.Friend_List.Status_Code;
import main.entities.FriendshipId;

@Repository
public interface FriendListRepo extends JpaRepository<Friend_List, FriendshipId> {
	
//	@Query("SELECT g FROM Gym g")
//	List<Gym> getGym();
	
	@Modifying
	@Query("UPDATE Friend_List f SET f.status_code = :code WHERE f.person_id = :person_id AND f.friend_id = :friend_id")
	void updateFriend(@Param("person_id") Integer person_id, @Param("friend_id") Integer friend_id, @Param("code") Status_Code code);
}

