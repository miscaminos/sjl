package com.encore.reservation;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BookedMapper {
	
	@Insert("insert into encore_booked values(booked_seq.nextval, #{state}, #{flight_num}, #{id})")
	void insert(Booked b);
	
	@Select("select * from encore_booked where booked_num=#{booked_num}")
	Booked selectByNum(@Param("booked_num")int booked_num);
	
	@Select("select * from encore_booked")
	ArrayList<Booked> selectAll();
	
	@Update("update encore_booked set state=#{state} where booked_num=#{booked_num}")
	void checkIn(Booked b);
	
	@Delete("delete from encore_booked where booked_num=#{booked_num}")
	void delete(@Param("booked_num")int booked_num);
}
