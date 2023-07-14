package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BbsDao {

	// 게시물 검색
	List<BbsVo> selectBbsList(SearchInfo info);

	// 게시글 등록
	int insertBbs(BbsVo vo);

	// 게시글 삭제
	int deleteBbs(int memId);
	
	// 게시글 수정
	int updateBbs(BbsVo vo);
	
	// 게시글 수정(게시글 불러오기)
	BbsVo selectBbs(int memId);
	
	// 페이징
	int selectBbsCount(SearchInfo info);

}