package com.exam.myapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//웹브라우저에서 
//http://localhost:8000/exweb/member/del.do?memId=삭제할회원아이디
//로 요청을 보내면, 지정한 회원 정보를 데이터베이스에서 삭제하고
//"명의 회원 삭제 성공" 메시지와 "회원목록으로 이동" 링크를 화면에 출력
//회원 삭제 SQL문
//DELETE 
//FROM MEMBER  
//WHERE MEM_ID = '삭제할회원아이디';

@WebServlet("/member/delform.do")
public class MemDelFormServlet extends HttpServlet {

	String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
	String user ="WEB";		//데이터베이스 접속 아이디
	String password ="WEB01";		//데이터베이스 접속 비밀번호
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		PrintWriter out = resp.getWriter();	
		System.out.println("회원 삭제");
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>회원 삭제</title>              ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("	<h1> 회원 삭제 </h1> 				   ");
		out.print("<form action='" + req.getContextPath() + "/member/del.do'>");
		out.print("		아이디 : <input type='text' name='memId' value=''><br>"			);
		out.print("		<input type='submit'/>"	);
		out.print("</form>");
		out.print("	<h2>=================================================================</h2> ");
		out.print("	<h2>회원목록으로 가기</h2> ");
		out.print("<a href='" + req.getContextPath() + "/member/list2.do' method='post'>");
		out.print("		<button type='button'> 회원 목록 </button>");
		out.print("</body>                             ");
		out.print("</html>                             ");
	}

}
