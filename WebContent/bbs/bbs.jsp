<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!--게시판만 적용되는 css-->            
<style>
.table-striped > tbody > tr {
	background-color: rgba(255, 255, 255)
}
.row h2 {
	color: aliceblue;                
}
.pagination-sm {
	margin: 0;
}            
</style>

<%@ include file="../include/header.jsp" %>
        
    <section>
        
        <div class="container">
            <div class="row">
                
                <h2>게시판 목록</h2>
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    
                    <%-- 게시글을 반복해서 뽑아주기 > 향상된 for문을 이용 --%>
                    <c:forEach var="vo" items="${list }">
                    <tbody>
                        <tr>
                            <td>${vo.bno }</td>
                            <td><a href="content.bbs?bno=${vo.bno }">${vo.title }</a></td>
                            <td>${vo.writer }</td>
                            <td><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd HH-mm"/> </td>
                            <!-- <td>2019-09-14 08:05</td> 이 형태로 포맷 -->
                        </tr>
                    </tbody>
                    </c:forEach>  
                </table>
                
				<%-- 페이지 바 --%>
                <div class="text-center">
                    <ul class="pagination pagination-sm">
                    	<%-- 이전버튼 활성화 여부 --%>
                    	<c:if test="${pageVO.prev }">
                        	<li><a href="list.bbs?pageNum=${pageVO.startPage - 1}&amount=${pageVO.amount}">이전</a></li>
                        </c:if>
                        <%-- pagination번호 반복 출력 --%>
                        <c:forEach var="num" begin="${pageVO.startPage }" end="${pageVO.endPage }">
                        	<%-- active = 현재 페이지활성화 시 색칠됨 >> 3항연산으로 처리 --%>
                        	<li class="${num eq pageVO.pageNum ? 'active' : '' }">
                        		<a href="list.bbs?pageNum=${num }&amount=${pageVO.amount}">${num }</a>
                        	</li>
                        </c:forEach>
                        <%-- 다음버튼 활성화 여부  --%>
                        <c:if test="${pageVO.next }">
                        	<li><a href="list.bbs?pageNum=${pageVO.endPage + 1}&amount=${pageVO.amount}">다음</a></li>
                        </c:if>
                    </ul>
                    <button class="btn btn-info pull-right" onclick="location.href='write.bbs'">글쓰기</button>
                </div>
                
            </div>
        </div>
    </section>

 
<%@ include file="../include/footer.jsp" %>





