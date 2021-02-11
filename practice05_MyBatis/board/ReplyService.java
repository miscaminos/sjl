package com.encore.board;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.resources.Factory;

public class ReplyService {
	private SqlSessionFactory sqlSessionFactory;
	
	public ReplyService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public void insert(Reply r) {
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		mapper.insert(r);
		session.commit();
		session.close();
	}
	
	public Reply selectByNum(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		Reply r = mapper.selectByNum(num);
		session.close();
		return r;
	}
	
	public ArrayList<Reply> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		ArrayList<Reply> list = mapper.selectAll();
		session.close();
		return list;
	}
	
	public void update(Reply r) {
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		mapper.update(r);
		session.commit();
		session.close();
	}
	
	public void delete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		mapper.delete(num);
		session.commit();
		session.close();
	}
	
	public ArrayList<Integer> badReplyNum(){
		SqlSession session = sqlSessionFactory.openSession();
		ReplyMapper mapper = (ReplyMapper)session.getMapper(ReplyMapper.class);
		ArrayList<Integer> list = mapper.badReplyNum();
		session.close();
		return list;
	}
	//sql="select * from reply where comment like ?";
	
}
