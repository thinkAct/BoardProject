package com.mmventures.study.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class BoardComment {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int commentId;
	private String name;
	private String comm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="boardId")
	private Board boardContent;
	
	
	public BoardComment(){} 
	
	public Board getboardContent() {
		return boardContent;
	}

	public void setboardContent(Board board) {
		this.boardContent = board;
	}
	
	public BoardComment(String name, String comm) {
		this.name = name;
		this.comm = comm;
	}
	public final int getSeq() {
		return commentId;
	}
	public final void setSeq(int seq) {
		this.commentId = seq;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getComm() {
		return comm;
	}
	public final void setComm(String comm) {
		this.comm = comm;
	}
}
