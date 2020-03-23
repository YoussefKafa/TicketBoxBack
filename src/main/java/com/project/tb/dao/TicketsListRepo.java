package com.project.tb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.tb.models.TicketsList;
@Repository
public interface TicketsListRepo extends CrudRepository<TicketsList, Long>{
TicketsList findByUserIdentifier(String Identifier);
}
