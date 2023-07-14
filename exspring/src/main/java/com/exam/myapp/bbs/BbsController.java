package com.exam.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVo;

// 알트 + 쉬프트 + R : 한번에 바꾸기
// 컨트롤 + 쉬프트 + Y : 소문자로 바구기 
// 컨트롤 + 쉬프트 + X : 대문자로 바구기 

@Controller
@RequestMapping("/bbs/")	//현재 컨트롤러 클래스 내부의 모든 메서드들의 공통 경로 설정
public class BbsController {
	@Autowired
	private BbsService bbsService;	// 스프링은 기본적으로 싱글톤패턴
	
	//스프링 방식//
	//게시판리스트
	@GetMapping("list.do")	//@RequestMapping(path = "list.do", method = RequestMethod.GET)
	public String list(Model model, SearchInfo info) {
		int cnt = bbsService.selectBbsCount(info);//전체 레코드 수 조회
		info.setTotalRecordCount(cnt);	//전체 레코드 수 정보 설정
		info.makePageHtml();	//페이지 처리에 필요한 값들 계산
		
		List<BbsVo> List = bbsService.selectBbsList(info);	
		
		model.addAttribute("bbsList", List);
		
		return "bbs/bbsList";
	}
	
	//게시판 글쓰기
	@GetMapping("add.do")	//@RequestMapping(path = "add.do", method = RequestMethod.GET)
	public String addform() {
		return "bbs/bbsAdd";	// 실행될 JSP
	}
	@PostMapping("add.do")	//@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public String add(BbsVo vo, HttpSession session, @SessionAttribute("loginUser") MemberVo mvo) {
		
		//System.out.println("첨부파일명 : " + vo.getBbsFile().getOriginalFilename());
		
		//MemberVo mvo = (MemberVo) session.getAttribute("loginUser"); //세션에 로그인 저장
		vo.setBbsWriter(mvo.getMemId());	//로그인한 사용자아이디를 게시글 작성자로 설정
		
		
		int n = bbsService.insertBbs(vo);	//클래스 참조하라

		System.out.println(n + "개의 게시글 등록");
		
		return "redirect:/bbs/list.do";			
	}
	
	//게시글 수정
	@GetMapping("edit.do")	//@RequestMapping(path = "edit.do", method = RequestMethod.GET)
	public String editform(Model model, int bbsNo) {
		
		BbsVo vo = bbsService.selectBbs(bbsNo);
		model.addAttribute("bvo", vo);
		
		return "bbs/bbsEdit";
	}
	@PostMapping("edit.do")	//@RequestMapping(path = "edit.do", method = RequestMethod.POST)
	public String edit(BbsVo vo, @SessionAttribute("loginUser") MemberVo mvo) {
		
		vo.setBbsWriter( mvo.getMemId() );
		
		int n = bbsService.updateBbs(vo);	//클래스 참조하라
		System.out.println(n + "개의 게시글 수정");

		// 수정 후 목록창으로 이동
		return "redirect:/bbs/list.do";
	}
	
	//게시글 삭제
	@GetMapping("del.do")	//@RequestMapping(path = "del.do", method = RequestMethod.GET)
	public String del(BbsVo vo, @SessionAttribute("loginUser") MemberVo mvo) {		
		
		vo.setBbsWriter( mvo.getMemId() );
		
		int n = bbsService.deleteBbs(vo);	//클래스 참조하라
		System.out.println( n + "개의 게시글 삭제");
		
		// 삭제 후 바로 리스트 창으로 가라
		return "redirect:/bbs/list.do";	
	}
	
	//컨트롤러 메서드가 인자로 HttpServletResponse, OutputStream, Writer를 받고 반환타입이 void 이면, 
	//직접 응답을 처리(전송)했다고 판단하여 스프링은 뷰에 대한 처리를 하지 않는다.
	@GetMapping("down.do")
	public void download(int attNo, AttachVo vo, HttpServletResponse resp) {
		
		vo = bbsService.selectAttach(attNo);	//DB에서 다운로드할 첨부파일 정보
		
		File f = bbsService.getAttachFile(vo);	//디스크 상에서 첨부파일의 위치 가져오기
		
		resp.setContentLength((int) f.length());	//응답메시지 본문(파일)의 크기 설정
		//resp.setContentLengthLong(f.length());		//응답메시지 본문(파일)의 크기 설정
		
		resp.setContentType("application/octet-stream");
		//resp.setContentType(MediaType.application_octet_stream_value);
		
		// 다룬로드 파일을 저장할 때 사용할 디폴트 파일명 설정
		// 지원하는 브라우저에 따라서 다른 처리가 필요할 수도 있다.
//		try {
//			String fname = URLEncoder.encode(vo.getAttOrgName(), "UTF-8").replace("+", "%20");
//			resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fname);
//			
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		String cdv = ContentDisposition.attachment().filename(vo.getAttOrgName(), StandardCharsets.UTF_8).build().toString();
		resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, cdv);
		
		
		try {
			//파일 f의 내용을 응답 객체(출력 스트림)에 복사(전송)	//파일이 저장된 폴더에 파일이 없으면 예외처리한다.
			FileCopyUtils.copy( new FileInputStream(f), resp.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println( f + "의 첨부파일 다운로드");
		//다음 시간에 파일 확장자명, 원래이름으로 저장
	}
	
}

