package com.exam.myapp.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController	//클래스 내부의 모든 요청처리 메서드에 @ResponseBody 일괄 적용
@RequestMapping("/rest")
public class MemberRestController {
	@Autowired
	private MemberService memberService;	// 스프링은 기본적으로 싱글톤패턴
	
	//회원리스트
	@GetMapping("/members")
	public List<MemberVo> list() {	
		List<MemberVo> list = memberService.selectMemberList();	
		return list;
	}
	
	//회원 추가
	@PostMapping("/members")
	public ResponseEntity<Map<String, Object>> add(@RequestBody @Valid MemberVo vo, BindingResult bresult) {
		if(bresult.hasErrors()) {	//검증결과 오류가 있다면
			throw new RuntimeException("회원정보검증오류");
		}
		
		int n = memberService.insertMember(vo);

		System.out.println(n + "명의 회원 추가");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		
		//return map;
		return new ResponseEntity< Map<String, Object>>(map, HttpStatus.CREATED);
		 
	}
	
	//회원 수정
	// 경로내에서 경로변수로 저장하고 싶은 부분을 {경로변수명}으로 지정
	@GetMapping("/members/{memId}")	
	public MemberVo editform(@PathVariable("memId") String memId) {
		MemberVo vo = memberService.selectMember(memId);
		
		return vo;
	}
	@PatchMapping("/members/{memId}")
	// @RequestBody : 요청메시지의 본문 내용을 자바 객체로 변환하여 매개변수에 저장
	public Map<String, Object> edit(@RequestBody MemberVo vo) {
		int n = memberService.updateMember(vo);	
		System.out.println(n + "명의 회원 수정");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		
		return map;	
	}
	
	//회원 삭제
	@DeleteMapping("/members/{memId}")
	public Map<String, Object> del(@PathVariable String memId) {	// 경로변수명과 매개변수명이 동일하면, @PathVariable()에서 경로변수명 생략 가능
		int n = memberService.deleteMember(memId);
		System.out.println( n + "명의 회원 삭제");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", n);
		return map;
	}
	
	
}

