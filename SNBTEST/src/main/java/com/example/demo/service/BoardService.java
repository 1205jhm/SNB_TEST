package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SnbBoard;
import com.example.demo.repository.BoardRepository;
import com.example.demo.util.BoardSpec;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public List<SnbBoard> findAllBoard(SnbBoard snbboard) {
		Pageable pageable = PageRequest.of(snbboard.getPageNum() - 1, snbboard.getLimit(), Sort.by("boardSeq").descending());
		Page<SnbBoard> snbboardPage = boardRepository.findAll(BoardSpec.withDelYn("N"), pageable);
		List<SnbBoard> snbboardList = snbboardPage.getContent();
		if (snbboardList.size() > 0) {
			snbboardList.get(0).setTotalPage(snbboardPage.getTotalPages());
		}
		return snbboardList;
	}

	public SnbBoard findByIdBoard(Integer boardSeq) {
		Optional<SnbBoard> snbBoardOptional = boardRepository.findById(boardSeq);
		return snbBoardOptional.get();
	}

	public SnbBoard saveBoard(SnbBoard snbboard) {
		snbboard.setDelYn("N");
		snbboard.setUpdDate(LocalDateTime.now());
		return boardRepository.save(snbboard);
	}

	public SnbBoard deleteBoard(SnbBoard snbboard) {
		snbboard.setDelYn("Y");
		snbboard.setUpdDate(LocalDateTime.now());
		return boardRepository.save(snbboard);
	}
}
