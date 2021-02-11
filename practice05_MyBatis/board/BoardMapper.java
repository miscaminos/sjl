package com.encore.board;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
//dao
public interface BoardMapper {
	
	@Insert("insert into encore_board values(board_seq.nextval, #{writer}, sysdate, #{title}, #{content})")
	void insert(Board b);
	
	@Select("select * from encore_board where board_id=#{board_id}")
	Board selectByNum(@Param("board_id")int board_id);
	
	@Select("select * from encore_board")
	ArrayList<Board> selectAll();
	
	@Update("update encore_board set title=#{title}, content=#{content} where board_id=#{board_id}")
	void update(Board b);
	
	@Delete("delete from encore_board where board_id=#{board_id}")
	void delete(@Param("board_id")int board_id);
	
	@Select("select board_id from encore_board where content like '%bad%' or title like '%bad%'")
	ArrayList<Integer> badBoardNum();

}
