package com.petFinder.service;
/**
 * @title   : 반려동물 입양|임보 Service 
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title   : 반려동물 입양|임보 Service
 * @author  : SUMIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.mapper.AdopTempMapper;



@Service
@Transactional
public class AdopTempService {
 
	@Autowired
	private AdopTempMapper adopTempMapper;
	
	

}
