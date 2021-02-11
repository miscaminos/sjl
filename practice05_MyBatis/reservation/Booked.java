package com.encore.reservation;

public class Booked {
	
	private int booked_num;//sequence
	private char state; // y=checked-in, n=not checked-in
	private String flight_num;
	private String id;//booked ticket state 
	
	public Booked() {
		
	}
	
	
	public Booked(int booked_num, char state, String flight_num, String id) {
		//super();
		this.booked_num = booked_num;
		this.state = state;
		this.flight_num = flight_num;
		this.id = id;
	}


	public int getBooked_num() {
		return booked_num;
	}


	public void setBooked_num(int booked_num) {
		this.booked_num = booked_num;
	}


	public char getState() {
		return state;
	}


	public void setState(char state) {
		this.state = state;
	}


	public String getFlight_num() {
		return flight_num;
	}


	public void setFlight_num(String flight_num) {
		this.flight_num = flight_num;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Booked [booked_num=" + booked_num + ", state=" + state + ", flight_num=" + flight_num + ", id=" + id
				+ "]";
	}


	@Override
	public boolean equals(Object arg0) {
		
		if(arg0 != null && arg0 instanceof Booked) {
			if(((Booked)arg0).booked_num == booked_num) {
				return true;
			}
		}
		return false;
	}
}
