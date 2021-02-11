package com.encore.flight;

public class Flight {
	
	private String flight_num;
	private String depart_city;
	private String depart_time;//���� : HH24:MI
	private String arrive_city;
	private String arrive_time;//���� : HH24:MI
	private String working_date;
	private int seat_count;//�¼� ��
	private int price;//����
	private String state;
	
	public Flight() {
		
	}
	
	
	public Flight(String flight_num, String depart_city, String depart_time, String arrive_city, String arrive_time,
			String working_date, int seat_count, int price, String state) {
		super();
		this.flight_num = flight_num;
		this.depart_city = depart_city;
		this.depart_time = depart_time;
		this.arrive_city = arrive_city;
		this.arrive_time = arrive_time;
		this.working_date = working_date;
		this.seat_count = seat_count;
		this.price = price;
		this.state = state;
	}


	public String getFlight_num() {
		return flight_num;
	}


	public void setFlight_num(String flight_num) {
		this.flight_num = flight_num;
	}


	public String getDepart_city() {
		return depart_city;
	}


	public void setDepart_city(String depart_city) {
		this.depart_city = depart_city;
	}


	public String getDepart_time() {
		return depart_time;
	}


	public void setDepart_time(String depart_time) {
		this.depart_time = depart_time;
	}


	public String getArrive_city() {
		return arrive_city;
	}


	public void setArrive_city(String arrive_city) {
		this.arrive_city = arrive_city;
	}


	public String getArrive_time() {
		return arrive_time;
	}


	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}


	public String getWorking_date() {
		return working_date;
	}


	public void setWorking_date(String working_date) {
		this.working_date = working_date;
	}


	public int getSeat_count() {
		return seat_count;
	}


	public void setSeat_count(int seat_count) {
		this.seat_count = seat_count;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		return "Flight [flight_num=" + flight_num + ", depart_city=" + depart_city + ", depart_time=" + depart_time
				+ ", arrive_city=" + arrive_city + ", arrive_time=" + arrive_time + ", working_date=" + working_date
				+ ", seat_count=" + seat_count + ", price=" + price + ", state=" + state + "]";
	}


	@Override
	public boolean equals(Object arg0) {
		
		if(arg0 != null && arg0 instanceof Flight) {
			if(((Flight)arg0).flight_num == flight_num) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
}
