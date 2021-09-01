package com.cts.zuul.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.zuul.beans.User;
import com.cts.zuul.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void registerUser(User user) throws Exception {
		
		if(!userRepository.getUserByUsername(user.getUsername()).isPresent()) {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
	}

}
