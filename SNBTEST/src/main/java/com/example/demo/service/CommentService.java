package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Snbcomment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.util.CommentSpec;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<Snbcomment> findAllComment(Snbcomment Snbcomment) {
		String boardseq = "" + Snbcomment.getBoardseq();
		List<Snbcomment> snbcommentList = commentRepository.findAll(CommentSpec.withBoardseq(boardseq).and(CommentSpec.withDelyn("N")), Sort.by("commentseq").ascending());
		return snbcommentList;
	}

	public Snbcomment saveComment(Snbcomment snbcomment) {
		snbcomment.setDelyn("N");
		snbcomment.setUpddate(LocalDateTime.now());
		return commentRepository.save(snbcomment);
	}

	public Snbcomment deleteComment(Snbcomment snbcomment) {
		snbcomment.setDelyn("Y");
		snbcomment.setUpddate(LocalDateTime.now());
		return commentRepository.save(snbcomment);
	}
}
