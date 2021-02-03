package com.encore.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.encore.dao.DaoPosting;
import com.encore.dao.DaoPostingImpl;
import com.encore.vo.Posting;


public class ServicePostingImpl implements ServicePosting {
	private DaoPosting daoPosting;
	
	public ServicePostingImpl() {
		daoPosting = new DaoPostingImpl();
	}
	
	@Override
	public void addBoard(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====글작성=====");
		
		if(ServiceImpl.getLogin_id() !=null) {
			
			Posting p = new Posting();
			String writer = ServiceImpl.getLogin_id();
			p.setWriter(writer);
			
			System.out.println("글제목(title): ");
			//이전에 입력되었던 입력내용의 "\n"이 입력buffer에 남아있기때문에, 
			//여기 nextLine()이 없다면, String title에 입력할 기회 없이 바로 다음 콘솔에 쓰여지는 문자가 들어가버림.
			sc.nextLine();
			String title = sc.nextLine();
			
			p.setTitle(title);
			
			StringBuilder sb = new StringBuilder(); 
			System.out.print("content를 작성하시오. (멈추려면 /end 입력)");
			
			//sc.nextLine();
			while(true) {
				String c = sc.nextLine(); 
				if(c.startsWith("/end")) {
					break;
				}
				sb.append(c+"\n"); //enter를 빼놓고 가져오기때문에 이렇게 붙여주어야함.
			}
			String content = sb.toString();
			p.setContent(content);
			daoPosting.insert(p);
		}
		else {
			System.out.println("로그인 후 작성해주세요.");
		}
	}

	@Override
	public void printAll(ArrayList<Posting> list) {
		// TODO Auto-generated method stub
		System.out.println("=====게시판 조회=====");
		for( Posting p: list)
			System.out.println(p);
		
	}

	@Override
	public void getByNum(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====게시판 검색(글번호)=====");
		System.out.println("검색할 글번호(number):");
		int num = sc.nextInt();
		
		Posting p = daoPosting.selectByNum(num);
		if(p== null) {
			System.out.println("없는 글번호");
		}
		else {
			System.out.println("검색결과:");
			System.out.println(p);
		}
			
		
	}

	@Override
	public ArrayList <Posting> getByWriter(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====게시판 검색(작성자)=====");
		System.out.println("검색할 글작성자(id):");
		String id = sc.next();
		ArrayList<Posting> list = daoPosting.selectByWriter(id);
		if(list.isEmpty()) {
			System.out.println("해당 작성자의 글이 없습니다.");
		}
		return list;
	}

	@Override
	public ArrayList <Posting> getByTitle(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====게시판 검색(제목)=====");
		System.out.println("검색할 단어:");
		String title = sc.next();
		ArrayList<Posting> list = daoPosting.selectByTitle(title);
		if(list.isEmpty()) {
			System.out.println("해당 제목의 글이 없습니다.");
		}
		return list;
	}

	@Override
	public void editBoard(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====글 수정=====");
		System.out.println("수정할 글번호 검색(number):");
		int num = sc.nextInt();
		
		boolean flag = checkMyPosting(num);
		if(flag) {
			System.out.println("글제목(title): ");
			sc.nextLine();
			String title = sc.nextLine();
			
			StringBuilder sb = new StringBuilder(); 
			System.out.print("content를 작성하시오. (멈추려면 /end 입력)");
			
			while(true) {
				String c = sc.nextLine(); 
				if(c.startsWith("/end")) {
					break;
				}
				sb.append(c+"\n"); //enter를 빼놓고 가져오기때문에 이렇게 붙여주어야함.
			}
			String content = sb.toString();
			Posting p = new Posting(num,"",null,title,content);
			daoPosting.update(p);
		}
		else {
			System.out.println("없는 글이거나 다시로그인이 필요합니다.");
		}
	}

	@Override
	public void delBoard(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("=====글 삭제=====");
		System.out.println("글번호(number):");
		int num = sc.nextInt();
		boolean flag = checkMyPosting(num);
		if(flag) {
			daoPosting.delete(num);
		}
		else {
			System.out.println("없는 글이거나 다시로그인이 필요합니다.");
		}
	}
	
	@Override
	public ArrayList<Posting> getAll() {
		// TODO Auto-generated method stub
		System.out.println("====글 전체목록====");
		ArrayList<Posting> list = daoPosting.selectAll();
		return list;
	}

	@Override
	public boolean checkMyPosting(int num) {
		// TODO Auto-generated method stub
		Posting p = daoPosting.selectByNum(num);
		if(p!=null && p.getWriter().equals(ServiceImpl.getLogin_id())) {
			return true;
		}
		else {
			return false;
		}
	}

}
