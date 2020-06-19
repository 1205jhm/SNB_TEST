package com.example.demo.util;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.SnbBoard;

public class BoardSpec {
	public static Specification<SnbBoard> withDelYn(String delYn) {
		return (Specification<SnbBoard>) ((root, query, builder) -> builder.equal(root.get("delYn"), delYn));
	}
}
