package main.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.user_id = :user_id")
	User findUser(@Param("user_id") Integer user_id);
	
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.credit = :credit WHERE u.user_id = :user_id")
	void updateCredit(@Param("user_id") Integer user_id, @Param("credit") Integer credit);

}
