package com.project.tb.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ReturnPasswordCodeModelRepo extends CrudRepository <com.project.tb.models.ReturnPasswordCodeModel , Long>{
	@Query(value="select id from return_password_code_model where return_password_code_model.email=:email", nativeQuery = true)
    public Long getIdFromEmail(@Param("email") String email);
 boolean existsById(Long id);
}
