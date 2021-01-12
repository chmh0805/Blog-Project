<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/layout/header.jsp" %>

<div class="container">
	<form action="user?cmd=join" method="post" onsubmit="return valid()">
		<table class="table">
		    <thead class="thead-light">
		      <tr>
		        <th colspan="2">회원가입</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>아이디</td>
		        <td>
		        	<input style="width:300px" type="text" name="username" id="username"  required />
		        	<button type="button" onClick="usernameCheck()">ID중복체크</button>
		        </td>
		      </tr>
		      <tr>
		        <td>비밀번호</td>
		        <td>
		        	<input style="width:300px" type="password" name="password" id="password" required />
		        </td>
		      </tr>
		      	      <tr>
		        <td>이메일</td>
		        <td>
		        	<input style="width:300px"  type="email" name="email" id="email" required />
		        </td>
		      </tr>
		    </tbody>
		</table>
		<button id="join" class="btn btn-outline-primary">회원가입</button>
	</form>
</div>

<script>
	var isChecked = false;
	
	function valid() {
		if (isChecked == false) {
			alert('아이디 중복체크를 해주세요.');
		}
		return isChecked;
	}
	
	function usernameCheck() {
		var username = $("#username").val();
		
		$.ajax({
			type: "POST",
			url: "/blogproject/user?cmd=usernameCheck",
			data: username,
			contentType: "text/plain; charset=utf-8",
			dataType: "text"
		}).done(function(data){
			if (data === 'ok') {
				isChecked = true;
				$("#username").attr("readonly", true);
				alert("해당 유저네임을 사용할 수 있습니다.");
			} else {
				isChecked = false;
				alert('유저네임이 중복되었습니다.');
			}
		});
	}
	
</script>

</body>
</html>