package com.project.tb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.EmployeeUniqueException;
import com.project.tb.exceptions.UserUniqueException;
import com.project.tb.models.Employee;
import com.project.tb.models.User;

@Service
public class UserServices {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // comes with spring security

	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword())); // encode the password
			newUser.setConfirmPassword(""); // to prevent it from appearing in json
			// for the one above also we can use @jsonIgnore in User class
			return userRepo.save(newUser);
		} catch (final Exception e) {
			throw new UserUniqueException("User email:   " + newUser.getEmail().toLowerCase() + " is already exists");
		}
//the happy path first
//email has to be unique (custom exception)
//make sure that password and confirmed password match
//don't persist or show the confirm password
	}
//tested
	public List<User> findAll() {
		var it = userRepo.findAll();
		var users = new ArrayList<User>();
		it.forEach(e -> users.add(e));
		return users;
	}
//tested
	public Long count() {
		return userRepo.count();
	}
	//tested
	public User findById(Long userId) {
    	Optional<User> user = userRepo.findById(userId);
    	User user2 =user.get();
    	if (user2==null) 
    		throw new UserUniqueException("User with id : " + userId + " does not exist");
    	return user2;
    }
	//tested
	public void deleteById(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		User user2 = user.get();
		if (user == null)
			throw new UserUniqueException("User with id " + userId + " cannot be found");
		userRepo.delete(user2);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}
}