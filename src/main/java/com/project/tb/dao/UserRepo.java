package com.project.tb.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.tb.models.User;
@Repository
public interface UserRepo extends CrudRepository <User , Long>{
    public User  findByEmail(String email);
	public User getById(Long id);
}