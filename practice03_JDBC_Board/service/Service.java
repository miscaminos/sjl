package com.encore.service;

import java.util.Scanner;

//이 프로그램이 외부에 제공할 기능들 정의한다:
//로그인, 로그아웃, 등등 
//예) 로그인 기능을 여기 service에서 구현하되, 
//member 테이블에서부터 id,pwd 정보를 가져오는 일은 dao에서 구현한다.
public interface Service {
	
	void addMember(Scanner sc);
	void login(Scanner sc);
	void logout(Scanner sc);
	boolean myInfo();
	void editInfo(Scanner sc);
	void out();
	boolean loginCheck();
}
