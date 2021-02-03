package com.encore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.encore.connect.DbConnect;
import com.encore.vo.Member;

//db작업만 한다.여기에서 구현한 db연동 작업은 service에서 호출해다가 서비스 제공에 사용한다.
public class DaoImpl implements Dao {
	
	private DbConnect db;
	
	public DaoImpl() {
		db = DbConnect.getInstance();
	}
	
	@Override
	public void insert(Member m) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		String sql = "insert into members values(?,?,?,?)";
		//String 
		int cnt =0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId()); //만약 넣어주어야하는 값이 정수라면, setInt(), 실수라면 setFoat()
			pstmt.setString(2, m.getPwd()); //insert하려는 column의 type에 맞게끔 작성
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			
			//sql 실행::
			//executeUpdate(): 쓰기 작업(insert, update, delete)실행==> 적용된 row수 반환(int)
			//executeQuery(): 읽기 작업(select)실행==> 검색결과 반환 (ResultSet)
			cnt = pstmt.executeUpdate(); 
			System.out.println(cnt+"줄 insert 됨.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {//정상 처리가되든 안되는 close해야하기때문에 finally문을 사용
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Member select(String id) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		String sql = "select * from members where id=?";
		ResultSet rs = null; //검색 결과 담을 변수
		Member m = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery(); //1줄 검색되거나 없거나
			if(rs.next()){ //검색한 한줄이 있냐? 있으면 true, 없으면 false
	
				m = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {//정상 처리가되든 안되는 close해야하기때문에 finally문을 사용
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	//만약 여러줄이 검색된다면, 아래와 같이 while(rs.next()) 사용
	/*
	ArrayList<Member> list = new ArrayList<Member>();
	Result rs = pstmt.executeQuery();
	while(rs.next()){
		Member m = new Member(rs.getString(1), rs.getString(2),....)
		list.add(m);
	}
	return list;
	*/
	
	@Override
	public void update(String id, String new_pwd) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		String sql = "update members set pwd=? where id=?";
		
		int cnt =0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//물음표 매칭
			pstmt.setString(1, new_pwd);
			pstmt.setString(2, id); 
			//sql 실행
			cnt = pstmt.executeUpdate(); 
			System.out.println(cnt+"줄 update 됨.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		String sql = "delete members where id=?";
		
		int cnt =0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//물음표 매칭
			pstmt.setString(1, id);
			//sql실행
			cnt = pstmt.executeUpdate(); 
			System.out.println(cnt+"줄 delete 됨.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {//정상 처리가되든 안되는 close해야하기때문에 finally문을 사용
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
