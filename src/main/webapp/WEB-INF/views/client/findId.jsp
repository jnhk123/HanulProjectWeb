<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<script type="text/javascript">
	function findId() {
		$.ajax({
			type : 'post',
			url : 'findId',
			data : {
				name : $('#name').val(),
				email : $('#email').val()
			},
			success : function(data) {
				if (data != null  && data != "" ) {
					var str=document.getElementById("fid");
					var fstr=document.getElementById("findid");
					var fdstr=document.getElementById("find");
					str.innerHTML ="찾으시는 아이디는 '";
					fstr.innerHTML=data;
					fdstr.innerHTML="' 입니다.";
					str.style.color="black";
					fstr.style.color="green";
					fdstr.style.color="black";
				} else{
					var str=document.getElementById("findid");
					str.innerHTML ="찾으시는 아이디가 존재하지 않습니다.";
					str.style.color="red";
					$('#name').val('');
					$('#email').val('');
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
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >아이디 찾기
	         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
	            height: 2px; background: #007bff;"></div>
	      </div>
		<form style="padding: 40px;">
			<table class="type07" style="font-size: 13px;">
				<tr>
					<th>이름</th>
					<td colspan="2"><input style="font-size: 13px; width: 250px;" type="text" id="name"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="email"  style="font-size: 13px; width: 250px;"></td>
					<td><input class="btn" type="button" value="아이디 찾기" 
						style="font-size: 13px; border-radius:10px; height: 35px;" onclick="findId()"></td>
				</tr>
			</table>
			<div align="center" style="font-size: 14px;">
					<span id="fid"></span><span id="findid"></span><span id="find"></span>
			</div>
			<script type="text/javascript" src="resources/js/need_check.js"></script>
			<div align="center" style="margin-top: 100px;">
			<input onclick="location='home'"
			class="btn" type="button" value="홈으로" style="font-size: 13px; border-radius:10px; height: 35px;">
			</div> 
		</form>
	</div>

</body>
</html>