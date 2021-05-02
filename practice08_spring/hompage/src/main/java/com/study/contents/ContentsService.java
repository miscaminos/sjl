package com.study.contents;

import java.util.List;
import java.util.Map;

public interface ContentsService {
	int create(ContentsDTO vo);
	List<ContentsDTO> list_seqno_asc();
	ContentsDTO read(int contentsno);
	int update(ContentsDTO vo);
	int delete(int contentsno);
	int update_seqno_up(int contentsno);
	int update_seqno_down(int contentsno);
	int update_visible(Map map);
}
