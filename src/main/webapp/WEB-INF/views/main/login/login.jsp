<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<script res="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<!-- Bootstrap CSS -->
<link href="https://fonts.googleapis.com/css?family=Hind+Vadodara:400,700|Mukta:500,700" rel="stylesheet">
<link rel="stylesheet" href="resources/dist/css/style.css?<%=new java.util.Date().getTime()%>">
<script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap" rel="stylesheet">	
<script type="text/javascript">
	function go_login() {
		$.ajax({
			type : 'post',
			url : 'login',
			data : {
				id : $('#uid').val(),
				pw : $('#upw').val()
			},
			success : function(data) {
				if (data) {
					location = "home";
				} else {
					alert("아이디나 비밀번호가 일치하지 않습니다");
					$('#uid').val('');
					$('#upw').val('');
					$('#uid').focus();
				}
			},
			error : function(req) {
				alert(req.status);
			}
		});
	}
	
	
</script>
<title>로그인</title>
<style>
html {
	height: 100%;
}

body {
	width: 100%;
	margin: 0;
	padding-bottom: 40px;
	font-family: "Nanum Gothic", arial, helvetica, sans-serif;
	background-repeat: no-repeat;
	/* background: linear-gradient(to bottom right, #0098FF, #6BA8D1); */
}

.card {
	margin: 0 auto;
	float: none;
	margin-bottom: 10px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.site-header {
	margin: 0;
	padding: 0;
}

.header {
	height: 100px;
}
 	#ga{
		overflow: hidden; 
	} 
 	.la{ 
		padding-right: 20px;
 		float: right; 
 		font-family: Jua;
 		font-size: 22px;
	} 
</style>
</head>
<header class="site-header"
	style="width: 95%; background: #ffffff; margin: 0 auto;">
	<div class="container">
		<div style="height: 100px;" class="site-header-inner">
			<div class="brand header-brand">
				<h1 class="m-0">
					<a onclick="location='<c:url value="/"/>'"> <svg width="48"
							height="32" viewBox="0 0 48 32"
							xmlns="http://www.w3.org/2000/svg">
                                    <title>니집내집</title>
                                    <defs>
                                        <linearGradient x1="0%"
								y1="100%" y2="0%" id="logo-a">
                                            <stop stop-color="#007CFE"
								stop-opacity="0" offset="0%" />
                                            <stop stop-color="#007DFF"
								offset="100%" />
                                        </linearGradient>
                                        <linearGradient x1="100%"
								y1="50%" x2="0%" y2="50%" id="logo-b">
                                            <stop stop-color="#FF4F7A"
								stop-opacity="0" offset="0%" />
                                            <stop stop-color="#FF4F7A"
								offset="100%" />
                                        </linearGradient>
                                    </defs>
                                    <g fill="none" fill-rule="evenodd">
                                        <img src="resources/img/titleicon.png" 
									style="margin-top:50px; margin-left:15px;width: 200px;" />
                                    </g>
                                </svg>
					</a>
				</h1>
			</div>
		</div>
	</div>
	<div style="margin-top: 60px; color:#76808a;" id="ga" align="right">
		<a class="la" onclick="location='list.qu'">문의하기</a>
		<a class="la" onclick="location='list.co'">커뮤니티</a> 
		<a class="la" onclick="location='home.st'">우리집 현황</a> 
		<a class="la" onclick="location='list.no'">공지사항</a>
	</div>
	<div
		style="width: 95%; background: #ffffff; height: 100px; margin: 0 auto;"></div>
</header>

<div style="height: 100px; width: 95%; background: #ffffff; margin: 0 auto;"></div>
<div style="height: 800px; width: 95%; background: #ffffff; margin: 0 auto;">

<body style="width: 100%; background: #f1f5f8; margin: 0 auto;" align="center">
	<div class="card align-middle"
		style="width: 20rem; border-radius: 20px;">
		<div class="card-title" style="margin-top: 30px;">
			<h2 class="card-title text-center" style="color: #113366; font-family: Jua;
		font-size: 40px;">로그인</h2>
		</div>
		<div class="card-body">
			<form class="form-signin" method="POST"
				onSubmit="logincall();return false">
				<h5 class="form-signin-heading"></h5>
				<label for="inputEmail" class="sr-only">Your ID</label> 
				<input type="text" id="uid" class="form-control" placeholder="아이디를 입력해주세요" required autofocus><BR> 
				<label for="inputPassword" class="sr-only">Password</label> 
				<input type="password" onkeypress="if(event.keyCode == 13){ go_login() }" id="upw"
					class="form-control" placeholder="비밀번호를 입력해주세요" required><br>
				<div style="display: flex; width: 400px;">
					<input type="button" id="btn-Yes" class="btn" on value="로 그 인" onclick="go_login()"
						style="height: 45px; width: 150px; font-family: Do Hyeon; font-size: 20px; border-radius: 10px;" />
					<button id="join" class="btn" type="button" onclick="location='client.cs'"
						style="height: 45px;font-family:Do Hyeon;
							font-size: 20px; width: 180px; margin-left: 20px; border-radius: 10px;">회 원 가 입</button>
				</div>
			</form>
		</div>
	</div>
		<div align="center">
				<a onclick="location='findId.cs'" style="font-size: 13px; margin-right: 15px;">아이디 찾기</a>
				<span style="font-size: 13px;">/</span>
				<a onclick="location='findPw.cs'" style="font-size: 13px; margin-left: 15px;">비밀번호 찾기</a>
			</div>

	<div class="modal"></div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>
<!--  </form> -->
</div>
<div style="width: 95%; margin: 0 auto;">
	<jsp:include page="../../include/footer.jsp"></jsp:include></div>
</html>


