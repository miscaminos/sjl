package com.study.portfolio;

import java.util.List;
import java.util.Map;

public interface PortfolioService {
	int create(PortfolioVO vo);
	List<PortfolioVO> list_seqno_asc();
	PortfolioVO read(int chapterno);
	int update(PortfolioVO vo);
	int delete(int chapterno);
	int update_seqno_up(int chapterno);
	int update_seqno_down(int chapterno);
	int update_visible(Map map);
}
