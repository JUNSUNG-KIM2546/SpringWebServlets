package com.exam.myapp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao ;
	
	//멤버 리스트
	@Override
	public List<MemberVo> selectMemberList() {
		return memberDao.selectMemberList();
	}

	//멤버 추가
	@Override
	public int insertMember(MemberVo vo) {
		return memberDao.insertMember(vo);
	}

	//멤버 삭제
	@Override
	public int deleteMember(String memId) {
		return memberDao.deleteMember(memId);
	}

	//멤버 수정
	@Override
	public int updateMember(MemberVo vo) {
		return memberDao.updateMember(vo);
	}

	//멤버 검색
	@Override
	public MemberVo selectMember(String memId) {
		return memberDao.selectMember(memId);
	}

	//멤버 로그인
	@Override
	public MemberVo selectLogin(MemberVo vo) {
		return memberDao.selectLogin(vo);
	}

}
