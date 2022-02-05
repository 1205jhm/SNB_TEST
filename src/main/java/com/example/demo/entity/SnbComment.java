package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@DynamicUpdate
@DynamicInsert
@Data
@Entity
public class SnbComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentSeq;
	
	@Column(updatable = false)
	private Integer boardSeq;

	@Column(updatable = false)
	private String comment;

	@Column(insertable = false)
	private String delYn;

	@Column(insertable = false, updatable = false)
	private LocalDateTime insDate;

	@Column(insertable = false)
	private LocalDateTime updDate;

	@Column(updatable = false)
	private String insUser;

	@Override
	public String toString() {
		return "SnbComment [commentSeq=" + commentSeq + ", boardSeq=" + boardSeq + ", comment=" + comment + ", delYn="
				+ delYn + ", insDate=" + insDate + ", updDate=" + updDate + ", insUser=" + insUser + "]";
	}
}
