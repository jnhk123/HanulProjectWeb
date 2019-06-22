<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	function findPw() {
		$.ajax({
			type : 'post',
			url : 'findPw',
			data : {
				name : $('#name').val(),
				email : $('#email').val(),
				id : $('#id').val()
			},
			success : function(data) {
				console.log(data);
				if (data != null  && data != "" ) {
					var str=document.getElementById("fpw");
					var fstr=document.getElementById("findpw");
					var fdstr=document.getElementById("find");
					str.innerHTML ="찾으시는 비밀번호는 '";
					fstr.innerHTML=data;
					fdstr.innerHTML="' 입니다.";
					str.style.color="black";
					fstr.style.color="green";
					fdstr.style.color="black";
				} else {
					/* alert("입력하신 정보로 비밀번호를 조회할 수 없습니다."); */
					var str=document.getElementById("findpw");
					str.innerHTML ="찾으시는 비밀번호가 존재하지 않습니다.";
					str.style.color="red";
					$('#name').val('');
					$('#email').val('');
					$('#id').val('');
					$('#name').focus();
				}
			},
			error : function(req) {
				alert(req.status);
			}
		});
	}
</script>
<title>Insert title here</title>
</head>
<body>
<div style="height: 1000px; width: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >비밀번호 찾기
	         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
	            height: 2px; background: #007bff;"></div>
	      </div>
		<form style="padding: 50px;">
	<table class="type07" style="font-size: 13px;">
		<tr>
			<th>이름</th>
			<td colspan="2"><input type="text" id="name" style="font-size: 13px; width: 250px;"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td colspan="2"><input type="text" id="email" style="font-size: 13px; width:  250px;"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td><input type="text" id="id" style="font-size: 13px; width:  250px;"></td>
			<td><input type="button" class="btn" value="비밀번호 찾기" onclick="findPw()"
				style="font-size: 13px; border-radius:10px; height: 35px;"></td>
		</tr>
	</table>
	<div align="center" style="font-size: 13px;">
	<span id="fpw"></span><span id="findpw"></span><span id="find"></span>
	</div>
	
	<script type="text/javascript" src="resources/js/need_check.js"></script>
			<div align="center" style="margin-top: 100px;">
			<input onclick="location='home'" class="btn" type="button" value="홈으로" 
			style="font-size: 13px; border-radius:10px; height: 35px">
			</div> 
		</form>
	</div>
	
</body>
</html>