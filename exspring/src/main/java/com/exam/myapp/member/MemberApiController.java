package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



//@Controller
@RestController	//클래스 내부의 모든 요청처리 메서드에 @ResponseBody 일괄 적용
@RequestMapping("/api")
public class MemberApiController {
	@Autowired
	private MemberService memberService;	// 스프링은 기본적으로 싱글톤패턴
	
	//스프링 방식//
	//회원리스트
	//@RequestMapping(path = "/member/list", method = RequestMethod.GET)
	@GetMapping("/member/list")
	public List<MemberVo> list() {	
		List<MemberVo> list = memberService.selectMemberList();	
		return list;
	}
	
	//회원 추가
	//스프링에 등록된 표준BeanValidator를 사용하여 저장된 값을 검증하고 싶은 객체 매개변수 앞에 @Valid 적용
	//@Valid 매개변수 다음 위치에 검증결과를 저장하기 위한 BindingResult 또는 Errors 타입의 매개변수를 추가
	//@RequestMapping(path = "/member/add", method = RequestMethod.POST)
	@PostMapping("/member/add")
	public Map<String, Object> add(@Valid MemberVo vo, BindingResult bresult) {
		if(bresult.hasErrors()) {	//검증결과 오류가 있다면
			
			// 에러 확인용
//			for (FieldError fe : bresult.getFieldErrors()) {
//				System.out.println("** " + fe.getField());
//				for (String c : fe.getCodes()) {
//					System.out.println(c);
//				}
//			}
			throw new RuntimeException("회원정보검증오류");
		}
		
		int n = memberService.insertMember(vo);	//클래스 참조하라

		System.out.println(n + "명의 회원 추가");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		
		return map;			
	}
	
	//회원 수정
	//@RequestMapping(path = "/member/edit", method = RequestMethod.GET)
	@GetMapping("/member/edit")
	public MemberVo editform( String memId) {
		MemberVo vo = memberService.selectMember(memId);
		
		return vo;
	}
	//@RequestMapping(path = "/member/edit", method = RequestMethod.POST)
	@PostMapping("/member/edit")
	public Map<String, Object> edit(MemberVo vo) {
		int n = memberService.updateMember(vo);	//클래스 참조하라
		System.out.println(n + "명의 회원 수정");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		
		return map;	
	}
	
	//회원 삭제
	//@RequestMapping(path = "/member/del", method = RequestMethod.GET)
	@GetMapping("/member/del")
	public Map<String, Object> del(String memId) {
		
		int n = memberService.deleteMember(memId);	//클래스 참조하라
		System.out.println( n + "명의 회원 삭제");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		return map;
	}
	
	
}

