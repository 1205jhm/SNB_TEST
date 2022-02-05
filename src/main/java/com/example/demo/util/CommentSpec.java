package com.example.demo.util;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.SnbComment;

public class CommentSpec {
	public static Specification<SnbComment> withBoardSeq(String boardSeq) {
		return (Specification<SnbComment>) ((root, query, builder) -> builder.equal(root.get("boardSeq"), boardSeq));
	}
	
	public static Specification<SnbComment> withDelYn(String delYn) {
		return (Specification<SnbComment>) ((root, query, builder) -> builder.equal(root.get("delYn"), delYn));
	}
}
