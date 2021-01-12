<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/layout/header.jsp" %>

<div class="container">
	<form action="user?cmd=login" method="post">
		<table class="table">
		    <thead class="thead-light">
		      <tr>
		        <th colspan="2">로그인</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>아이디</td>
		        <td>
		        	<input style="width:300px" type="text" name="username" id="username"  required />
		        </td>
		      </tr>
		      <tr>
		        <td>비밀번호</td>
		        <td>
		        	<input style="width:300px" type="password" name="password" id="password" required />
		        </td>
		      </tr>
		    </tbody>
		</table>
		<button id="join" class="btn btn-outline-info">로그인</button>
	</form>
</div>

</body>
</html>