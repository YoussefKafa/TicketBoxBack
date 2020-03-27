package com.project.tb.dao;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.tb.models.*;
@Repository
public interface StadiumRepo extends CrudRepository <Stadium , Long>{
	@Transactional
	  @Modifying
	 @Query("delete FROM Stadium p WHERE p.name=name")
	    public void deleteByName(@Param("name") String name);
	@Transactional
	@Modifying
	@Query("UPDATE Stadium s set s.name = ?1 where s.stadiumId = ?2")
	void updateStadiumNameById(String name, Long id);
	@Transactional
	 @Query(value = "SELECT stadium_id FROM Stadium p WHERE p.name=name",nativeQuery = true)
	String getIdByName(@Param("name") String name);
	Stadium findByName(@Param("name") String name);
}