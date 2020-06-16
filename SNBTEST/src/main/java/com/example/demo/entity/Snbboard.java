package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Snbboard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer boardseq;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String delyn;
	
	@Column
	private Date insdate;
	
	@Column
	private Date upddate;
	
	@Column
	private String insuser;
}
