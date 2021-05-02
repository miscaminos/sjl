package com.study.contents;

import java.util.List;
import java.util.Map;

public interface ContentsMapper {
	int create(ContentsDTO dto);
	List<ContentsDTO> list_seqno_asc();
	int update(ContentsDTO dto);
	int delete(int contentsno);
	ContentsDTO read(int contentsno);
	List<ContentsDTO> getByTitle(String title);
	int deleteAll();
	
	//to be added later:
//	int update_seqno_up(int contentsno);
//	int update_seqno_down(int contentsno);
//	int update_visible(Map map);
}
