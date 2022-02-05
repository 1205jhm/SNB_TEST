package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SnbComment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.util.CommentSpec;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<SnbComment> findAllComment(SnbComment Snbcomment) {
		String boardSeq = "" + Snbcomment.getBoardSeq();
		List<SnbComment> snbcommentList = commentRepository.findAll(CommentSpec.withBoardSeq(boardSeq).and(CommentSpec.withDelYn("N")), Sort.by("commentSeq").ascending());
		return snbcommentList;
	}

	public SnbComment saveComment(SnbComment snbComment) {
		snbComment.setDelYn("N");
		snbComment.setUpdDate(LocalDateTime.now());
		return commentRepository.save(snbComment);
	}

	public SnbComment deleteComment(SnbComment snbComment) {
		snbComment.setDelYn("Y");
		snbComment.setUpdDate(LocalDateTime.now());
		return commentRepository.save(snbComment);
	}
}
