package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Snbboard;

@Repository
public interface BoardRepository extends JpaRepository<Snbboard, Integer>, JpaSpecificationExecutor<Snbboard> {
}
