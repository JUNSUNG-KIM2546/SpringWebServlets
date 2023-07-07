package com.exam.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

	// 회원목록 검색
	List<MemberVo> selectMemberList();

	// 회원 추가
	int insertMember(MemberVo vo);

	// 회원 삭제
	int deleteMember(String memId);
	
	// 회원 수정
	int updateMember(MemberVo vo);
	
	// 회원 수정(회원정보 불러오기)
	MemberVo selectMember(String memId);

	// 회원 로그인
	MemberVo selectLogin(MemberVo vo);



}