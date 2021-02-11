package com.encore.flight;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.encore.resources.Factory;

public class FlightService {

	private SqlSessionFactory sqlSessionFactory;
	
	public FlightService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public void insert(Flight f) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		mapper.insert(f);
		session.commit();
		session.close();
	}
	
	public Flight selectByNum(String flight_num) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		Flight f = mapper.selectByNum(flight_num);
		session.close();
		return f;
	}
	
	public ArrayList<Flight> selectByDepartCity(String city) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		ArrayList<Flight> f = mapper.selectByDepartCity(city);
		session.close();
		return f;
	}
	public ArrayList<Flight> selectByArriveCity(String city) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		ArrayList<Flight> f = mapper.selectByArriveCity(city);
		session.close();
		return f;
	}
	
	public ArrayList<Flight> selectByWorkingDate(String day) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		ArrayList<Flight> f = mapper.selectByWorkingDate(day);
		session.close();
		return f;
	}
	
	public ArrayList<Flight> selectAll(){
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		ArrayList<Flight> list = mapper.selectAll();
		session.close();
		return list;
	}
	
	public void update(Flight f) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		mapper.update(f);
		session.commit();
		session.close();
	}
	
	public void delete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		FlightMapper mapper = (FlightMapper)session.getMapper(FlightMapper.class);
		mapper.delete(num);
		session.commit();
		session.close();
	}

	
	
	
	
}
