package com.project.tb.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.tb.payload.BookRequests;
@Repository
public interface BookRequestRepo extends CrudRepository <BookRequests , Long>{
		List<BookRequests> findByEmail(String email);
		void deleteById(Long id);
	}
