package com.petFinder.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.ComCodeVO;
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.PetVO;
import com.petFinder.domain.ReportBoardCommentVO;
import com.petFinder.domain.ReportBoardVO;
import com.petFinder.service.ReportCommentService;
import com.petFinder.service.RestComSelectBoxService;
import com.petFinder.service.PetFindService;


@RestController
@RequestMapping("/api/*")
public class RestComSelectBoxController {
	
	@Autowired
	private RestComSelectBoxService restComSelectBoxService;

	// 코드 조회
	@GetMapping(value = "/coedStrList/{codeM}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ComCodeVO>> selectCoedStrList(@PathVariable("codeM") String codeM) {

		List<ComCodeVO> coedStrList = restComSelectBoxService.selectCoedStrList(codeM);
		
		return new ResponseEntity<List<ComCodeVO>>(coedStrList, HttpStatus.OK);
	} 
   
	
	// 시군구 코드 조회
	// 시도 ,시군구, 법정명
	
	@GetMapping(value = "/sidoCoedList/{clickCode}/{codeM}/{codeD}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ComCodeVO>> sidoCoedList(@PathVariable("clickCode") String clickCode, @PathVariable("codeM") String codeM,@PathVariable("codeD") String codeD) {

		ComCodeVO vo = new ComCodeVO(codeM,codeD,clickCode);
		
		List<ComCodeVO> coedStrList = restComSelectBoxService.sidoCoedList(vo);
		
		return new ResponseEntity<List<ComCodeVO>>(coedStrList, HttpStatus.OK);
	} 
}