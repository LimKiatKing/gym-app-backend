package main.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.Timeslot;

@Repository
public interface TimeslotRepo extends JpaRepository<Timeslot, Integer> {
	
	@Query("SELECT t FROM Timeslot t WHERE t.gym.gym_id = :gym_id AND t.slot_date = :slot_date")
	List<Timeslot> getTimeslot(@Param("gym_id") String gym_id, @Param("slot_date") LocalDate slot_date);
}
