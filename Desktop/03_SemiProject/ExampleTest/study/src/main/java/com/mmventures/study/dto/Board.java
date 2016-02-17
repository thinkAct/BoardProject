package com.mmventures.study.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int boardId;
	private String name;
	private String password;
	private String title;
	private String content;
	private String fileName;
	private Date regdate;
	private int readCount;
	private int reply;
	private int reply_step;
	private int reply_level;
	private boolean isDeleted;	
	@OneToMany(fetch = FetchType.LAZY,  mappedBy = "boardContent" )
	private List<BoardComment> boardComments = new ArrayList<BoardComment>();

	public Board() {}
	
	public Board(String title,String name, String content){
		this.name = name;
		this.title = title;
		this.content = content;
	}
	
	public Board(String name, String password, String title, String content, String fileName) {
		this.name = name;
		this.password = password;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}
	
	public Board(String name, String password, String title, String content, String fileName, int readCount) {
		this(name,password,title,content,fileName);
		this.readCount = readCount;
	}
	
	public int getBoardId() {
		return boardId;
	}
	
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	

	public final List<BoardComment> getBoardComments() {
		return boardComments;
	}
	
	public final void setBoardComments(List<BoardComment> boardComments) {
		this.boardComments = boardComments;
	}

	public final boolean isDeleted() {
		return isDeleted;
	}

	public final void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", name=" + name + ", password=" + password + ", title=" + title + ", content="
				+ content + ", fileName=" + fileName + ", regdate=" + regdate + ", readCount=" + readCount + ", reply="
				+ reply + ", reply_step=" + reply_step + ", reply_level=" + reply_level + ", isDeleted=" + isDeleted
				+ "]";
	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getRegdate() {
		return regdate;  //2013-07-15 형태
	}
	public void setRegdate(Date date){
		this.regdate = date;
	}


	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public int getReply_step() {
		return reply_step;
	}

	public void setReply_step(int reply_step) {
		this.reply_step = reply_step;
	}

	public int getReply_level() {
		return reply_level;
	}

	public void setReply_level(int reply_level) {
		this.reply_level = reply_level;
	}     

}
