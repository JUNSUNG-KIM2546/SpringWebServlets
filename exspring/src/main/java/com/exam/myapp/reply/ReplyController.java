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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVo;

@Controller
@RequestMapping("/reply/")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;	// 스프링은 기본적으로 싱글톤패턴
	
	@GetMapping("list.do")
	@ResponseBody
	private List<ReplyVo> list( int repBbsNo ) {
		List<ReplyVo> repList = replyService.selectReplyList(repBbsNo);
		return repList;
	}
	
	@PostMapping("add.do")
	@ResponseBody	// 메서드의 번환값을 응답
	public HashMap<String, Object> add(ReplyVo rvo, HttpSession session, @SessionAttribute("loginUser") MemberVo mvo) {
		
		//MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		
		rvo.setRepWriter( mvo.getMemId() );
		int num = replyService.insertReply(rvo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ok", true);
		map.put("result", num);
		
		//return "redirect:/bbs/edit.do?bbsNo=" + rvo.getRepBbsNo();
		//return num + "reply add";	// ""
		//return "{ \"ok\": true, \"result\" : " + num + "}";
		return map;
	}
	
	//댓글 삭제
	@GetMapping("del.do")	//@RequestMapping(path = "del.do", method = RequestMethod.GET)
	public String del(int repNo) {		
			
		int n = replyService.deleteBbs(repNo);	//클래스 참조하라
		System.out.println( n + "개의 게시글 삭제");
		
		// 삭제 후 바로 리스트 창으로 가라
		return "redirect:/reply/list.do";	
	}

}
