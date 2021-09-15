package com.petFinder.service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @title   : 커뮤니티 게시판 Service
 * @author  : HYEPIN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.mapper.CommunityMapper;


@Service
@Transactional
public class CommunityService {

	@Autowired
	private CommunityMapper communityMapper;
}
