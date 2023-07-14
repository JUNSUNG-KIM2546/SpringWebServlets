package com.exam.myapp.bbs;

import java.util.Iterator;

public class PageInfo {

	private int currentPageNo = 1;			//현재 페이지 번호	yes
	private int recordCountPerPage = 5;		//한 페이지당 게시되는 게시물 건 수	yes	
	private int pageSize = 3;				//페이지 리스트에 게시되는 페이지 건수	yes	
	private int totalRecordCount;		//전체 게시물 건 수	yes	
	private int totalPageCount;			//페이지 개수	no	
	private int firstPageNoOnPageList;	//페이지 리스트의 첫 페이지 번호	no	
	private int lastPageNoOnPageList;	//페이지 리스트의 마지막 페이지 번호	no	
	private int firstRecordIndex;		//페이징 SQL의 조건절에 사용되는 시작 rownum	no	
	private int lastRecordIndex;		//페이징 SQL의 조건절에 사용되는 마지막 rownum	no	
	
	private String pageHtml = "";
	


	public void makePageHtml() {
		totalPageCount = ((totalRecordCount-1) / recordCountPerPage) + 1;
		firstPageNoOnPageList = ((currentPageNo-1) / pageSize) * pageSize + 1;
		lastPageNoOnPageList = firstPageNoOnPageList + pageSize - 1;
		
		if(lastPageNoOnPageList > totalPageCount){
			lastPageNoOnPageList = totalPageCount;
		}
		
		firstRecordIndex = (currentPageNo - 1) * recordCountPerPage;
		lastRecordIndex = currentPageNo * recordCountPerPage;
		
		pageHtml += "<a href=\"#\" onclick=\"goPage(1); return false;\">[처음]</a> &#160;"; 
		pageHtml += "<a href=\"#\" onclick=\"goPage(" + (currentPageNo - 1) + "); return false;\">[이전]</a> &#160;";
		
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo) {
				pageHtml += "<strong>{" + i + "}</strong> &#160;";
			}
			else {
				pageHtml += "<a href=\"#\" onclick=\"goPage(" + i + "); return false;\">{" + i + "}</a> &#160;";
			}
		}
		pageHtml += "<a href=\"#\" onclick=\"goPage(" + (currentPageNo + 1) + "); return false;\">[다음]</a> &#160;";
		pageHtml += "<a href=\"#\" onclick=\"goPage(" + totalPageCount + "); return false;\">[마지막]</a> &#160;";
		pageHtml += "<script> function goPage(n) { location.href = location.pathname + '?currentPageNo=' + n; } </script>";
		
	}
	
	public String getPageHtml() {
		return pageHtml;
	}
	
	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}
	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}
	public int getLastPageNoOnPageList() {
		return lastPageNoOnPageList;
	}
	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}
	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}
	public int getLastRecordIndex() {
		return lastRecordIndex;
	}
	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}
	
}
