package com.project.tb.wrappers;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.tb.models.User;

import io.jsonwebtoken.lang.Arrays;

public class OurUserDetails implements UserDetails{
private User user;
public OurUserDetails(User user) {
	this.user = user;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	//this method returns a Collection of User Roles names existed
	SimpleGrantedAuthority authority =new SimpleGrantedAuthority(user.getRole());
	return java.util.Arrays.asList(authority);
}

@Override
public String getPassword() {
	return user.getPassword();
}

@Override
public String getUsername() {
	return user.getEmail();
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
}
