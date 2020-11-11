package com.testweb.util;

public class PageVO {
	
	//page바를 계산하는 클래스
	private int startPage;
	private int endPage;
	private boolean prev, next;//다음, 이전 버튼 활성화 
	
	private int pageNum;//보고있는 페이지번호
	private int amount;//한번에 볼 게시글의 수
	private int total;//전체 게시글 수
	
	public PageVO(int pageNum, int amount, int total) {
		
		//설정 초기화
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		//(중요)계산 진행
		///////////////////////////////////////
		//마지막 페이지 = 올림(현재페이지 / 10) * 10;
		//>1~10, 11~20, 21~30
		this.endPage = (int)Math.ceil(this.pageNum / (double)10) * 10;
		
		//endPage = 10...20...30..
		//>endPage - 10 + 1 = 1..2...3..
		this.startPage = this.endPage - 10 + 1;
		
		//실제 끝번호
		//>올림(전체글개수 / 한번에 볼 게시글 수)
		int realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
	};
	
	
}




































