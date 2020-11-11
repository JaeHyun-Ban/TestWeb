package com.testweb.util;

public class PageVO {
	
	//page바를 계산하는 클래스
	private int startPage;
	private int endPage;
	//다음, 이전 버튼 활성화 
	private boolean prev;
	private boolean next;
	
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
		//1.마지막 페이지 = 올림(현재페이지 / 10) * 10;
		//>1~10, 11~20, 21~30
		this.endPage = (int)Math.ceil(this.pageNum / (double)10) * 10;
		
		//2.시작페이지
		//>endPage = 10...20...30..
		//>endPage - 10 + 1 = 1...11...21...>>시작페이지
		this.startPage = this.endPage - 10 + 1;
		
		//3.실제 끝번호
		//>올림(전체글개수 / 한번에 볼 게시글 수)
		//>올림(207/10) = 21, 올림(167/10) = 17
		int realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		//끝번호 조절하기
		if(endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//4.이전버튼 활성화 여부
		//>1페이지빼고 다 활성화
		this.prev = this.startPage > 1;//1페이지 보다 크다면 true
		
		//5.다음페이지 활성화 여부
		//>실제 끝번호 > 마지막 페이지
		this.next = realEnd > endPage;
	}

	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	};
	
	
	
	
}




































