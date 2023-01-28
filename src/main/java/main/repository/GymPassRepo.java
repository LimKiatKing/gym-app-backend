package main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.Gym_Pass;
import main.entities.User;

@Repository
public interface GymPassRepo extends JpaRepository<Gym_Pass, Integer> {

	@Query("SELECT p FROM Gym_Pass p WHERE p.user.user_id = :user_id AND p.gym.gym_id = :gym_id AND p.balance > 0")
	List<Gym_Pass> findPass(@Param("user_id") Integer user_id, @Param("gym_id") String gym_id);

	@Transactional
	@Modifying
	@Query("UPDATE Gym_Pass p SET p.balance = :balance WHERE p.pass_id = :pass_id")
	void updatePass(@Param("pass_id") Integer pass_id, @Param("balance") Integer balance);

}
