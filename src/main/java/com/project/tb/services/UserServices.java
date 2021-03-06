package com.project.tb.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.ReturnPasswordCodeModel;
import com.project.tb.models.Ticket;
import com.project.tb.models.User;
import com.project.tb.payload.ChangeForgPassword;
import com.project.tb.payload.ChangePasswordRequest;
import com.project.tb.payload.CreditRequest;
@Service
public class UserServices {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TicketRepo ticketrepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; // comes with spring security
	@Autowired
	private EmailServices emailServices;
	@Autowired
	private ReturnPasswordCodeModelServices returnPasswordCodeModelServices;
	public User saveUser(User user) throws Exception {
		Optional<User>user1 =userRepo.findById(user.getId());
		System.out.println(bCryptPasswordEncoder.matches(user.getPassword(), user1.get().getPassword()));
		if ( !bCryptPasswordEncoder.matches(user.getPassword(), user1.get().getPassword())) {
			throw new ModelException("Password is not correct.");
		}
		user.setCreatedAt(user1.get().getCreatedAt());
		user.setUpdatedAt(new Date().toInstant());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepo.save(user);
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
		System.out.println("xxxxxxxxxxxx");
		User user2;
		try {
		user2 = user.get();}
		catch (NoSuchElementException e) {
			throw new ModelException("User with id: " + userId + " does not exist.");
		}
		if (user2 == null)
			throw new ModelException("User with id : " + userId + " does not exist");
		return user2;
	}

	// tested
	public void deleteById(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		User user2 = user.get();
		if (user == null)
			throw new ModelException("User with id " + userId + " cannot be found");
		userRepo.delete(user2);
	}
public void addCredit(int credit, long id) {
	userRepo.addCredit(credit, id);
}
public void addCreditByEmail(CreditRequest creditRequest) {
	userRepo.addCredit(creditRequest.getCredit(),
			userRepo.getIdFromEmail(creditRequest.getEmail()));
}
public User findByEmail(String email) {
	return userRepo.findById(userRepo.getIdFromEmail(email)).get();
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
	public ArrayList<Ticket> getTicketsByUserId(int id){
		List<BigInteger> idOfTickets= userRepo.getTicketsByUserId(id);
		Ticket ticket1= new Ticket();
		ArrayList<Ticket> tickets=new ArrayList<Ticket>();
		for (BigInteger bigInteger : idOfTickets) {
			System.out.println(bigInteger+"******************************************************");
			try {
		Optional<Ticket> ticket= ticketrepo.findById(Long.parseLong(bigInteger.toString()));
		 ticket1=ticket.get();}
			catch (Exception e) {System.out.println(e.getMessage());}
		tickets.add(ticket1);	
		}
		return tickets;
	}
	public int getRoleIdFromRoleName(String name) {
		return userRepo.getRoleIdFromRoleName(name);
	}

	public void changePassword(ChangePasswordRequest changePasswordRequest) {
		Optional<User>user1 =userRepo.findById(Long.parseLong(changePasswordRequest.getIdOfUser()));
		if ( !bCryptPasswordEncoder.matches(changePasswordRequest.getOldPass(), user1.get().getPassword())) {
			throw new ModelException("Password is not correct!");
		}
		userRepo.changePassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPass()), Long.parseLong(changePasswordRequest.getIdOfUser()));
	}
	public void returnPassword(ChangeForgPassword changeForgPassword) {
		String email=changeForgPassword.getEmail();
		Long id=returnPasswordCodeModelServices.getIdFromEmail(email);
		Optional<ReturnPasswordCodeModel> returnPasswordCodeModel=returnPasswordCodeModelServices.findById(id);
		if(changeForgPassword.getCode()==returnPasswordCodeModel.get().getCode()) {
			userRepo.changePassword(bCryptPasswordEncoder.encode(changeForgPassword.getNewPassword()),userRepo.getIdFromEmail(changeForgPassword.getEmail()));
			returnPasswordCodeModelServices.deleteById(returnPasswordCodeModelServices.getIdFromEmail(email));
		}
		else {
			throw new ModelException("Invalid Code");
		}
	}
	public void forgetPassword(String email) {
		int min=1; int max=1000000;
		int random_int = (int)(Math.random() * (max - min + 1) + min);
	ReturnPasswordCodeModel returnPasswordCodeModel=new ReturnPasswordCodeModel();
	returnPasswordCodeModel.setCode(random_int);
	returnPasswordCodeModel.setEmail(email);
	returnPasswordCodeModelServices.saveOrUpdate(returnPasswordCodeModel);
		emailServices.sendMessage(email,random_int);
	}
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	}
