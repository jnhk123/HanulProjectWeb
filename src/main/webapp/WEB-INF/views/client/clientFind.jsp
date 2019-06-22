<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function findId(){
		$.ajax({
			type : 'post',
			url : 'findId',
			data : {
				name : $('#name').val(),
				email : $('#email').val()
			},
			success : function(data) {
				if (data) {
					alert("조회하신 아이디는" );
				} else {
					alert("조회하신 이름과 이메일이 조회할 수 없습니다.");
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
	
	function findPw(){
	}

</script>
<title>Insert title here</title>
</head>

<body>
	<table summary="테이블 구성" style="font-size: 15px;">
		<tr>
			<th>이름</th><td><input type="text" id="name"></td>
		</tr>
		<tr>
			<th>이메일</th><td><input type="text" id="email"></td><td><input type="button" value="아이디 찾기" onclick="findId()"></td>
		</tr>
		<tr>
			<th>아이디</th><td><input type="text" id="id"></td><td><input type="button" value="비밀번호 찾기" onclick="findPw()"></td>
		</tr>
		
	
	</table>
	<script type="text/javascript" src="resources/js/need_check.js"></script>
</body>
</html>