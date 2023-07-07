package com.exam.myapp.reply;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReplyService {

	public int insertReply(ReplyVo rvo);

	public List<ReplyVo> selectReplyList(int repBbsNo);

	public int deleteBbs(int repNo);

}
