package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@DynamicUpdate
@DynamicInsert
@Data
@Entity
public class Snbboard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardseq;

	@Column
	private String title;

	@Column
	private String content;

	@Column(insertable = false)
	private String delyn;

	@Column(insertable = false, updatable = false)
	private LocalDateTime insdate;

	@Column(insertable = false)
	private LocalDateTime upddate;

	@Column(updatable = false)
	private String insuser;

	@Transient
	private int totalpage;

	@Transient
	private int limit;

	@Transient
	private int pagenum;
}
