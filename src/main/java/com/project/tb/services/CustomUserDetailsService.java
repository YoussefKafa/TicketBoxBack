package com.project.tb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tb.dao.UserRepo;
import com.project.tb.models.User;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	//injection of userRepo to automatically generate instance of userRepo 
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email);
		if (user==null) new UsernameNotFoundException("User not found");
		return (UserDetails) user;
	}
 @Transactional
 public User loadUserById(Long id) {
	 User user=userRepo.getById(id);
	 if (user==null) new UsernameNotFoundException("User not found");
		return user;
 }
}
