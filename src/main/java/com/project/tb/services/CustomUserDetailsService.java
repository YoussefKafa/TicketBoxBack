package com.project.tb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tb.dao.UserRepo;
import com.project.tb.models.User;
import com.project.tb.wrappers.UserPrincipal;
@Service
//**checked**//
public class CustomUserDetailsService implements UserDetailsService{
	//injection of userRepo to automatically generate instance of userRepo 
	@Autowired
	private UserRepo userRepo;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Let people login with either username or email
		User user;
        try {
        	 user = userRepo.findByEmail(email);
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with email "+email);
		}
        return UserPrincipal.create(user);
	}
 @Transactional
 public UserDetails loadUserById(Long id) {
	 User user=userRepo.getById(id);
	 if (user==null) new UsernameNotFoundException("User not found");
		return UserPrincipal.create(user);
 }
}
