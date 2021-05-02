package com.study.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.portfolio.PortfolioMapper;
import com.study.portfolio.PortfolioVO;

@RestController
@RequestMapping("/api")
public class PortfolioController {
	
	private static final Logger log = LoggerFactory.getLogger(PortfolioController.class);

	@Autowired
	private PortfolioMapper mapper;

//RestController지정시, 생성자 사용X
	
//	@GetMapping("/hello")
//	public String hello() {
//		return "hello";
//	}
//	
	@GetMapping("/portfolio")
	public String portfolio() {
		return "portfolio";
	}
	
	@PostMapping("/portfolio/create")
	public ResponseEntity<String> create(@RequestBody PortfolioVO dto){
		log.info("Create portfoliono:"+dto.getPortfoliono());
		log.info("Create portfolio:"+dto.getPortfolio());
		
		int flag = mapper.create(dto);
		log.info("Create flag:"+flag);
		
		ResponseEntity<String> success = new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity<String> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag==1? success:fail;
	}
	
//	@GetMapping("/portfolio")
//	public ResponseEntity<List<PortfolioVO>> getList(){
//		ResponseEntity<List<PortfolioVO>> success = new ResponseEntity<>(mapper.list_seqno_asc(),HttpStatus.OK);
//		return success;
//	}
	
	
	@DeleteMapping("/portfolio/{portfoliono}")
	public ResponseEntity<String> remove(@PathVariable("portfoliono") int portfoliono){
		log.info("Delete portfoliono: "+portfoliono);
		
		int flag = mapper.delete(portfoliono);
		log.info("Delete flag:"+flag);
		
		ResponseEntity<String> success = new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity<String> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag==1? success:fail;
	
	}
	
	@PutMapping("/portfolio/{portfoliono}")
	public ResponseEntity<String> modify(@PathVariable("portfoliono") int portfoliono,
			@RequestBody PortfolioVO dto){
		log.info("Update portfoliono:"+portfoliono);
		log.info("Update portfolio:"+dto.getPortfolio());
		
		int flag = mapper.update(dto);
		log.info("flag:"+flag);
		
		ResponseEntity<String> success = new ResponseEntity<>("success",HttpStatus.OK);
		ResponseEntity<String> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag==1? success:fail;		
	}
	
}
