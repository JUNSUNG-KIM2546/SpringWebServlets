package com.exam.myapp.reply;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReplyService {
	
	//댓글 추가
	public int insertReply(ReplyVo rvo);

	//댓글 리스트
	public List<ReplyVo> selectReplyList(int repBbsNo);
	
	//댓글 삭제
	public int deleteReply(ReplyVo rvo);

	

}
