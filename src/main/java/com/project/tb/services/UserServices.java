package com.project.tb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.SomeThingWentWrong;
import com.project.tb.exceptions.UserException;
import com.project.tb.models.User;
import com.project.tb.payload.CreditRequest;
import com.sun.istack.FinalArrayList;

@Service
public class UserServices {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // comes with spring security

	public User saveUser(User user) throws Exception {
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // encode the password
			// for the one above also we can use @jsonIgnore in User class
			// create a ticketslist every time you save or update a user
			// if the user is new then create the list
			return userRepo.save(user);
		} catch (Exception e) {
			throw new UserException("User email:   " + user.getEmail().toLowerCase() + " is already exists");
		}
	}
//tested
	public List<User> findAll() {
		Iterable<User> it = userRepo.findAll();
		ArrayList<User> users = new ArrayList<User>();
		it.forEach(e -> users.add(e));
		return users;
	}

//tested
	public Long count() {
		return userRepo.count();
	}

	// tested
	public User findById(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		User user2;
		try {
		user2 = user.get();}
		catch (NoSuchElementException e) {
			throw new UserException("User with id: " + userId + " does not exist.");
		}
		if (user2 == null)
			throw new UserException("User with id : " + userId + " does not exist");
		return user2;
	}

	// tested
	public void deleteById(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		User user2 = user.get();
		if (user == null)
			throw new UserException("User with id " + userId + " cannot be found");
		userRepo.delete(user2);
	}
public void addCredit(int credit, long id) {
	userRepo.addCredit(credit, id);
}
public void addCreditByEmail(CreditRequest creditRequest) {
	userRepo.addCredit(creditRequest.getCredit(), userRepo.findByEmail(creditRequest.getEmail()).getId());
}
public User findByEmail(String email) {
	return userRepo.findByEmail(email);
}
	public void deleteAll() {
		userRepo.deleteAll();
	}
	public int countMaleUsers() {
		return userRepo.countMaleUsers();
	}
	public int countFemaleUsers() {
		return userRepo.countFemaleUsers();
	}
	public List<Object[]> countAgeGroupsOfUsers() {
		return userRepo.countAgeGroupsOfUsers();
	}
}