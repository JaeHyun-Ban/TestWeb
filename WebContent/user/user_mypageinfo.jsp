<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../include/header.jsp"%>


<section>
	<div class="container">
		<div class="row join-wrap">
			<!--join-form을 적용한다 float해제 margin:0 auto-->
			<div class="col-xs-12 col-md-9 join-form">

				<div class="titlebox">MEMBER INFO</div>
				<div align="center" style="color: red; font-size: larger;">${msg }</div>
				<p>*표시는 필수 입력 표시입니다</p>
				
				<form action="update.user" method="post" name="regForm">
				<table class="table" >
					<tbody class="m-control">
						<tr>
							<td class="m-title">*ID</td>
							<td><input class="form-control input-sm" name="id" value="${sessionScope.login.id }" readonly /></td>
						</tr>
						<tr>
							<td class="m-title">*이름</td>
							<td><input class="form-control input-sm" name="name" value="${sessionScope.login.name }" required 
									oninvalid="this.setCustomValidity('이름을 입력하세요')" 
									oninput="setCustomValidity('')"/></td>
						</tr>
						<tr>
							<td class="m-title">*비밀번호</td>
							<td><input type="password" class="form-control input-sm" name="password" required 
									oninvalid="this.setCustomValidity('비밀번호를 입력하세요')"
									oninput="setCustomValidity('')"/></td>
						</tr>
						<tr>
							<td class="m-title">*비밀번호확인</td>
							<td><input type="password" class="form-control input-sm" name="pwcheck" required 
									oninvalid="this.setCustomValidity('비밀번호를 입력하세요')"
									oninput="setCustonValidity('')"/></td>
						</tr>
						<tr>
							<td class="m-title">*E-mail</td>
							<td>
								<input class="form-control input-sm" name="email" value="${sessionScope.login.email }" required 
										oninvalid="this.setCustomValidity('이메일을 입력하세요')"
										oninput="setCustomValidity('')"/>@ 
								<select class="form-control input-sm sel" name="eaddr">
										<option>naver.com</option>
										<option>gmail.com</option>
										<option>daum.net</option>
								</select>
								<button class="btn btn-info">중복확인</button>
							</td>
						</tr>
						<tr>
							<td class="m-title">*휴대폰</td>
							<td>
								<input class="form-control input-sm sel" name="phone1" value="${sessionScope.login.phone1 }" required /> - 
								<input class="form-control input-sm sel" name="phone2" value="${sessionScope.login.phone2 }" required /> - 
								<input class="form-control input-sm sel" name="phone3" value="${sessionScope.login.phone3 }" required />
							</td>
						</tr>
						<tr>
							<td class="m-title">*주소</td>
							<td>
								<input class="form-control input-sm add" name="addr_basic" value="${sessionScope.login.addr_basic }" required />
							</td>
						</tr>
						<tr>
							<td class="m-title">*상세주소</td>
							<td>
								<input class="form-control input-sm add" name="addr_detail" value="${sessionScope.login.addr_detail }" required />
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				<div class="titlefoot">
					<button class="btn" onclick="update()">수정</button>
					<button class="btn" onclick="location.href='mypage.user'">목록</button>
				</div>

			</div>


		</div>

	</div>
	<script type="text/javascript">
		function update(){
			document.regForm.submit();
		}
	</script>

</section>


<%@ include file="../include/footer.jsp"%>