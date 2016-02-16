package com.mmventures.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmventures.study.dao.BoardDAO;
import com.mmventures.study.dto.Board;
import com.mmventures.study.dto.BoardComment;

@Service("boardService")
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> boardList(){
		System.out.println("boardList in service");
		return boardDAO.boardList();
	}

	
	public Board readContent(int seq){
		return boardDAO.readContent(seq);
	}
	
	public void insertComment(int seq, BoardComment comment){ 
		boardDAO.insertComment(seq, comment);
	} 
	
//	public List<BoardComment> commentList(int seq){
//		return boardDAO.commentList(seq);
//	}
	
	public void insertBoard(Board board){
		boardDAO.insertBoard(board);
	}
	
	public void updateBoard(Board board){
		boardDAO.updateBoard(board);
	}
	
	public void deleteBoard(int seq){
		boardDAO.deleteBoard(seq);
	}
}
