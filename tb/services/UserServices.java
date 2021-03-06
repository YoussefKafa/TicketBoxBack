package com.project.tb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.SomeThingWentWrong;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.TicketsList;
import com.project.tb.models.User;
import com.sun.istack.FinalArrayList;

@Service
public class UserServices {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // comes with spring security
	@Autowired
	private TicketsListRepo ticketsListRepo;

	public User saveUser(User user) throws Exception {
		try {
			user.setUserIdentifier(user.getUserIdentifier());
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // encode the password
			user.setConfirmPassword(""); // to prevent it from appearing in json
			// for the one above also we can use @jsonIgnore in User class
			// create a ticketslist every time you save or update a user
			// if the user is new then create the list
			if (user.getId() == null) {
				TicketsList ticketsList = new TicketsList();
				// set the relationship
				user.setTicketsList(ticketsList);
				ticketsList.setUser(user);
				ticketsList.setUserIdentifier(user.getUserIdentifier());
			}
			// and this is what to do if
			// we are updating a user
			// so the ticketslist will not be null
			if (user.getId() != null) {
				user.setTicketsList(ticketsListRepo.findByUserIdentifier(user.getUserIdentifier()));
			}
			return userRepo.save(user);
		} catch (Exception e) {
			throw new UserUniqueException("User email:   " + user.getEmail().toLowerCase() + " is already exists");
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
		User user2 = user.get();
		if (user2 == null)
			throw new UserUniqueException("User with id : " + userId + " does not exist");
		return user2;
	}

	// tested
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