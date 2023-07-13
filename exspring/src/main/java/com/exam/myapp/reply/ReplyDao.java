package com.exam.myapp.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {

	public int insertReply(ReplyVo rvo);

	public List<ReplyVo> selectReplyList(int repBbsNo);

	public int deleteReply(ReplyVo rvo);
	
}
