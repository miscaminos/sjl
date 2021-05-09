package com.model.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
	
	@Autowired
	private BoardDAO jpa;
	
	public void insert(BoardVO vo) {
		jpa.insertBoard(vo);
	}
	

}
