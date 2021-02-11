package com.encore.board;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.resources.Factory;


public class BoardService {
	private SqlSessionFactory sqlSessionFactory;

	public BoardService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public void insert(Board b) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		mapper.insert(b);
		session.commit();
		session.close();
	}
	
	public Board selectByNum(int board_id) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		Board b = mapper.selectByNum(board_id);
		session.close();
		return b;
	}
	
	public ArrayList<Board> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		ArrayList<Board> list = mapper.selectAll();
		session.close();
		return list;
	}
	
	public void update(Board b) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		mapper.update(b);
		session.commit();
		session.close();
	}
	
	public void delete(int board_id) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		mapper.delete(board_id);
		session.commit();
		session.close();
	}
	
	public ArrayList<Integer> badBoardNum(){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = (BoardMapper)session.getMapper(BoardMapper.class);
		ArrayList<Integer> list = mapper.badBoardNum();
		session.close();
		return list;
	}
	//sql="select * from board where content like ? or title like ?";
	
	
}
