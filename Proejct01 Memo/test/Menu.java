package com.encore.test;
//Memojang 메뉴를 정의한 클래스

import java.util.Scanner;
import com.encore.service.MemoService;
//import com.encore.vo.Memo;
//import java.util.ArrayList;

public class Menu {
	private MemoService service;

	
	public Menu() {
		service = new MemoService();
	}
	
	public void run(Scanner sc) {
		boolean flag = true;
		
		while(flag) {
			service.createDir();
			System.out.println("1.Memo조회  2.새로운Memo쓰기  3.Directory관리  4.Exit");
			int m = sc.nextInt();
			switch(m) {
			case 1:
				run_viewFiles(sc);
				break;
			case 2:
				run_writeNew(sc);
				break;
			case 3:
				run_DirManage(sc);
				break;
			case 4: 
				flag=false;
				break;
			}
		}
	}
	
	
	public void run_viewFiles(Scanner sc) {
		if(!service.createDir()) {
			System.out.println("Directory가 비어있습니다.");
			return;
		}
		else {
			System.out.println("Directory에 저장된 Memo 파일들입니다:");
			for (String s: service.viewAll())
				System.out.println(s);
			System.out.println("조회하고 싶은 Memo title을 입력하세요: ");
			String fileTitle = sc.next();
		
			boolean flag = true;
			while(flag) {
				System.out.println("1.Memo읽기  2.Memo이어쓰기  3.Exit");
				int m = sc.nextInt();
				switch(m) {
				case 1:
					service.readMemo(fileTitle);
					break;
				case 2:
					System.out.println("수정 날짜를 입력해주세요: ");
					String editedDate = sc.next();
					service.writeMemo(fileTitle, true);
					service.saveMemo(fileTitle, editedDate);
					System.out.println(fileTitle+"저장 완료");
					break;
				case 3:
					flag=false;
					break;
				}
			}
		}
	}
	
	public void run_writeNew(Scanner sc) {
		//boolean flag = true;
		service.createDir();
		
		System.out.println("작성 날짜를 입력해주세요: ");
		String editedDate = sc.next();
		System.out.println("Memo title을 입력해주세요: ");
		String fileTitle = sc.next();
		boolean flag = true;
		String newName = null;
		
		while(flag) {
			if(!service.searchMemo(fileTitle)) {
				service.saveMemo(fileTitle, editedDate);
				flag=false;
				break;
			}
			else {
				System.out.println("다른 Memo title을 입력해주세요:");
				newName = sc.next();
				fileTitle = newName;
			}
		}
		System.out.println("내용을 입력해주세요: ");
		service.writeMemo(fileTitle, false);	
	}

	public void run_DirManage(Scanner sc) {	
		if(!service.createDir()) {
			System.out.println("Directory가 비어있습니다.");
			return;
		}
		else {
			System.out.println("Directory에 저장된 Memo 파일들입니다:");
			for (String s: service.viewAll())
				System.out.println(s);
			boolean flag = true;
			while(flag) {
				System.out.println("1.Memo삭제  2.Memo복사본 생성하기  3.Memo파일zip하기 4.Exit");
				int m = sc.nextInt();
				switch(m) {
				case 1:
					System.out.println("삭제할 Memo title을 입력하세요: ");
					String fileTitle = sc.next();
					service.deleteMemo(fileTitle);
					System.out.println(fileTitle+" 삭제 완료");
					break;
				case 2:
					System.out.println("copy할 Memo title을 입력하세요: ");
					String copyFrom = sc.next();
					System.out.println("생성될  Memo title을 입력해주세요:");
					String copyTo = sc.next();
					service.copyMemo(copyFrom, copyTo);
					System.out.println(copyFrom+"의 복사본 생성 완료. 새로 생성된 파일은 "+copyTo+"입니다.");
					break;
				case 3:
					System.out.println("Directory에 저장된 Memo 파일들입니다:");
					for (String s: service.viewAll())
						System.out.println(s);
					System.out.println("zip하려는 파일 갯수:");
					int num = sc.nextInt();
					System.out.println("생성할 zip파일 이름:");
					String zipFileName = sc.next();
					System.out.println("zip하려는 파일이름을 하나씩 입력하세요: ");
					String[] paths = new String[num];
					for(int i=0; i<num; i++) {
						String input = sc.next();
						paths[i] = "src/com/encore/vo/memoDir/"+input;
					}
					service.zipFiles(paths, zipFileName);
					System.out.println();
					break;
				case 4:
					flag=false;
					break;				
				}
			}
		}
	}

	
}
