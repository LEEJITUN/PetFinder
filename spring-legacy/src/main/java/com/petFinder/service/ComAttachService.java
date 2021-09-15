package com.petFinder.service;
/**
 * @title   : 공통 파일 처리 Service 
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.petFinder.mapper.ComAttachMapper;



@Service
@Transactional
public class ComAttachService{
 
	@Autowired
	private ComAttachMapper comAttachMapper;
	
	@Autowired
	private AttachFile attachFile;
	

}
