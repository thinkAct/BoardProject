package com.mmventures.study.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mmventures.study.dto.Board;
import com.mmventures.study.dto.BoardComment;
/**
 * Control Board tasks.
 * @author heechanglee
 *
 */
@Repository
public class BoardDAO {

	/**
	 * sessionFactory is declared as bean in ApplicationContextConfig. 
	 */
	@Autowired
	private SessionFactory sessionFactory;

//	public final SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}

//	public final void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	/**
	 * read main board.
	 * @return List<Board>
	 */
	public final List<Board> boardList() {
		// TODO Auto-generated method stub
		System.out.println("boardList");
		List<Board> boardList = new ArrayList<Board>();

		Session session = this.sessionFactory.openSession();

		System.out.println("session success");

		try {

			System.out.println("query before");
			boardList = (List<Board>) session.createQuery(
					"from Board where isDeleted = ? ")
					.setParameter(0, new Boolean(false))
					.list();

			System.out.println("query after");
		} finally {
			session.close();
		}


		if (boardList.size() > 0) {
			System.out.println("size = " + boardList.size());
			return boardList;
		} else {
			return null;
		}
	}
	
	
	
	/**
	 */
//	public List<Board> loadBoardContent(int seq){
//		System.out.println("loadBoardContent");
//		List<Board> boardList = new ArrayList<Board>();
//
//		Session session = this.sessionFactory.openSession();
//
//		System.out.println("session success");
//
//		try {
//
//			System.out.println("query before");
//			boardList = (List<Board>) session.createQuery(
//					"from Board where seq = ? ")
//					.setParameter(0, new Integer(seq))
//					.list();
//
//			System.out.println("query after");
//		} finally {
//			session.close();
//		}
//
//
//		if (boardList.size() > 0) {
//			System.out.println("size = " + boardList.size());
//			return boardList;
//		} else {
//			return null;
//		}
//	}

	/**
	 * read selected board content.
	 * @param seq int selected board id.
	 * @return Board return Board instance.
	 */
	//예제에서는 파라미터가 string 이었음 
	public final Board readContent(final int seq) {

		List<Board> boardList = new ArrayList<Board>();

		Session  session = this.sessionFactory.openSession();
		try {

			boardList = (List<Board>)session.createQuery("from Board where id =?").
					setParameter(0, seq).list();			

		} finally {

			session.close();

		}


		if (boardList.size() > 0) {

			this.updateReadCount(seq,boardList.get(0).getReadCount());

			return boardList.get(0);

		} else {

			return null;
		}
	}

	/**
	 * update readCount information.
	 * @param seq int this seq info point selected board.
	 */
	//예제에서는 파라미터가 string 이었음
	public final void updateReadCount(final int seq, final int count){
		Session session = sessionFactory.openSession();
		
		
		
		//board의 visit 값을 가져와야한다.
		Integer visitCount =  new Integer(count + 1);
		
		try {
			
			Query query = session.createQuery(
					"update Board set readCount = :count where seq =:seq ");
				query.setParameter("count", visitCount);
				query.setParameter("seq", new Integer(seq));
				
			System.out.println("count ="+visitCount);
			
			int result = query.executeUpdate();
			
		} catch( HibernateException he) {
			System.out.println("read count Hibernate Exception");
		} finally {
			if (session != null) {
				session.close();				
			}
		}
		//업데이트/삭제 엔티티 개수를  리턴


	}

//	public void insertCommentIntoBoard(final int boardSeq,final BoardComment comment){
//
//		Board currentBoard = this.findBoard(boardSeq);
//		
//		
//		
//		
//		if (currentBoard.getComment() == null) {
//			
//			System.out.println("getComment is null");
//			
//			List<BoardComment> comments =  new ArrayList<BoardComment>();
//			
//			currentBoard.addComment(comment);
//			
////			currentBoard.setComment(new ArrayList<BoardComment>());
//		} else {
//			
//			currentBoard.addComment(comment);
//			
//		}
//
//
//		Session session = this.sessionFactory.openSession();
//		session.beginTransaction();
//
//		try {
//			session.save(currentBoard);		
//			session.getTransaction().commit();
//
//		} catch (HibernateException he) {
//
//			System.out.println("Hibernate data store exception in insertCommentIntoBoard");
//
//			session.getTransaction().rollback();
//
//		} finally {
//
//			if(session != null) {
//
//				session.close();
//
//			}
//		}
//	}	



	private Board findBoard(int boardSeq) {
		List<Board> board = new ArrayList<Board>();
		Session session = this.sessionFactory.openSession();

		try {

			board = session.createQuery("from Board where id =?").
					setParameter(0, new Integer(boardSeq)).list();

		} finally {
			session.close();
		}

		if (board.size() > 0) {

			System.out.println("specific board is selected");

			return board.get(0);
		} else {
			return null;
		}
	}

	/**
	 * insert Comment into board.
	 * @param comment BoardComment instance is returned.
	 */
	public void insertComment(final int boardSeq, final BoardComment comment) {

		BoardComment newComment = new BoardComment(
				comment.getName(), comment.getComm());

		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		try {

			session.save(newComment);			
			session.getTransaction().commit();

		} catch(HibernateException he) {

			System.out.println("Hibernate data store exception in insertComment");

			session.getTransaction().rollback();

		} finally {

			if(session != null) {

				session.close();

			}
		}

		//세션 관리가 이루어져야하는 부분.
//		insertCommentIntoBoard(boardSeq, newComment);

	}

	/**
	 * list comment in specific board.
	 * @param seq int
	 * @return ArrayList<BoardComment>
	 */
//	public List<BoardComment> commentList (int seq){
//
//
//		List<Board> board = new ArrayList<Board>();
//		Session session = this.sessionFactory.openSession();
//		try {
//
//			board  = session.createQuery("from Board where seq=?").setParameter(0, new Integer(seq)).list();
//
//		} finally {			
//
//			session.close();
//
//		}
//
//
//		if(board.size() > 0 ){
//
//			return board.get(0).getComment();
//
//		}else{
//
//			return null;
//		}
//
//	}



	public void insertBoard(Board board){
		Date now = new Date();
		Board newBoard = new Board();

		newBoard.setName(board.getName());
		newBoard.setpassword(board.getpassword());
		newBoard.setTitle(board.getTitle());
		newBoard.setContent(board.getContent());
		newBoard.setFileName(board.getFileName());
		newBoard.setRegdate(now);

		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(newBoard);
			session.getTransaction().commit();
			System.out.println("new board commit is completed.");
		} catch(HibernateException he) {
			System.out.println("Hibernate data store exception in insertBoard");

			session.getTransaction().rollback();
		} finally {
			if (session != null) {

				session.close();

			}
		}

	}
	
	public void updateBoard(Board board){
		Board newBoard = new Board();
		newBoard.setTitle(board.getTitle());
		newBoard.setContent(board.getContent());
		
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(
				"update Board set title = :title "
				+ ", content = :content  where seq= :seq");

		query.setParameter("title", board.getTitle());
		query.setParameter("content", board.getContent());
		query.setParameter("seq",
				new Integer(board.getSeq()));
		
		
		//업데이트/삭제 엔티티 개수를  리턴
		int result = query.executeUpdate();
		
		session.close();
	}
	
	/**
	 * This board is actually not deleted. just toggle isDelete variable.
	 * @param seq int board sequence.
	 */
	public void deleteBoard(int seq){
		
		
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(
				"update Board set isDeleted =:deleted "
				+ " where seq =:seq");

		
		query.setParameter("deleted", new Boolean(true));
		query.setParameter("seq",seq);
		
		
		//업데이트/삭제 엔티티 개수를  리턴
		int result = query.executeUpdate();
		System.out.println("board is deleted");
		
		session.close();
	}
	
	
}
