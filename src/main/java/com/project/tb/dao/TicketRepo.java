package com.project.tb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.tb.models.*;

@Repository

public interface TicketRepo extends CrudRepository <Ticket , Long>{
	Optional<Ticket> findById(Long id);
	@Transactional
		@Modifying
		@Query(value="UPDATE ticket SET game_id=:game_id where ticket_id=:ticket_id",nativeQuery = true)
		void addGame(@Param("ticket_id") Long ticket_id,@Param("game_id") Long game_id);
	@Transactional
		@Modifying
		@Query(value="DELETE from ticket where ticket_id=:ticketId",nativeQuery = true)
	void deleteTicket(@Param("ticketId") Long ticketId);
}