package com.encore.dao;

import com.encore.vo.Member;

//db작업만 한다. 여기에서 구현한 db연동 작업은 service에서 호출해다가 서비스 제공에 사용한다.
public interface Dao {
	//service의  회원가입 기능에 필요: service에서 id, pwd, name, email을 Member 객체에 담아서 insert()를  호출 ==> db에 저장하려고
	void insert(Member m);
	
	//service의 login, 내정보 확인기능에 필요:
	Member select(String id);
	
	//service의 내정보수정
	void update(String id, String new_pwd);
	
	//service의 탈퇴.
	void delete(String id);
}
