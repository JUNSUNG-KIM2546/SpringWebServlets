package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachDao {

	// 첨부파일 검색
	//List<BbsVo> selectBbsList();

	// 첨부파일 등록
	int insertAttach(AttachVo vo);
	
	// 첨부파일 조회(불러오기)
	AttachVo selectAttach(int attNo);

	// 첨부파일 삭제
	int deleteAttach(int attNo);
	
	// 첨부파일 수정
	//int updateBbs(BbsVo vo);
	
	// 첨부파일 수정(첨부파일 불러오기)
	//BbsVo selectBbs(int memId);

}