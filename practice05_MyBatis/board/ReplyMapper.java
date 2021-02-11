package com.encore.board;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ReplyMapper {
	
	@Insert("insert into encore_reply values(reply_seq.nextval, #{board_id}, #{comments})")
	void insert(Reply r);
	
	@Select("select * from encore_reply where reply_id=#{reply_id}")
	Reply selectByNum(@Param("reply_id")int reply_id);
	
	@Select("select * from encore_reply")
	ArrayList<Reply> selectAll();
	
	@Update("update encore_reply set comments=#{comments} where reply_id=#{reply_id}")
	void update(Reply r);
	
	@Delete("delete from encore_reply where reply_id=#{reply_id}")
	void delete(@Param("reply_id")int reply_id);
	
	@Select("select reply_id from encore_reply where comments like '%bad%'")
	ArrayList<Integer> badReplyNum();
}
