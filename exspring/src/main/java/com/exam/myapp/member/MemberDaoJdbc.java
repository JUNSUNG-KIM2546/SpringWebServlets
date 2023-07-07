package com.exam.myapp.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO : Data Access Object
public abstract class MemberDaoJdbc implements MemberDao {
	// 데이터베이스 정보
	String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
	String user ="WEB";		//데이터베이스 접속 아이디
	String password ="WEB01";		//데이터베이스 접속 비밀번호
	
	// 회원목록 검색
	@Override
	public List<MemberVo> selectMemberList() {
		List<MemberVo> List = new ArrayList<MemberVo>();
		
		String sql = "SELECT mem_ID,MEM_PASS,MEM_NAME,MEM_POINT FROM MEMBER ORDER BY mem_Id";					//*중요* 안에 세미콜론은 없어도 됨
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음	
			) 	
		{ 
			//SQL문 실행 (SELECT 문 실행은 executeQuery()메서드 사용
			ResultSet rs = pstmt.executeQuery();	//반환값은 조회 결과 레코드(row) 수
			
			// 처음 ResultSet 객체는 조회결과에서 첫 레코드(row) 이전을 가리키고 있음
			// .next() 메서드를 실행하면 다음 레코드를 가리키게 된다.
			// .next() 메서드는 다음 레코드가 있으면 true를 반환하고, 없으면 false를 반환하라
			while (rs.next())	
			{
				MemberVo vo = new MemberVo();
				//컬럼값의 데이터타입에 따라서 get타입("컬럼명") 메서드를 사용하여 컬럼값 읽기
				vo.setMemId(rs.getString("mem_ID")); //현재 가리키는 레코드(row)의 "mem_id" 컬럼값 읽기
				vo.setMemPass(rs.getString("MEM_PASS")); //현재 가리키는 레코드(row)의 "MEM_PASS" 컬럼값 읽기
				vo.setMemName(rs.getString("MEM_NAME")); //현재 가리키는 레코드(row)의 "MEM_NAME" 컬럼값 읽기
				vo.setMemPoint(rs.getInt("MEM_POINT")); //현재 가리키는 레코드(row)의 "MEM_POINT" 컬럼값 읽기	
				List.add(vo);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return List;
	}
	
	// 회원 추가
	@Override
	public int insertMember(MemberVo vo) {
		String sql = "INSERT INTO MEMBER ( mem_id ,MEM_PASS, MEM_NAME, MEM_POINT )"		//SQL 문
					+ "VALUES ( ?, ?, ?, ? )";							//*중요* 안에 세미콜론은 없어도 됨
		
		int n = 0;	//반환값은 SQL문 실행으로 영향받은 레코드(row) 수
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음
			) 
			{ 
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, vo.getMemId());		//1번째 ?에 memId 값을 넣기
			pstmt.setString(2, vo.getMemPass());	//2번째 ?에 memPass 값을 넣기
			pstmt.setString(3, vo.getMemName());	//3번째 ?에 memName 값을 넣기
			pstmt.setInt(4, vo.getMemPoint());		//4번째 ?에 memPoint 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용
			n = pstmt.executeUpdate();                                             
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	// 회원 삭제
	@Override
	public int deleteMember(String memId) {
		//SQL 문
		String sql = "DELETE FROM MEMBER WHERE mem_id = ? ";		//*중요* 안에 세미콜론은 없어도 됨	
					
		
		int n = 0;	//반환값은 SQL문 실행으로 영향받은 레코드(row) 수
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음
			) 
			{ 
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, memId);		//1번째 ?에 memId 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용
			n = pstmt.executeUpdate();                                             
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
}
