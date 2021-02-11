package com.encore.flight;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//dao
public interface FlightMapper {
	@Insert("insert into encore_tickets values(#{flight_num}, #{depart_city}, #{depart_time}, #{arrive_city}, #{arrive_time}, #{working_date}, #{seat_count}, #{price}, #{state}")
	void insert(Flight flight);
	
	@Select("select * from encore_tickets where flight_num=#{flight_num}")
	Flight selectByNum(@Param("flight_num")String flight_num);
	
	@Select("select * from encore_tickets where depart_city like #{%depart_city%} order by price asc")
	ArrayList<Flight> selectByDepartCity(@Param("depart_city")String depart_city);
	
	@Select("select * from encore_tickets where arrive_city like #{%arrive_city%} order by price asc")
	ArrayList<Flight> selectByArriveCity(@Param("arrive_city")String arrive_city);
	
	@Select("select * from encore_tickets where working_date like #{%working_date%} order by price asc")
	ArrayList<Flight> selectByWorkingDate(@Param("working_date")String working_date);
	
	@Select("select * from encore_tickets")
	ArrayList<Flight> selectAll();
	
	@Update("update encore_tickets set depart_city=#{depart_city}, depart_time=#{depart_time}, arrive_city=#{arrive_city}, arrive_time=#{arrive_time}, working_date=#{working_date}, seat_count=#{seat_count}, price=#{price}, state=#{state} where flight_num=#{flight_num}")
	void update(Flight flight);
	
	@Delete("delete from encore_tickets where flight_num=#{flight_num}")
	void delete(@Param("flight_num")int flight_num);
}
