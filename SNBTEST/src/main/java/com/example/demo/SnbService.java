package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Snbboard;
import com.example.demo.entity.Snbuser;

@Service
public class SnbService {

	@Autowired
	private BoardRepository boardRepository;
	private UserRepository userRepository;
	
	public List<Snbboard> findAllBoard() {
		return boardRepository.findAll();
	}
	
	public Snbboard saveBoard(Snbboard board) {
		return boardRepository.save(board);
	}
	
	public void deleteBoard(Snbboard board) {
		boardRepository.delete(board);
	}
	
	public List<Snbuser> findAllUser() {
		return userRepository.findAll();
	}
	
	public Optional<Snbuser> findByIdUser(String userid) {
		return userRepository.findById(userid);
	}
	
	public Snbuser saveUser(Snbuser user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(Snbuser user) {
		userRepository.delete(user);
	}
}
