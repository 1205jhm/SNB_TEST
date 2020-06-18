package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Snbuser;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public Snbuser login(Snbuser snbuser) {
		Optional<Snbuser> snbuserOptional = userRepository.findById(snbuser.getUsername());
		Snbuser loginuser = snbuserOptional.get();
		return loginuser;
	}

	public Snbuser join(Snbuser snbuser) {
		return userRepository.save(snbuser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Snbuser> byUserName = userRepository.findById(username);
		Snbuser snbuser = byUserName.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(snbuser.getUsername(), "{noop}" + snbuser.getPassword(), authorities());

	}

	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
