package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Snbuser {

	@Id
	private String userid;

	@Column
	private String pass;
}
