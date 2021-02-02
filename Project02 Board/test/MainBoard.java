package com.encore.test;

import java.util.Scanner;

import com.encore.dao.Dao;
import com.encore.dao.DaoImpl;
import com.encore.vo.Member;

public class MainBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Dao dao = new DaoImpl();
		//dao.insert(new Member("aaa","111","name","email@gmail.com"));
		//System.out.println(dao.select("aaa"));
		
		Scanner sc = new Scanner(System.in);
		Menu m = new Menu();
		m.run(sc);
	}

}
