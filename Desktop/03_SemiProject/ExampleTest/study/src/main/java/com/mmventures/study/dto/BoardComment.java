package com.mmventures.study.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BoardComment {
	@Id
	@GeneratedValue
	private int seq;
	private String name;
	private String comm;
	
	@ManyToOne
	private Board board; 
	
	
	public BoardComment(){} 
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public BoardComment(String name, String comm) {
		this.name = name;
		this.comm = comm;
	}
	public final int getSeq() {
		return seq;
	}
	public final void setSeq(int seq) {
		this.seq = seq;
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
