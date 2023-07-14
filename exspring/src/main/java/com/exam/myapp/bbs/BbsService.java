package com.exam.myapp.bbs;

import java.io.File;
import java.util.List;

public interface BbsService {

	// 게시물 목록 검색
	List<BbsVo> selectBbsList(SearchInfo info);

	// 게시글 등록
	int insertBbs(BbsVo vo);

	// 게시글 삭제
	int deleteBbs(BbsVo vo);
	
	// 게시글 수정
	int updateBbs(BbsVo vo);
	
	// 게시글 수정(게시글 불러오기)
	BbsVo selectBbs(int bbsNo);

	// 첨부파일 다운로드
	AttachVo selectAttach(int attNo);

	File getAttachFile(AttachVo vo);
	
	//페이징
	int selectBbsCount(SearchInfo info);

}