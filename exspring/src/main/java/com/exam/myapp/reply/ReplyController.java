package com.exam.myapp.reply;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVo;


@RestController //현재 클래스의 모든 요청처리 메서드에 @ResponseBody를 적용
@RequestMapping("/reply/")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;	// 스프링은 기본적으로 싱글톤패턴
	
	@GetMapping("list.do")
//	@ResponseBody
	private List<ReplyVo> list( int repBbsNo ) {
		List<ReplyVo> repList = replyService.selectReplyList(repBbsNo);
		return repList;
	}
	
	// 댓글 추가
	@PostMapping("add.do")
//	@ResponseBody	// 메서드의 반환값을 응답 메시지 내용으로 전송
	public HashMap<String, Object> add(ReplyVo rvo, HttpSession session, @SessionAttribute("loginUser") MemberVo mvo) {
		
		//MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		
		rvo.setRepWriter( mvo.getMemId() );
		int num = replyService.insertReply(rvo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ok", true);	//map객체에 2개의 속성(ok, result)
		map.put("result", num);
		
		//return "redirect:/bbs/edit.do?bbsNo=" + rvo.getRepBbsNo();
		//return num + "reply add";	// ""
		//return "{ \"ok\": true, \"result\" : " + num + "}";
		return map;
	}
	
	// 댓글 삭제
	@GetMapping("del.do")
//	@ResponseBody	// 메서드의 반환값을 응답 json
	public HashMap<String, Object> del(ReplyVo rvo, HttpSession session, @SessionAttribute("loginUser") MemberVo mvo) {
		
		rvo.setRepWriter( mvo.getMemId() );
		
		int num = replyService.deleteReply(rvo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ok", true);	//map객체에 2개의 속성(ok, result)
		map.put("result", num);
		
		return map;
	}

}
