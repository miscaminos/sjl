package com.encore.dao;

import java.util.ArrayList;

import com.encore.vo.Posting;

public interface DaoPosting {
	void insert(Posting b);
	ArrayList<Posting> selectAll();
	Posting selectByNum(int num);
	ArrayList<Posting> selectByWriter(String writer);
	ArrayList<Posting> selectByTitle(String title);
	void update(Posting b);//번호로 찾아서 날짜, 제목, 내용 수정
	void delete(int num);
}
