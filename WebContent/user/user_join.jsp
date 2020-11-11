<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--회원가입 폼만 적용되는 css-->
<style type="text/css">
.table-striped>tbody>tr {
	background-color: #f9f9f9
}

.join-form {
	margin: 0 auto;
	padding: 20px;
	width: 50%;
	float: none;
	background-color: white;
}

.form-group>.sel {
	width: 120px;
	display: inline-block;
}
</style>


<%@ include file="../include/header.jsp" %>

<section>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-9 col-sm-12 join-form">
				<h2>회원가입<small>(가운데정렬)</small></h2>
				<div align="center" style="color: red;">${msg }</div>
				
				<form action="joinForm.user" method="post" name="regForm">
					<div class="form-group">
						<label for="id">아이디</label> 
						<input type="text" class="form-control" id="id" name="id" 
								placeholder="아이디를 (영문포함 4~12자 이상)" required pattern="[a-zA-Z]{4,12}">
					</div>
					<div class="form-group">
						<label for="password">비밀번호</label> 
						<input type="password" class="form-control" id="password" name="password"
							placeholder="비밀번호 (영 대/소문자, 숫자, 특수문자 3종류 이상 조합 8자 이상)" required>
					</div>
					<div class="form-group">
						<label for="password-confrim">비밀번호 확인</label> 
						<input type="password" class="form-control" id="password-confrim"
							placeholder="비밀번호를 확인해주세요." required>
					</div>
					<div class="form-group">
						<label for="name">이름</label> 
						<input type="text" class="form-control" id="name" name="name"
							placeholder="이름을 입력하세요." required>
					</div>
					<!--input2탭의 input-addon을 가져온다 -->
					<div class="form-group">
						<label for="hp">휴대폰번호</label><br> <input
							class="form-control sel" placeholder="010" id="phone1" name="phone1" required> - <input
							class="form-control sel" placeholder="xxxx" id="phone2" name="phone2" required> - <input
							class="form-control sel" placeholder="xxxx" id="phone3" name="phone3" required> 
					</div>
					<div class="form-group">
						<label for="hp">이메일</label><br> 
						<input class="form-control sel" id="email" name="email">@ 
						<select name="eaddr" class="form-control sel">
							<option>naver.com</option>
							<option>gmail.com</option>
							<option>daum.net</option>
						</select>
					</div>

					<div class="form-group">
						<label for="addr-num">주소</label> 
						<input type="text" class="form-control" id="addr-basic" name="addr-basic" placeholder="기본주소">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addr-detail" name="addr-detail"	placeholder="상세주소">
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-lg btn-success btn-block" onclick="joincheck()">회원가입</button>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-lg btn-info btn-block" onclick="location.href='login.user'">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>


</section>

<script type="text/javascript">
	//회원가입 버튼 submit처리
	function joincheck(){
		document.regForm.submit();
	}
</script>
<!-- onclick안에 자바스크립트 직접 작성 x, 따로 작성 후 대입 -->
<!-- <script type="text/javascript">
	function check() { //가입버튼 이벤트
		//alert("가입 버튼 눌림!");
		//form태그는 유일하게 document.form이름.이름... 접근이 가능하다.
		console.log(document.regForm.id);//>누를때마다 폼태그의 id 정보를 가져온다
		console.log(document.regForm.id.name);
		console.log(document.regForm.id.value);
		
		//value는 String값이다
		if(document.regForm.id.value.length < 4){ //value가 공백이라면
			//>아이디 길이가 4자리 미만이라면 경고창
			alert("공백입니다!");//경고창 띄워주기
			return; //함수 종료
		} else if(document.regForm.pw.value < 4 ){
			alert("비밀번호는 4자리 이상입니다");
			return;
		} else if(document.regForm.pw.value != document.regForm.pwCheck.value){
			alert("비밀번호를 다시 확인해주세요");
			return;
			
		} else if(document.regForm.name.value == '') {
			alert("이름은 필수 입니다");
			return;
		} else {
			//submit()은 자바스크립트의 서브밋기능
			document.regForm.submit();
		}
	}
</script> -->


<%@ include file="../include/footer.jsp" %>








































