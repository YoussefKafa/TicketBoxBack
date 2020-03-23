package com.project.tb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tb.models.*;

@Repository
public interface GameRepo extends CrudRepository <Game , Long>{
	public Game getById(Long id);
}