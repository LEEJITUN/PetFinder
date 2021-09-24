package com.petFinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petFinder.domain.RestAdopCommCommentVO;
import com.petFinder.domain.RestAdopCommVO;
import com.petFinder.mapper.RestAdopCommCommentMapper;
import com.petFinder.mapper.RestAdopCommMapper;



@Service
@Transactional
public class RestAdopCommService {

	@Autowired
	private RestAdopCommMapper restAdopCommMapper;
	
	@Autowired
	private RestAdopCommCommentMapper restAdopCommCommentMapper;
	
	// ================ 추천 비추천 =================
	
	/* UPDATE - 추천 비추천 */
	public void updateadopCommBoardCheck(RestAdopCommVO restAdopCommVO) {
		
		int num = restAdopCommMapper.selectNextNumber(restAdopCommVO);
		restAdopCommVO.setNum(num);
		restAdopCommMapper.updateadopCommBoardCheck(restAdopCommVO);
	}

	/* SELECT - 추천 비추천 각각 갯수*/
	public RestAdopCommVO selectTotalCount(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectTotalCount(restAdopCommVO);
		
	}
	
	/* SELECT - 해당 게시물에 추천 or 비추천을 했는지 조회 */
	public int selectCheck(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.selectCheck(restAdopCommVO);
		
	}
	
	/* DELETE - 추천 OR 비추천이 있을경우 삭제 (해당 유저) */
	public int deleteCommBoard(RestAdopCommVO restAdopCommVO) {
		return restAdopCommMapper.deleteCommBoard(restAdopCommVO);
		
	}
	
	// ================ 댓글 =================

	/* SELECT - 댓글 index 조회) */
	public int selectCommentIndex(RestAdopCommCommentVO restAdopCommCommentVO) {
		return restAdopCommCommentMapper.selectCommentIndex(restAdopCommCommentVO);
	}

	// 댓글 작성
	public void insertComment(RestAdopCommCommentVO restAdopCommCommentVO) {
		restAdopCommCommentMapper.insertComment(restAdopCommCommentVO);
	}

	// 댓글 전체 조회
	public List<RestAdopCommCommentVO> selectComments(String reportId) {
		return restAdopCommCommentMapper.selectComments(reportId);
	}

	// 댓글 수정
	public void updateComment(RestAdopCommCommentVO restAdopCommCommentVO) {
		restAdopCommCommentMapper.updateComment(restAdopCommCommentVO);
	}

	// 댓글 삭제
	public void deleteComment(String commentId) {
		restAdopCommCommentMapper.deleteComment(commentId);
		
	}

	// 답글 +1 업데이트
	public void updateReSeqPlusOne(RestAdopCommCommentVO restAdopCommCommentVO) {
		
		// 답글을 남길 대상글과 같은 글그룹 안에서
		//  대상글의 순번보다 큰 글들의 순번을 1씩 증가(UPDATE)
		restAdopCommCommentMapper.updateReSeqPlusOne(restAdopCommCommentVO);
		
		// insert할 답글 re값으로 수정
		restAdopCommCommentVO.setCommentLev(restAdopCommCommentVO.getCommentLev() + 1);
		restAdopCommCommentVO.setCommentSeq(restAdopCommCommentVO.getCommentSeq() + 1);
		
		// 답글 insert 하기
		restAdopCommCommentMapper.insertComment(restAdopCommCommentVO);	
	}

	
}
