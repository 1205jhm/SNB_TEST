package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Snbuser;

public interface UserRepository extends JpaRepository<Snbuser, String> {
}
