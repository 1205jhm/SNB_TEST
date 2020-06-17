package com.example.demo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Snbboard;

@Service
public class SnbService {

	@Autowired
	private BoardRepository boardRepository;

	public List<Snbboard> findAllBoard(Snbboard snbboard) {
		Pageable pageable = PageRequest.of(snbboard.getPagenum()-1, snbboard.getLimit(), Sort.by("boardseq").descending());
		Page<Snbboard> snbboardPage = boardRepository.findAll(BoardSpec.withDelyn("N"), pageable);
		List<Snbboard> snbboardList = snbboardPage.getContent();
		if(snbboardList.size() > 0)
		{
			snbboardList.get(0).setTotalpage(snbboardPage.getTotalPages());
		}
		return snbboardList;
	}
	
	public Snbboard findByIdBoard(Integer boardSeq) {
		Optional<Snbboard> snbBoardOptional = boardRepository.findById(boardSeq);
		return snbBoardOptional.get();
	}

	public Snbboard saveBoard(Snbboard snbboard) {
		snbboard.setDelyn("N");
		Date date = new Date();
		long longUtcTime = date.getTime();
		TimeZone zone = TimeZone.getDefault();
		int offset = zone.getOffset(longUtcTime);
		long longLocalTime = longUtcTime + offset;
		Date now = new Date();
		now.setTime(longLocalTime);
		snbboard.setUpddate(LocalDateTime.now());
		return boardRepository.save(snbboard);
	}

	public Snbboard deleteBoard(Snbboard snbboard) {
		snbboard.setUpddate(LocalDateTime.now());
		snbboard.setDelyn("Y");
		return boardRepository.save(snbboard);
	}
}
