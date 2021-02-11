package com.encore.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.encore.board.Board;
import com.encore.board.BoardService;
import com.encore.board.Reply;
import com.encore.board.ReplyService;
import com.encore.flight.Flight;
import com.encore.flight.FlightService;
import com.encore.member.Member;
import com.encore.member.MemberService;
import com.encore.reservation.Booked;
import com.encore.reservation.BookedService;


public class Menu {
	
	MemberService memberService;
	FlightService flightService;
	BookedService bookedService;
	BoardService boardService;
	ReplyService replyService;
	
	public Menu() {
		
		memberService = new MemberService();
		flightService = new FlightService();
		bookedService = new BookedService();
		boardService = new BoardService();
		replyService = new ReplyService();
	}
	
	public void startMenu(Scanner sc) {
		boolean flag = true;
		while(flag) {
			System.out.println("1.Login 2.SignUp 3.Exit");
			int input = sc.nextInt();
			switch(input) {
				case 1:
					flag=loginMenu(sc);//if done with login+c/e/a's menu loginMenu()=true
					break;
				case 2:
					flag=signUpMenu(sc);//if successfully inserted signUpMenu()=true
					break;
				case 3:
					flag=false;
					break;	
			}
		}
	}
	
	public boolean loginMenu(Scanner sc) {
		System.out.println("=====1.Login====");
		Member m = new Member();
		boolean gate = true;
		
		while(gate) {
			System.out.println("id:");
			String id= sc.next();
			System.out.println("password:");
			String pwd= sc.next();
			m = memberService.selectById(id);
			
			if(m!=null && m.getPassword().equals(pwd)) {
				System.out.println("Login success");
				String type = m.getType();
				switch(type) {
				case "c":
					customerMenu(sc,m);
					break;
				case "e":
					employeeMenu(sc,m);
					break;
				case "a":
					adminMenu(sc,m);
					break;
				}
				gate=false;
				break;
			}
			else if(m!=null && !m.getPassword().equals(pwd)){
				System.out.println("Wrong password. Try again");
			}
			else {
				System.out.println("Member info does not exist. Please signup");
				gate=false;
				break;
			}
		}
		//after using the loginMenu(), return true	
		return true;
	}
		 
	
	public boolean signUpMenu(Scanner sc) {
		System.out.println("=====2.Signup====");
		System.out.println("id:");
		String id=sc.next();
		System.out.println("name:");
		String name=sc.next();
		System.out.println("password:");
		String password=sc.next();
		System.out.println("passport number:");
		String passportnum=sc.next();
		System.out.println("member type(choose: c[customer],e[employee],a[admin]):");
		String type=sc.next();
		
		Member m = new Member(id,name,password,passportnum,type,null);
		boolean signedup = false;
		signedup = memberService.insert(m);//if successfully inserted signedup=true
		return signedup;
		/*if want date input:
		String d=sc.next();
		Date date = Date.valueOf(d); 
		OR
		LocalDate dt = LocalDate.parse(d); in 2021-02-06 format
		Date date= Date.valueOf(dt);*/
	}
	
	public void customerMenu(Scanner sc, Member m) {
		System.out.println("=====Customer Menu====");
		String id = m.getId();
		boolean flag = true;
		while(flag) {
			System.out.println("1.Search tickets 2.Make reservation 3.My reservation 4.Check-in 5.Board 6.MyInfo 7.Exit");
			int type =sc.nextInt();
			
			switch(type){
			case 1://Search tickets
				System.out.println("===1.Search tickets===");
				System.out.println("show all flight tickets:");
				ArrayList<Flight> listAll= flightService.selectAll();
				for(Flight f: listAll) {
					System.out.println(f);
				}
				System.out.println("search by flight num:");
				String num = sc.next();
				Flight f = flightService.selectByNum(num);
				System.out.println(f);
				
				System.out.println("search by depart city:");
				String dCity = sc.next();
				ArrayList<Flight> listByDepartCity = flightService.selectByDepartCity(dCity);
				for(Flight d: listByDepartCity) {
					System.out.println(d);
				}
				
				System.out.println("search by arrival city:");
				String aCity = sc.next();
				ArrayList<Flight> listByArrivalCity = flightService.selectByArriveCity(aCity);
				for(Flight a: listByArrivalCity) {
					System.out.println(a);
				}
				
				System.out.println("search by flying day:");
				String day = sc.next();
				ArrayList<Flight> listByDay = flightService.selectByWorkingDate(day);
				for(Flight w: listByDay) {
					System.out.println(w);
				}
				break;
				
			case 2://reservation
				System.out.println("===2.Make reservation===");
				boolean res = true;
				while(res) {
					System.out.println("flight number:");
					String flight_num = sc.next();
					Flight chosen_flight = flightService.selectByNum(flight_num);
					
					int seat_cnt = chosen_flight.getSeat_count();
					
					//check if there is any seat left
					if(seat_cnt<=0) {
						System.out.println("The selected flight is sold out. Please choose another one.");
					}
					else {
						//insert into DB encore_booked
						Booked b = new Booked(0, 'n', flight_num, id);
						bookedService.insert(b);
						//update seat_count of DB encore_tickets
						int new_seat_cnt = chosen_flight.getSeat_count()-1;
						chosen_flight.setSeat_count(new_seat_cnt);
						flightService.update(chosen_flight);
						System.out.println("Reservation complete");
						res=false;
						break;
					}
				}
				break;
				
			case 3://update reservation
				System.out.println("===3.My reservation===");
				System.out.println("booking number:");
				int bookingNum = sc.nextInt();
				Booked  bk = bookedService.selectByNum(bookingNum);
				System.out.println(bk);
				System.out.println("choose to delete? yes/no:");
				String answer = sc.next();
				if(answer.equals("yes")) {
					bookedService.delete(bookingNum);
					System.out.println("delete complete");
				}
				break;
				
			case 4://check in 
				System.out.println("===4.Check-in===");
				System.out.println("booked_num:");
				int booked_num = sc.nextInt();
				Booked  booked = bookedService.selectByNum(booked_num);
				booked.setState('y'); //update state=y to check-in
				bookedService.checkIn(booked);
				System.out.println("check-in complete");
				break;
				
			case 5://board 
				System.out.println("===5.Board===");
							
				Board post = new Board();
				post.setWriter(id);
				System.out.println("Title: ");
				sc.nextLine();
				String title = sc.nextLine();
				post.setTitle(title);
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
				post.setContent(content);
				boardService.insert(post);
				
				ArrayList<Board> board_list = boardService.selectAll();
				for(Board pt: board_list) {
					System.out.println(pt);
				}
				break;
				
			case 6://myinfo
				System.out.println("===6.My Info===");
				System.out.println(m);
				System.out.println("edit my info");
				System.out.println("password:");
				String pwd = sc.next();
				System.out.println("passport number:");
				String ps_num = sc.next();
				Member me = new Member(id, null, pwd, ps_num, null, null);
				memberService.update(me);
				
				System.out.println("delete my info");
				memberService.delete(id);
				if(memberService.selectById(id)==null) {
					System.out.println("delete complete");
				}
				System.out.println();
				flag=false;
				break;
				
			case 7://exit
				flag=false;
				break;
			}
			
		}
	}
	
	public void employeeMenu(Scanner sc, Member m) {
		System.out.println("=====Employee Menu====");
		String id = m.getId();

		boolean flag = true;
		while(flag) {
			System.out.println("1.Update Flight Schedule 2.Check-in 3.MyInfo 4.Write replies 5.Exit");
			int type =sc.nextInt();
			switch(type){
			case 1://Tickets update/add
				System.out.println("===1.Update Flight Schedule===");
				System.out.println("flight number:");
				String new_flight_num = sc.next();
				System.out.println("depart city:");
				String new_depart_city = sc.next();
				System.out.println("depart time:");
				String new_depart_time = sc.next();
				System.out.println("arrival city:");
				String new_arrive_city = sc.next();
				System.out.println("arrival time:");
				String new_arrive_time = sc.next();
				System.out.println("working days(choose: M,T,W,Th,F,Sat,Sun):");
				String new_days = sc.next();
				System.out.println("price:");
				int new_price = sc.nextInt();
				Flight new_flight = new Flight(new_flight_num, new_depart_city, new_depart_time, new_arrive_city, new_arrive_time, new_days, 30, new_price, " ");
				flightService.insert(new_flight);
				
				//check insertion
				Flight nf = flightService.selectByNum(new_flight_num);
				if(nf!=null) {
					System.out.println("new flight schedule successfully inserted");
				}
				
				System.out.println("Update existing flight schedule:");
				System.out.println("flight number:");
				String update_flight_num = sc.next();
				Flight update_flight = flightService.selectByNum(update_flight_num);
				System.out.println("working days:");
				String update_days = sc.next();
				update_flight.setWorking_date(update_days);
				flightService.update(update_flight);
				break;
				
			case 2://check in
				System.out.println("===2.Check-in===");
				System.out.println("customer's booking number:");
				int c_booking_num = sc.nextInt();
				Booked c_bk = bookedService.selectByNum(c_booking_num);
				if(c_bk!=null && c_bk.getState()!='y') {
					c_bk.setState('y');
					bookedService.checkIn(c_bk);
					System.out.println(c_bk.getId()+" is successfully checked-in.");
				}
				else {
					System.out.println("already checked-in or the booking does not exist");
				}
				
				break;
			case 3://my info
				System.out.println("===3.MyInfo===");
				System.out.println(m);
				System.out.println("edit my info");
				System.out.println("password:");
				String pwd = sc.next();
				System.out.println("passport number:");
				String ps_num = sc.next();
				Member me = new Member(id, null, pwd, ps_num, null, null);
				memberService.update(me);
				
				break;
			case 4:
				System.out.println("===4.Write replies===");
				Reply reply = new Reply();
				
				System.out.println("board_id: ");
				int board_id = sc.nextInt();
				reply.setBoard_id(board_id);
				System.out.println("comment: ");
				String comment = sc.next();
				reply.setComments(comment);
				
				replyService.insert(reply);
				
				ArrayList<Reply> reply_list = replyService.selectAll();
				for(Reply rep: reply_list) {
					System.out.println(rep);
				}
				break;
				
			case 5:
				flag=false;
				break;
			}
		}
	}
	
	public void adminMenu(Scanner sc, Member m) {
		System.out.println("=====Admin Menu====");
		String id = m.getId();
		
		boolean flag = true;
		while(flag) {
			System.out.println("1.Delete unused accounts 2.Clean board 3.Exit");
			int type =sc.nextInt();
			switch(type){
			case 1://delete unused accounts
				System.out.println("===1.Delete unused accounts===");
				ArrayList<String> unused_list = memberService.unusedId();
				for(String unused_id: unused_list) {
					memberService.delete(unused_id);
					if(memberService.selectById(unused_id)==null) {
						System.out.println(unused_id+ "'s account is deleted");
					}
				}
				break;
			case 2://clean bad postings in board & reply
				System.out.println("===2.Clean board===");
				ArrayList <Integer> bad_list = boardService.badBoardNum();
				if(bad_list.isEmpty()) {
					System.out.println("There's no bad posting on the board");
				}
				else {
					for(int bad_posting: bad_list) {
						boardService.delete(bad_posting);
						if(boardService.selectByNum(bad_posting)==null) {
							System.out.println("Posting No."+bad_posting+ " is deleted");
						}
					}	
				}
				
				ArrayList <Integer> bad_replies = replyService.badReplyNum();
				if(bad_replies.isEmpty()) {
					System.out.println("There's no bad replies on the board");
				}
				else {
					for(int bad_reply: bad_replies) {
						Reply re = replyService.selectByNum(bad_reply);
						int bad_reply_posting = re.getBoard_id();
						System.out.println("Posting No."+bad_reply_posting+"has a bad reply No."+bad_reply);
						replyService.delete(bad_reply);
						if(replyService.selectByNum(bad_reply)==null) {
							System.out.println("Reply No."+bad_reply+ " is deleted");
						}
					}
					break;		
				}
				
			case 3:
				flag=false;
				break;
			}
		}
	}

}
