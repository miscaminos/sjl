package com.study.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.contents.ContentsDTO;
import com.study.contents.ContentsMapper;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ContentsController {

	private static final Logger log = LoggerFactory.getLogger(ContentsController.class);

	@Autowired
	private ContentsMapper mapper;

//RestController지정시, 생성자 사용X

//	@GetMapping("/hello")
//	public String hello() {
//		return "hello";
//	}
//	
//	@GetMapping("/contentss")
//	public String contents() {
//		return "contents";
//	}

	@PostMapping("/contents/create")
	public ResponseEntity<String> createContents(@RequestBody ContentsDTO dto) {
		log.info("Create contentsno:" + dto.getContentsno());
		log.info("Create contents:" + dto.getContents());

		int flag = mapper.create(dto);
		log.info("Create flag:" + flag);

		ResponseEntity<String> success = new ResponseEntity<>("success", HttpStatus.OK);
		ResponseEntity<String> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag == 1 ? success : fail;
	}

	@GetMapping("/contents")
	public ResponseEntity<List<ContentsDTO>> getContentsList() {
		ResponseEntity<List<ContentsDTO>> success = new ResponseEntity<>(mapper.list_seqno_asc(), HttpStatus.OK);
		return success;
	}

	@GetMapping("/contents/{contentsno}")
	public ResponseEntity<ContentsDTO> getContents(@PathVariable("contentsno") int contentsno) {
		ResponseEntity<ContentsDTO> success = new ResponseEntity<>(mapper.read(contentsno), HttpStatus.OK);
		return success;
	}

	@PutMapping("/contents/{contentsno}")
	public ResponseEntity<String> updateContents(@PathVariable("contentsno") int contentsno,
			@RequestBody ContentsDTO dto) {
		log.info("Update contentsno:" + contentsno);
		log.info("Update contents:" + dto.getContents());

		int flag = mapper.update(dto);
		log.info("flag:" + flag);

		ResponseEntity<String> success = new ResponseEntity<>("success", HttpStatus.OK);
		ResponseEntity<String> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag == 1 ? success : fail;
	}

	@DeleteMapping("/contents/{contentsno}")
	public ResponseEntity<HttpStatus> deleteContents(@PathVariable("contentsno") int contentsno) {
		log.info("Delete contentsno: " + contentsno);

		int flag = mapper.delete(contentsno);
		log.info("Delete flag:" + flag);

		ResponseEntity<HttpStatus> success = new ResponseEntity<>(HttpStatus.OK);
		ResponseEntity<HttpStatus> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag == 1 ? success : fail;
	}

	@DeleteMapping("/contents")
	public ResponseEntity<HttpStatus> deleteAllContents() {
		int flag = mapper.deleteAll();
		ResponseEntity<HttpStatus> success = new ResponseEntity<>(HttpStatus.OK);
		ResponseEntity<HttpStatus> fail = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return flag == 1 ? success : fail;
	}

	@GetMapping("/contents/{title}")
	public ResponseEntity<List<ContentsDTO>> findByTitle(@PathVariable("title") String title) {
		ResponseEntity<List<ContentsDTO>> success = new ResponseEntity<>(mapper.getByTitle(title), HttpStatus.OK);
		return success;
	}

}
