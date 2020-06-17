package com.example.demo;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Snbboard;

public class BoardSpec {
	public static Specification<Snbboard> withDelyn(String delyn) {
		return (Specification<Snbboard>) ((root, query, builder) -> builder.equal(root.get("delyn"), delyn));
	}
}
