package com.example.demo.util;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Snbcomment;

public class CommentSpec {
	public static Specification<Snbcomment> withBoardseq(String boardseq) {
		return (Specification<Snbcomment>) ((root, query, builder) -> builder.equal(root.get("boardseq"), boardseq));
	}
	
	public static Specification<Snbcomment> withDelyn(String delyn) {
		return (Specification<Snbcomment>) ((root, query, builder) -> builder.equal(root.get("delyn"), delyn));
	}
}
