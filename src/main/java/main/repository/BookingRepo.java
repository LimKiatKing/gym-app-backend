package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import main.entities.Booking;
import main.entities.User;


@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
	
	@Query("SELECT b.user FROM Booking b WHERE b.timeslot.slot_id = :slot_id")
	List<User> getUser(@Param("slot_id") int slot_id);

}
