<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>전체 알림</title>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|PT+Sans&display=swap" rel="stylesheet">
	
<style type="text/css">
</style>
<script type="text/javascript">
	//메세지 입력했나 안했나 확인
	function check() {
		$(function() {
			if ("'${login.admin eq 'Y'}' == 'true'") {
				if ($('#title').val().trim() == '') {
					alert('제목을 입력하세요');
					$('#title').focus();
					return false;
				}
				if ($('#body').val().trim() == '') {
					alert('내용을 입력하세요');
					$('#body').focus();
					return false;
				}
				var title = $('#title').val();
				var body = $('#body').val();
				location = "sendALL?title=" + title + "&body=" + body;
			} else {
				alert('관리자 계정이 아닙니다.');
				location = "home";
			}
		});
	}
</script>
</head>
<body>
	<div style="min-height: 1000px; max-height: 100%">
		<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >전체 알림 작성
			<div style="padding-left:40px; margin-right: 40px; margin-top:10px;
				height: 2px; background: #007bff;"></div>
		</div>
		<form action="sendALL" style="margin-bottom: 40px; padding: 40px;">
			<div style="font-size: 15px;">
				제목  <input type="text" name="title" id="title"
					style="width: 50%; font-size: 15px; margin-left: 40px;">
					<br /><br />
				내용<br/>
				  <textarea type="text" name="body" id="body" 
					style="font-size: 15px; width: 95%; height: 350px; margin:0 auto;"></textarea>
				<div style="margin-top: 50px; width: 100%;" align="center">
				<input type="button" class="btn" onclick="return check()"
					style="font-size: 13px; width: 70px; height: 35px; border-radius:20px;
					margin-right: 5px; margin-top: 50px;" value="전송">
				</div>
			</div>
		</form>
	</div>
</body>
</html>