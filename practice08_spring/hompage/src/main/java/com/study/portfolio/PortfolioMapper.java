package com.study.portfolio;

import java.util.List;
import java.util.Map;

public interface PortfolioMapper {
	int create(PortfolioVO dto);
	List<PortfolioVO> list_seqno_asc();
	int update(PortfolioVO dto);
	int delete(int chapterno);
	
	//to be added later:
	PortfolioVO read(int chapterno);
	int update_seqno_up(int chapterno);
	int update_seqno_down(int chapterno);
	int update_visible(Map map);
}
