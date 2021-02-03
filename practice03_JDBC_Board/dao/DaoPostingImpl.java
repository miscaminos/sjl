package com.encore.dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import com.encore.connect.DbConnect;
import com.encore.vo.Posting;

public class DaoPostingImpl implements DaoPosting {

	private DbConnect db;
	
	public DaoPostingImpl() {
		db = DbConnect.getInstance();
	}
	
	@Override
	public void insert(Posting b) {
		// TODO Auto-generated method stub
		Connection conn = db.conn(); //db연결
		int cnt = 0;
		String sql = "insert into board values(seq_board.nextval,?,sysdate,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1,b.getNum());대신 sequence를 사용한다! <-이미 seq_board생성해두었기때문에 바로 seq사용가능
			pstmt.setString(1,b.getWriter());
			pstmt.setString(2,b.getTitle());
			pstmt.setString(3,b.getContent());
			cnt = pstmt.executeUpdate();
			System.out.println(cnt+"줄 insert됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Posting> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Posting> list = new ArrayList<Posting>();
		Connection conn = db.conn();
		ResultSet rs = null;
		String sql = "select * from board order by num";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//sql에 매칭할 ?가 없으니 매칭 없이 바로 실행
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt(1);
				String writer = rs.getString(2);
				Date w_Date = rs.getDate(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
	
				Posting p = new Posting(num, writer, w_Date, title, content);
				list.add(p);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Posting selectByNum(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		String sql = "select * from board where num =?";
		ResultSet rs = null;
		Posting p = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();//rs: 검색한 결과. 1줄 검색되거나 없거나
			if(rs.next()) {//검색한 한줄이 있냐? 있으면 true, 없으면 false
				/*p.setNum(rs.getInt(1));
				p.setWriter(rs.getString(2));
				p.setW_date(rs.getDate(3));
				p.setTitle(rs.getString(4));
				p.setContent(rs.getString(5));*/
				p = new Posting(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public ArrayList<Posting> selectByWriter(String writer) {
		// TODO Auto-generated method stub
		ArrayList<Posting> list = new ArrayList<Posting>();
		Connection conn = db.conn();
		ResultSet rs = null;
		String sql = "select * from board where writer=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt(1);
				
				Date w_Date = rs.getDate(3);
				String title = rs.getString(4);
				String content = rs.getString(5);
	
				Posting p = new Posting(num, writer, w_Date,title, content);
				list.add(p);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;	
	}

	@Override
	public ArrayList<Posting> selectByTitle(String title) {
		// TODO Auto-generated method stub
		ArrayList<Posting> list = new ArrayList<Posting>();
		Connection conn = db.conn();
		ResultSet rs = null;
		String sql = "select * from board where title like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt(1);
				String writer = rs.getString(2);
				Date w_Date = rs.getDate(3);
				String full_title = rs.getString(4);
				String content = rs.getString(5);
	
				Posting p = new Posting(num, writer, w_Date,full_title, content);
				list.add(p);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;	
	}

	@Override
	public void update(Posting b) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		int cnt =0;
		String sql = "update board set title='"+b.getTitle()+"', content='"+b.getContent()+"', w_date=sysdate where num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getNum());
			//또는 
			//String sql = "update board set title=?, content=?, w_date=sysdate where num=?";
			//pstmt.setString(1, b.getTitle());
			//pstmt.setString(2, b.getContent());
			//pstmt.setInt(3, b.getNum());
			
			cnt = pstmt.executeUpdate();
			System.out.println(cnt+"줄 update됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		Connection conn = db.conn();
		int cnt=0;
		String sql = "delete board where num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			cnt = pstmt.executeUpdate();
			System.out.println(cnt+"줄 delete됨");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

}
