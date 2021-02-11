package com.encore.reservation;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.flight.FlightMapper;
import com.encore.resources.Factory;

public class BookedService {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public BookedService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public void insert(Booked b) {
		SqlSession session = sqlSessionFactory.openSession();
		BookedMapper mapper = (BookedMapper)session.getMapper(BookedMapper.class);
		mapper.insert(b);
		session.commit();
		session.close();
	}
	
	public Booked selectByNum(int bookingNum) {
		SqlSession session = sqlSessionFactory.openSession();
		BookedMapper mapper = (BookedMapper)session.getMapper(BookedMapper.class);
		Booked b = mapper.selectByNum(bookingNum);
		session.close();
		return b;
	}
	
	public ArrayList<Booked> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		BookedMapper mapper = (BookedMapper)session.getMapper(BookedMapper.class);
		ArrayList<Booked> list = mapper.selectAll();
		session.close();
		return list;
	}
	
	public void checkIn(Booked b) {
		SqlSession session = sqlSessionFactory.openSession();
		BookedMapper mapper = (BookedMapper)session.getMapper(BookedMapper.class);
		mapper.checkIn(b);
		session.commit();
		session.close();
	}
	
	public void delete(int bookingNum) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		mapper.delete(bookingNum);
		session.commit();
		session.close();
	}
	

}
