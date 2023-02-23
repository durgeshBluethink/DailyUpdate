package com.blogger.security;

import com.blogger.entities.User;
import com.blogger.exception.ResourceNotFoundException;
import com.blogger.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User ", " email : " + username, 0));
		return user;
	}
}
