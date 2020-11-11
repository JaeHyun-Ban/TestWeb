<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<%@ include file="../include/header.jsp" %>

    
	<section>
        <div class="container">
            <div class="row join-wrap">
                
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER                   
                    </div>
                    <div align="center" style="color: red; font-size: larger;">${msg }</div>
                    <div>
                        <p>${sessionScope.login.id }(${sessionScope.login.name })님 회원정보</p>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary" onclick="location.href='mypageinfo.user'">회원정보변경</button>
                        <button type="button" class="btn btn-primary" id="delCheck">회원 탈퇴</button>
                        
                        
                    </div>
                    <div class="delete-hidden">
                        <form action="delete.user" method="post" name="regForm">
	                        <input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
	                        <button type="button" class="btn btn-primary" onclick="check()">확인</button>
                        </form>
                    </div>
                    
                    
                    <br>
                    <div>
                        <p>${sessionScope.login.id }님의 작성 게시물</p>
                        <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    
                    <thead>
                        <tr>
                            <th style="text-align: center;">번호</th>
                            <th style="text-align: center;">제목</th>
                            <th style="text-align: center;">작성자</th>
                            <th style="text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    <%-- 반복 출력 --%>
                    <c:forEach var="list" items="${mypage }">
                    <tbody>
                        <tr>
                            <td>${list.bno }</td>
                            <td><a href="<%=request.getContextPath() %>/bbs/content.bbs?bno=${list.bno }">${list.title }</a></td>
                            <td>${list.writer }</td>
                            <td><fmt:formatDate value="${list.regdate }" pattern="yyyy-MM-dd HH:mm"/> </td>
                            <!-- <td>2019-09-14 08:05</td> -->
                        </tr>

                    </tbody>
                    </c:forEach>
                </table>
                    </div>
                    
                    
                </div>


            </div>

        </div>

    </section>
    
    <script>
        //탈퇴버튼 디스플레이 처리
        var delCheck = document.getElementById("delCheck");
        delCheck.onclick = function() {
            var del  = document.querySelector(".delete-hidden");
            if(del.style.display == "none" || del.style.display == "") {
                del.style.display = "inline";
            } else {
                del.style.display = "none";
            }
        }
        
        //delete버튼 서브밋 버튼
        function check() {
        	document.regForm.submit();
        }
        
    </script>
    
    
<%@ include file="../include/footer.jsp" %>