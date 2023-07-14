package com.exam.myapp.bbs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional	//이 객체의 모든 메서드들을 각각 하나의 트랜잭션으로 정의
public class BbsServiceImpl implements BbsService {
	@Autowired
	private BbsDao bbsDao ;
	@Autowired
	private AttachDao attachDao ;
	
	@Value("${bbs.upload.path}")	//지정한 값을 스프링이 변수에 주입(저장) @value를 써서 스프링이 스스로 프로퍼티키에 맞는 값을 주입(저장)
	private String uploadPath;	//게시판 첨부파일 저장 위치
	
	
	@PostConstruct	//스프링이 현재 객체의 초기화 작업이 완료된 후 실행
	public void init() {
		new File(uploadPath).mkdirs();	//uploadPath 디렉토리가 없으면 생성(파일을 저장할 등록한 폴더가 없으면 생성하고 저장하라)
	}

	//게시물 리스트
	@Override
	public List<BbsVo> selectBbsList(SearchInfo info) {
		return bbsDao.selectBbsList(info);
	}

	//게시글 추가 트랜잭션
	//@Transactional	// 이 메서드을 하나의 트랜잭션으로 정의
	@Override
	public int insertBbs(BbsVo vo) {
		int num = bbsDao.insertBbs(vo);
		
		List<MultipartFile> bbsFileList = vo.getBbsFile();
		for (MultipartFile f : bbsFileList) {
			if (f.getSize() <= 0 ) continue;	//파일크기가 0인 경우, 저장하지 않음 
				
			
			System.out.println( "파일명: " + f.getOriginalFilename() );
			System.out.println( "파일크기: " + f.getSize() );
			
			String fname = null;	
			File saveFile = null;	
			do {	
				fname = UUID.randomUUID().toString();	//중복될 확률이 극도로 낮은 랜덤 문자열 생성
				saveFile = new File(uploadPath, fname);	//첨부파일 어떤 이름으로 저장할지(플러스나 ,로 하면 된다)
			} while ( saveFile.exists() );	//중복되지 않은 명이 나올때까지 반복
			
			try {
				f.transferTo(saveFile);	//파일 f의 내용을 saveFile에 복사(저장)
				
				//int a = 10/0; 오류테스트
				
				AttachVo attVo = new AttachVo();
				attVo.setAttBbsNo( vo.getBbsNo() );	//첨부파일이 속한 게시글 번호
				attVo.setAttOrgName( f.getOriginalFilename() );	//첨부파일 원래 이름
				attVo.setAttNewName( fname );	//첨부파일 저장 이름
				attachDao.insertAttach(attVo);
				
			} catch (IllegalStateException | IOException e) {
				//e.printStackTrace();
				throw new RuntimeException(e);	//첨부파일 저장 중 오류 발생시 트랜잭션이 롤백되도록
			}
		}
		
		return num;
	}

	//게시글 삭제
	@Override
	public int deleteBbs(BbsVo vo) {
		//
		BbsVo bbsVo = bbsDao.selectBbs(vo.getBbsNo());	//삭제할 게시글 정보 조회
		if ( ! bbsVo.getBbsWriter().equals(vo.getBbsWriter())) {
			return 0;
		}
			
		for (AttachVo attVo : bbsVo.getAttachList() ) {	//게시글의 첨부파일을 하나씩 꺼내서
			new File(uploadPath, attVo.getAttNewName()).delete();	//디스크에서 첨부파일 삭제
			attachDao.deleteAttach(attVo.getAttNo());	//테이블에서 첨부파일 삭제
		}
		
		return bbsDao.deleteBbs(vo.getBbsNo());	//테이블에서 게시글 삭제
	}

	//게시글 수정
	@Override
	public int updateBbs(BbsVo vo) {
		return bbsDao.updateBbs(vo);
	}

	//게시글 검색
	@Override
	public BbsVo selectBbs(int bbsNo) {
		return bbsDao.selectBbs(bbsNo);
	}

	// 첨부파일 불러오기
	@Override
	public AttachVo selectAttach(int attNo) {
		return attachDao.selectAttach(attNo);
	}
	
	//하드디스크에서 읽어서 응답으로 보냄
	@Override
	public File getAttachFile(AttachVo vo) {
		return new File(uploadPath, vo.getAttNewName());
	}

	@Override
	public int selectBbsCount(SearchInfo info) {
		return bbsDao.selectBbsCount(info);
	}

}
