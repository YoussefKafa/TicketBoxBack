package com.project.tb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tb.models.*;

@Repository
public interface TicketRepo extends CrudRepository <Ticket , Long>{
}