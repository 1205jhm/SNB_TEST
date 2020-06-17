package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Snbuser;

public interface UserRepository extends JpaRepository<Snbuser, String> {
}
