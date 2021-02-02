package com.encore.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.encore.service.Service;
import com.encore.service.ServiceImpl;
import com.encore.service.ServicePosting;
import com.encore.service.ServicePostingImpl;
import com.encore.vo.Posting;

public class Menu {
	private Service service;
	private ServicePosting servicePosting;
	 
	public Menu() {
		service = new ServiceImpl();
		servicePosting = new ServicePostingImpl();
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		int menu = 0;
		while(flag) {
			System.out.println("1. 회원관리   2. 게시판   3. 종료");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				member_run(sc);
				break;
			case 2: 
				board_run(sc);
				break;
			case 3:
				flag = false;
				break;
			}
		}
	}
	
	public void member_run(Scanner sc) {
		boolean flag = true;
		int menu = 0;
		while(flag) {
			System.out.println("1.회원가입 2.로그인 3.내정보확인 4.내정보수정 5.로그아웃 6.탈퇴 7.종료");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				service.addMember(sc);
				break;
			case 2:
				service.login(sc);
				break;
			case 3:
				service.myInfo();
				break;
			case 4:
				service.editInfo(sc);
				break;
			case 5:
				service.logout(sc);
				break;
			case 6:
				service.out();
				break;
			case 7:
				flag = false;
				break;
			}
		}
	}
	
	public void board_run(Scanner sc) {
		boolean flag = true;
		int menu = 0;
		ArrayList<Posting> list = null;
		while(flag) {
			System.out.println("1.글작성 2.게시판 조회 3.검색(글번호) 4.검색(작성자) 5.검색(제목) 6.글수정 7.글삭제 8.종료");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				servicePosting.addBoard(sc);
				break;
			case 2:
				list = servicePosting.getAll();
				servicePosting.printAll(list);
				break;
			case 3:
				servicePosting.getByNum(sc);
				break;
			case 4:
				list = servicePosting.getByWriter(sc);
				servicePosting.printAll(list);
				break;
			case 5:
				list = servicePosting.getByTitle(sc);
				servicePosting.printAll(list);
				break;
			case 6:
				servicePosting.editBoard(sc);
				break;
			case 7:
				servicePosting.delBoard(sc);
				break;
			case 8:
				flag = false;
				break;
			}
		}
	}
	
	
	
}
