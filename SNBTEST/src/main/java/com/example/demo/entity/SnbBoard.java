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
public class SnbBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardSeq;

	@Column
	private String title;

	@Column
	private String content;

	@Column(insertable = false)
	private String delYn;

	@Column(insertable = false, updatable = false)
	private LocalDateTime insDate;

	@Column(insertable = false)
	private LocalDateTime updDate;

	@Column(updatable = false)
	private String insUser;

	@Transient
	private int totalPage;

	@Transient
	private int limit;

	@Transient
	private int pageNum;
}
