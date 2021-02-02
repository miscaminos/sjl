package com.encore.service;

import java.util.Scanner;
import com.encore.dao.Dao;
import com.encore.dao.DaoImpl;
import com.encore.vo.Member;

//이 프로그램이 외부에 제공할 기능들 정의한다:
//로그인, 로그아웃, 등등 
//예) 로그인 기능을 여기 service에서 구현하되, 
//member 테이블에서부터 id,pwd 정보를 가져오는 일은 dao에서 구현한다.
public class ServiceImpl implements Service {
	
	private Dao dao;
	private static String Login_id = null; //null:로그인안된 상태, 값이 있으면: 로그인된 상태
	
	public ServiceImpl() {
		dao = new DaoImpl();
	}

	public static String getLogin_id() {
		return Login_id;
	}

	public static void setLogin_id(String login_id) {
		Login_id = login_id;
	}

	@Override
	public void addMember(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====회원가입=====");
		//회원가입에 필요한 정보 입력
		String id="";
		Member m = null;
		do {
			System.out.println("id: ");
			id = sc.next();
			m = dao.select(id);			
		}while(m!=null);
		
		System.out.println("pwd: ");
		String pwd = sc.next();
		System.out.println("name: ");
		String name = sc.next();
		System.out.println("email: ");
		String email = sc.next();
		
		//dao의 insert로 입력한 정보를 영구저장
		dao.insert(new Member(id, pwd, name, email));
	}

	@Override
	public void login(Scanner sc) {
		// TODO Auto-generated method stub
		//이미 로그인중인지 확인
		if(Login_id != null) {
			System.out.println("이미 로그인된 상태입니다.");
			return;
		}
		else {
			System.out.println("=====로그인=====");
			System.out.println("id: ");
			String id = sc.next();
			System.out.println("pwd: ");
			String pwd = sc.next();
			
			Member m = dao.select(id);
			if(m==null) {
				System.out.println("없는 아이디");
			}
			else {
				if(m.getPwd().equals(pwd)) {
					System.out.println("로그인 성공");
					Login_id = id;
				}
				else {
					System.out.println("패스워드 불일치");
				}
			}	
		}
	}

	@Override
	public void logout(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====로그아웃=====");
		if(loginCheck()) {
			Login_id = null;
		}
		else {
			System.out.println("로그인 먼저 해주세요");
		}
	}

	@Override
	public boolean myInfo() {
		// TODO Auto-generated method stub
		System.out.println("=====내정보 조회=====");
		if(loginCheck()) {
			Member m = dao.select(Login_id);
			System.out.println(m);
			return true;
		}
		else {
			System.out.println("로그인 먼저 해주세요");
			return false;
		}
		
	}

	@Override
	public void editInfo(Scanner sc) {
		// TODO Auto-generated method stub
		boolean flag = myInfo();
		if(flag) {
			System.out.println("=====내정보 수정=====");
			System.out.println("new pwd: ");
			String new_pwd = sc.next();
			dao.update(Login_id, new_pwd);
		}
		
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		System.out.println("=====탈퇴=====");
		if(loginCheck()) {
			dao.delete(Login_id);
			Login_id = null; //탈퇴 후 로그아웃처리
		}
		else {
			System.out.println("로그인 먼저 해주세요");
		}
	}
	

	@Override
	public boolean loginCheck() {
		// TODO Auto-generated method stub
		if(Login_id==null) {
			return false;
		}
		else {
			return true;
		}
	}

}
