package com.encore.member;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.resources.Factory;


public class MemberService {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public MemberService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public boolean insert(Member m){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		boolean inserted = true;
		inserted = mapper.insert(m);
		session.commit();
		session.close();
		return inserted;
	}
	
	public Member selectById(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		Member m = mapper.selectById(id);
		session.close();
		return m;
	}
	
	public ArrayList<Member> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		ArrayList<Member> list = mapper.selectAll();
		session.close();
		return list;
	}
	
	public void update(Member m){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		mapper.update(m);
		session.commit();
		session.close();
	}
	
	public void delete(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		mapper.delete(id);
		session.commit();
		session.close();
	}
	
	public ArrayList <String> unusedId(){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		ArrayList<String> list = mapper.unusedId();
		session.close();
		return list;
	}
	//sql = "select id from encore_member where months_between(sysdate, lastAccessDate)>?"
}


