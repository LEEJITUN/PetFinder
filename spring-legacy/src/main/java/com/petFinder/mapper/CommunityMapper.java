package com.petFinder.mapper;
/**
 * @title   : Ŀ�´�Ƽ �Խ��� Mapper
 * @author  : HYEPIN
 * @date    : 2021.09.19
 * @version : 1.4
 **/

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petFinder.domain.ComBoardVO;
import com.petFinder.domain.Criteria;

@Mapper
public interface CommunityMapper {

	/* SELECT - �Խñ� ��ȣ ��ȸ */
	int selectBoardNumber();

	/* SELECT - �Խñ� ��ü �������� */
	List<ComBoardVO> selectBoardList();
	
	/* SELECT - �Խñ� ����¡ */
	List<ComBoardVO> selectBoardsWithPaging(Criteria cri);
	
	/* SELECT - ��ü �۰��� */
	int selectTotalCount();
	
	/* INSERT - Ŀ�´�Ƽ �Խñ� �ۼ� */
	void insertBoardWrite(ComBoardVO comBoardVO);

	/* SELECT - �۹�ȣ�� �ش��ϴ� �� �Ѱ� �������� */
	ComBoardVO selectBoardContent(String boardId);
	
	/* UPDATE - �Խñ� ��ȸ�� �� */
	void updateBoardReadCount(String boardId);

	/* UPDATE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void updateBoardModify(ComBoardVO comBoardVO); // ������, �۳��� ����

	/* DELETE - �۹�ȣ�� �ش��ϴ� �� �����ϱ� */
	void deleteBoardContent(String boardId);

	/* SELECT - �˻��� �����Ͽ� �ش� ��ü �۰��� �������� */
	int selectTotalCountBySearch(Criteria cri);





	
}
