package com.encore.member;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//dao
public interface MemberMapper {
	@Insert("insert into encore_member values(#{id}, #{name}, #{password}, #{passportnum}, #{type}, sysdate)")
	boolean insert(Member m);
	
	@Select("select * from encore_member where id=#{id}")
	Member selectById(@Param("id")String id);
	
	@Select("select * from encore_member")
	ArrayList<Member> selectAll();
	
	@Update("update encore_member set password=#{password}, passportnum=#{passportnum}, login_time=sysdate where id=#{id}")
	void update(Member m);
	
	@Delete("delete from encore_member where id=#{id}")
	void delete(@Param("id") String id);
	
	@Select("select id from encore_member where months_between(sysdate, login_time)>24")
	ArrayList <String> unusedId();
}
