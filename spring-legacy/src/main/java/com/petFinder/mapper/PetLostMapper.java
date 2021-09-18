package com.petFinder.mapper;
/**
 * @title   : 반려동물 분실 신고 Mapper
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.PetVO;

@Mapper
public interface PetLostMapper {

	/* INSERT - 분실 신고 등록 */
	void insertLostReport(PetVO petVO);
	
	/* UPDATE - 분실 신고 수정 */
	void updateLostReport(PetVO petVO);

	/* UPDATE - 분실 신고 삭제 */
	void deleteLostReport(String reportId);

}
