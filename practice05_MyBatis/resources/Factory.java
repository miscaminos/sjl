package com.encore.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@SuppressWarnings("unchecked")
public class Factory {
	 private static SqlSessionFactory sqlSessionFactory;
	 
	    static {
	        try {
	        	String resource = "com/encore/resources/config.xml";
	            Reader reader = Resources.getResourceAsReader(resource);
	 
	            if (sqlSessionFactory == null) {
	                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	             
	    			Class[] mapper1 = {com.encore.board.BoardMapper.class};
	    			for(Class b : mapper1){
	    				sqlSessionFactory.getConfiguration().addMapper(b);
	    			}  
	    			Class[] mapper2 = {com.encore.flight.FlightMapper.class};
	    			for(Class f : mapper2){
	    				sqlSessionFactory.getConfiguration().addMapper(f);
	    			}
	    			Class[] mapper3 = {com.encore.member.MemberMapper.class};
	    			for(Class m : mapper3){
	    				sqlSessionFactory.getConfiguration().addMapper(m);
	    			}
	    			Class[] mapper4 = {com.encore.reservation.BookedMapper.class};
	    			for(Class bk : mapper4){
	    				sqlSessionFactory.getConfiguration().addMapper(bk);
	    			}
	    			Class[] mapper5 = {com.encore.board.ReplyMapper.class};
	    			for(Class re : mapper5){
	    				sqlSessionFactory.getConfiguration().addMapper(re);
	    			}
	            }
	        }
	        catch (FileNotFoundException fileNotFoundException) {
	            fileNotFoundException.printStackTrace();
	        }
	        catch (IOException iOException) {
	            iOException.printStackTrace();
	        }
	    }
	    public static SqlSessionFactory getSqlSessionFactory() {
	        return sqlSessionFactory;
	    }
}
