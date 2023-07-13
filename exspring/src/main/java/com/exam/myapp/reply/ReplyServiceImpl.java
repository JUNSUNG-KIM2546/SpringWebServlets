package com.exam.myapp.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
	

	@Autowired
	private ReplyDao replyDao ;
	
	@Override
	public int insertReply(ReplyVo rvo) {
		return replyDao.insertReply(rvo);
	}

	@Override
	public List<ReplyVo> selectReplyList(int repBbsNo) {
		return replyDao.selectReplyList(repBbsNo);
	}

	@Override
	public int deleteReply(ReplyVo rvo) {
		return replyDao.deleteReply(rvo);
	}

}
