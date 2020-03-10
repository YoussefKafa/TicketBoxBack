package com.project.tb.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.TeamUniqueException;
import com.project.tb.models.User;
@Service
public class UserServices{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //comes with spring security
    public User saveUser(User newUser){
    	try {
    		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword())); //encode the password
    		newUser.setConfirmPassword(""); //to prevent it from appearing in json
    	//for the one above also we can use @jsonIgnore in User class
    		return userRepo.save(newUser);
		} catch (final Exception e) {
            throw new TeamUniqueException("User email:   "+ newUser.getEmail().toLowerCase()+ " is already exists");
		}
//the happy path first
//email has to be unique (custom exception)
//make sure that password and confirmed password match
//don't persist or show the confirm password
    }
}