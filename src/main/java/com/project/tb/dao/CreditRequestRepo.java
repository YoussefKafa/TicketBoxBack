package com.project.tb.dao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.tb.payload.CreditRequest;
public interface CreditRequestRepo extends CrudRepository <CreditRequest , Long>{
	@Transactional
	@Modifying
	@Query(value="UPDATE credit_request SET user_id=:user_id where id=:credit_id",nativeQuery = true)
	void addUser(@Param("credit_id") Long credit_id,@Param("user_id") Long user_id);
}
