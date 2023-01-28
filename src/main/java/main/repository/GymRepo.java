package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.Gym;

@Repository
public interface GymRepo extends JpaRepository<Gym, String> {
	
	@Query("SELECT g FROM Gym g")
	List<Gym> getGym();
}
