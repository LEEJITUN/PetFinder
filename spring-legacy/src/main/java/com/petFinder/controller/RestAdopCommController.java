package com.petFinder.controller;
/**
 * @title   : ��õ | ����õ | �Ű� Controller
 * @author  : SUMIN, HYEPIN
 * @date    : 2021.09.23 
 * @version : 1.0 
 **/

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;
import com.petFinder.domain.PageDTO;
import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.service.RestAdopCommService;


@RestController
@RequestMapping("/api/*")
public class RestAdopCommController {
	
	@Autowired
	private RestAdopCommService restAdopCommService;

	// ���� 2�� 
	@PutMapping(value = "/adopCommBoardCheck",
	consumes = "application/json",
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<RestAdopCommVO> adopCommBoardCheck(@RequestBody RestAdopCommVO restAdopCommVO, 
			HttpServletRequest request, RedirectAttributes rttr) throws IOException {
		
		
		int checkNum = restAdopCommService.selectCheck(restAdopCommVO);
		
		// ��õ�� ����õ �� �ϳ��� ���� �ʾ��� ��� ������Ʈ (�ش� ����)
		if(checkNum == 0) {
			// ��õ ����õ ������Ʈ
			restAdopCommService.updateadopCommBoardCheck(restAdopCommVO);
		}else {
			restAdopCommService.deleteCommBoard(restAdopCommVO);		
		}
				
		// ��õ ����õ ��ȸ(count)
		RestAdopCommVO restAdopCommCount = restAdopCommService.selectTotalCount(restAdopCommVO);

	
		return new ResponseEntity<RestAdopCommVO>(restAdopCommCount, HttpStatus.OK);
	} 
}