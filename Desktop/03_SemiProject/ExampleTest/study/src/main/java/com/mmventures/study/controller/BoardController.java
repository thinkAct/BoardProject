package com.mmventures.study.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mmventures.study.dto.Board;
import com.mmventures.study.dto.BoardComment;
import com.mmventures.study.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView board(HttpServletRequest reqrequest, HttpServletResponse response){
		System.out.println("list");

		ModelAndView mav = new ModelAndView("list","list", boardService.boardList());		
		return mav;
	}

	@RequestMapping(value = "/read.html", method = RequestMethod.GET)
	public ModelAndView read(HttpServletRequest request, HttpServletResponse response){
		
		int boardId = Integer.parseInt(request.getParameter("seq"));

		System.out.println("seq = "+ boardId);

		ModelAndView mav = new ModelAndView("read","read", boardService.readContent(boardId));		
		
		List<BoardComment> boardComments = boardService.commentList(boardId);
		
		if(boardComments!=null) {
			
			System.out.println("boardComment is null");
		
		} else {
			System.out.println("boardComment is not null");
		}
		
		mav.addObject("comments", boardComments);
		return mav;
	}

	@RequestMapping(value="/comment.html")
	public ModelAndView comment(HttpServletRequest request, HttpServletResponse response){

		int boardId = Integer.parseInt(request.getParameter("seq"));

		System.out.println("boardId = "+ boardId +"name "+ request.getParameter("name")+request.getParameter("comment"));
		BoardComment boardComment = new BoardComment();
		boardComment.setName(request.getParameter("name"));
		boardComment.setComm(request.getParameter("comment"));

		boardService.insertComment(boardId, boardComment);
		
		ModelAndView mav = new ModelAndView();
		
//		mav.addObject("comments", boardService.commentList(seq));
//		System.out.println("Comments ="+boardService.commentList(seq));
		mav.setViewName("redirect:/read.html?seq=" + boardId);
		
		return mav;
	}

	@RequestMapping(value="/write.html")
	public ModelAndView write(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("write view");

		return new ModelAndView("write");

	}
							
	@RequestMapping(value="/writeok.html")
	public ModelAndView wirteok(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("쓰기 오케이");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String fileName ="ready";

		System.out.println("쓰기 "+name + title + password + content);
		Board board = new Board(name,password,title,content,fileName);
		System.out.println("board id = " +board.getBoardId());
		boardService.insertBoard(board);
		return new ModelAndView("redirect:/list");

	}

	// 게시글 수정페이지 이동
	@RequestMapping(value="/updatePage.html")
	public ModelAndView updatePageGo(HttpServletRequest request, HttpServletResponse response)throws Exception{

		System.out.println("게시글 받");
		String boardId = request.getParameter("seq");
		
		
		System.out.println("boardId = "+ boardId);
		
		return new ModelAndView("update", "board",boardService.readContent(Integer.parseInt(boardId)));

	}


	@RequestMapping(value="/updateComplete")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("updateComplete");

		String boardId = request.getParameter("seq");
		String password = request.getParameter("passwd");

		String title = request.getParameter("title");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		System.out.println("boardId = "+boardId +"name = "+name+",password = "+password+"title = "+title+"content = "+content);

		Board newBoard = new Board(name,title,content);
		newBoard.setBoardId(Integer.parseInt(boardId));

		boardService.updateBoard(newBoard);

		return new ModelAndView("redirect:/read.html?seq="+boardId);

	}
	
	@RequestMapping(value="/boardDelete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("board Delete");
		String boardId = request.getParameter("seq");
		System.out.println("seq = "+ boardId);
		boardService.deleteBoard(Integer.parseInt(boardId));
		return new ModelAndView("redirect:/list");
	}

}






