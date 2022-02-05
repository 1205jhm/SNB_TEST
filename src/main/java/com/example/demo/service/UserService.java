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

import com.example.demo.entity.SnbUser;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public SnbUser login(SnbUser snbuser) {
		Optional<SnbUser> snbuserOptional = userRepository.findById(snbuser.getUsername());
		SnbUser loginuser = snbuserOptional.get();
		return loginuser;
	}

	public int join(SnbUser snbuser) {
		Optional<SnbUser> snbuserOptional = userRepository.findById(snbuser.getUsername());
		if(!snbuserOptional.isEmpty())
		{
			return -1;
		}
		else
		{
			userRepository.save(snbuser);
			return 0;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SnbUser> byUserName = userRepository.findById(username);
		SnbUser snbuser = byUserName.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(snbuser.getUsername(), "{noop}" + snbuser.getPassword(), authorities());
	}

	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
}
