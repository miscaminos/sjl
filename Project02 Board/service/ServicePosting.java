package com.encore.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.encore.vo.Posting;


public interface ServicePosting {
	void addBoard(Scanner sc);//글작성
	void printAll(ArrayList<Posting> list);//글목록(번호, 제목, 작성자)
	void getByNum(Scanner sc);//글 번호 입력받아 검색
	ArrayList<Posting> getByWriter(Scanner sc);//작성자 입력받아 작성자로 검색
	ArrayList<Posting> getByTitle(Scanner sc);//제목 일부 입력받아 제목으로 검색
	void editBoard(Scanner sc);//번호, 새제목, 새내용 입력받아 수정
	void delBoard(Scanner sc);//글 번호 입력받아 삭제
	ArrayList<Posting> getAll(); 
	boolean checkMyPosting(int num);
}
