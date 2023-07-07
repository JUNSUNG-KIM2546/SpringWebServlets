package com.exam.myapp.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;	// 스프링은 기본적으로 싱글톤패턴
	
	//스프링 방식//
	//회원리스트
	@RequestMapping(path = "/member/list.do", method = RequestMethod.GET)
	public String list(Model model) {	
		
		List<MemberVo> List = memberService.selectMemberList();	
		
		model.addAttribute("memberList", List);
		
		return "member/memList";
	}
	
	//회원 추가
	@RequestMapping(path = "/member/add.do", method = RequestMethod.GET)
	public String addform() {
		return "member/memAdd";
	}
	@RequestMapping(path = "/member/add.do", method = RequestMethod.POST)
	public String add(MemberVo vo) {
//		req.setCharacterEncoding("UTF-8"); //필터로 이동
		
		int n = memberService.insertMember(vo);	//클래스 참조하라

		System.out.println(n + "명의 회원 추가");
		
		return "redirect:/member/list.do";			
	}
	
	//회원 수정
	@RequestMapping(path = "/member/edit.do", method = RequestMethod.GET)
	public String editform(Model model, String memId) {
		
		MemberVo vo = memberService.selectMember(memId);
		model.addAttribute("mbvo", vo);
		
		return "member/memEdit";
	}
	@RequestMapping(path = "/member/edit.do", method = RequestMethod.POST)
	public String edit(MemberVo vo) {
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		
		int n = memberService.updateMember(vo);	//클래스 참조하라
		System.out.println(n + "명의 회원 수정");

		// 수정 후 목록창으로 이동
		return "redirect:/member/list.do";
	}
	
	//회원 삭제
	@RequestMapping(path = "/member/del.do", method = RequestMethod.GET)
	public String del(String memId) {
//		req.setCharacterEncoding("UTF-8");
		
		int n = memberService.deleteMember(memId);	//클래스 참조하라
		System.out.println( n + "명의 회원 삭제");
		
		// 삭제 후 바로 리스트 창으로 가라
		return "redirect:/member/list.do";	
	}
	
	//로그인	
	@RequestMapping(path = "/member/login.do", method = RequestMethod.GET)
	public String loginform() {
//		String memId = req.getParameter("memId");
//		MemberVo vo = memberDao.selectMember(memId);
//		req.setAttribute("mbvo", vo);
		
		return "member/memLogin";	//포워딩
	}
	@RequestMapping(path = "/member/login.do", method = RequestMethod.POST)
	public String login(MemberVo vo, HttpSession session) {
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		
		//데이터베이스에 입력한 아이디와 비번이 있는지 확인 후 로그인
		//파라미터 값으로
		MemberVo lo = memberService.selectLogin(vo);
		
		// 로그인 실패
		if (lo == null) {
			System.out.println(lo + " 회원 로그인 실패");
			// 로그인 실패시 다시 로그인창으로 이동
			return "member/memLogin";
			//return "redirect:/member/login.do";
		}
		// 로그인 성공
		else {
			// 세션객체(일정시간까지 유지, 사용자별로 구별가능), 서블릿컨텍스트객체(서버 종료시까지 유지, 한사람이 접속하면 모든사람이 사용가능)
			// 세션객체
			session.setAttribute("loginUser", lo); //세션에 로그인 저장
			System.out.println(lo + " 회원 로그인 성공");
			// 로그인 후 목록창으로 이동
			return "redirect:/member/list.do";
		}	
	}
	
	//로그아웃
	@RequestMapping(path = "/member/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 3개중 하나만 사용
//		session.setAttribute("loginUser", null); //세션에 지정한 이름의 속성값을 null로
//		session.removeAttribute("loginUser");	//세션에서 지정한 이름의 속성을 삭제
		session.invalidate(); //세션객체를 제거(후 다시 생성), 모든 속성들도 함께 삭제
		
		System.out.println(" 회원 로그아웃 성공 ");
		// 로그아웃 시 로그인창으로 이동
		return "redirect:/member/login.do";			
	}
}

