<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|PT+Sans|Poor+Story&display=swap" rel="stylesheet">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>니집내집 &mdash; 우리집을 내손안에서...</title>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|PT+Sans&display=swap" rel="stylesheet">
<script	src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	//로그아웃 처리
	function logout() {
		$.ajax({
			url : 'logout',
			success : function() {
				location = "home"; //무조건 홈으로
			},
			error : function(req) {
				alert(req.status);
			}
		});
	}
</script>
</head>
<body class="is-boxed has-animations">
	<!--     <div class="body-wrap boxed-container"> -->
	<main>
	<section class="hero" style="background: #ffffff;">
		<div class="container">
			<div class="hero-inner">
				<div class="hero-copy">
					<h2 class="hero-title h2-mobile mt-0 is-revealing" style="font-family: ">너의 집 나의 집!</h2>

					<!-- 카카오톡 로그인 처리 -->
					<a href="http://developers.kakao.com/logout"></a>
					<script type='text/javascript'>
						//카카오톡 앱키
						Kakao.init('2e6d1993455094c1830708e2c9ec9dac');
						// 카카오 로그인 버튼을 생성
						function kakao_login() {
							Kakao.Auth
									.loginForm({
										success : function(authObj) {
											Kakao.API
													.request({
														url : '/v1/user/me',
														success : function(res) {
															var id = res.id;
															var email = res.kaccount_email;
															var name = res.properties['nickname'];
															var profile_image = res.properties['profile_image'];
															console
																	.log(JSON
																			.stringify(authObj)); //Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
															console.log(res.id);//콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)
															console
																	.log(res.kaccount_email);//콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)
															console
																	.log(res.properties['nickname']);
															console
																	.log(profile_image);
															console
																	.log(authObj.access_token);// 콘솔 로그에 토큰값 출력

															$.ajax({
																		url : 'kakao_login',
																		data : {
																			id : id,
																			email : email,
																			name : name,
																			profile_image : profile_image
																		},
																		success : function() {
																			location = "home"; // 무조건 홈으로
																		},
																		error : function(
																				req) {
																			alert(req.status);
																		}
																	});
														}
													})
										},
										fail : function(err) {
											alert(JSON.stringify(err));
										}
									});
						}
					</script>
					<!-- 페이스북 로그인 처리 -->
					<script>
						window.fbAsyncInit = function() {
							FB.init({
								appId : '326362824741911',
								cookie : true,
								xfbml : true,
								version : 'v3.3'
							});

							FB.AppEvents.logPageView();
						};

						(function(d, s, id) {
							var js, fjs = d.getElementsByTagName(s)[0];
							if (d.getElementById(id)) {
								return;
							}
							js = d.createElement(s);
							js.id = id;
							js.src = "https://connect.facebook.net/en_US/sdk.js";
							fjs.parentNode.insertBefore(js, fjs);
						}(document, 'script', 'facebook-jssdk'));
						/* 페이스북 로그인 처리 */
						function facebook_login() {
							FB.login(function(response) {
								console.log(response);
								var userid = response.authResponse.userID;
								FB.api('/' + userid, 'GET', {
									"fields" : "birthday,hometown,name,email"
								}, function(response) {
									console.log(JSON.stringify(response));
									var id = response.id;
									var name = response.name;
									console.log(id);
									console.log(name);
									$.ajax({
										url : 'facebook_login',
										data : {
											id : id,
											name : name
										},
										success : function() {
											location = "home"; //무조건 홈으로
										},
										error : function(req) {
											alert(req.status);
										}
									});
								});
							});
						}
						function facebook_logout() {
							FB.logout(function(response) {
								console.log(response);
							});
						}
					</script>
					<c:if test="${empty login }">
						<p class="hero-paragraph is-revealing" 
							style="font-family: Jua; font-size: 22px;">'방문자' 님 어서오세요</p>
						<p class="hero-cta is-revealing">
							<!-- 페이스북 로그인 버튼 -->
							<a id="fb_logout" onclick="facebook_login()"> <img
								src="img/facebook.png"
								style="width: 72px; height: 38px; float: left; margin-right: 8px;"></a>
							<!-- 카카오톡 로그인 버튼 -->
							<a id="kakao-login-btn" onclick="kakao_login()"> <img
								src="img/kakaotalk.png"
								style="width: 72px; height: 38px; float: left; margin-right: 8px;"></a>
							<!-- 로그인 & 회원가입 버튼 -->
							<!-- 로그인 -->
							<a href="login.cs"> <img src="img/login.png"
								style="width: 72px; height: 38px; float: left; margin-right: 8px;"></a>
							<!-- 회원가입  -->
							<a onclick="location='client.cs'"> <img src="img/join.png"
								style="width: 72px; height: 38px; float: left;"></a> <br />
						</p>
					</c:if>
					<!-- 로그인 성공시 프로필 띄우는 부분 -->
					<c:if test="${!empty login }">
						<div style="float: left; font-family: Jua; font-size: 22px;">'${login.name }' 님 어서오세요</div>
						<!-- 로그아웃 -->
						<a onclick="logout()"> <img src="img/logout.png"
							style="width: 75px; height: 35px; float: left; margin-left: 10px;" /></a>
					</c:if>
				</div>
				<div class="hero-illustration is-revealing">
					<img src="img/mainhome.gif" />
				</div>
			</div>
		</div>
	</section>
	<!-- 메뉴 아이콘 -->
	<section class="features section text-center"
		style="background: #F6F8FA;">
		<div class="section-square"></div>
		<div class="container" style="background: #F6F8FA;">
			<div class="features-inner section-inner">
				<div class="features-wrap">
					<div class="feature is-revealing">
						<a onclick="location='list.no'">
							<div class="feature-inner">
								<img src="img/notice.png" style="width: 60px; margin: 0 auto;"/>
								<h4 class="feature-title h3-mobile" style="font-family: Jua; font-size: 28px; margin-top: 10px;">공지사항</h4>
							</div>
						</a>
					</div>
					<div class="feature is-revealing">
						<a onclick="location='home.st'">
							<div class="feature-inner" >
								<img src="img/home.png" style="width: 60px; margin: 0 auto;"/>
								<h4 class="feature-title h3-mobile" style="font-family: Jua; font-size: 28px; margin-top: 10px;">우리집 현황</h4>
							</div>
						</a>
					</div>
					<div class="feature is-revealing">
						<a onclick="location='list.co'">
							<div class="feature-inner">
								<img src="img/community.png" style="width: 60px; margin: 0 auto;"/>
								<h4 class="feature-title h3-mobile" style="font-family: Jua; font-size: 28px; margin-top: 10px;">커뮤니티</h4>
							</div>
						</a>
					</div>
					<div class="feature is-revealing">
						<a onclick="location='list.qu'">
							<div class="feature-inner">
								<img src="img/question.png" style="margin: 0 auto; width: 60px;"/>
								<h4 class="feature-title h3-mobile" style="font-family: Jua; font-size: 28px; margin-top: 10px;">문의하기</h4>
							</div>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="features section text-center"
		style="background: #F6F8FA; padding-top: 80px;">
		<div class="hero-illustration is-revealing">
			<h1 style="font-family: PT Sans ">About Us</h1>
			<h6 style="font-family:Do Hyeon">니집내집은 IoT와 결합하여 사용할 수 있는 스마트홈 기기입니다.</h6>
			<div style="overflow:hidden; width: 100%">
				<img src="img/info.jpg" style="width:45%; margin-top: 40px; float: left; margin-left: 50px; padding-bottom: 100px;">
				<div style="float: left; margin-left:30px; width: 45%; text-align: left;">
					<div style="margin-top: 120px; font-family:Jua; font-size: 30px; color: #000000">니집 내집</div>
					<div style="margin-top: 10px; font-family:Do Hyeon; font-size: 20px;">'내 손안에 있는 스마트 폰 하나로 모든 것들이 제어가 된다면?'</div>
					<div style="font-size: 15px;">
						이라는 생각을 해보신 적이 있나요? 우리 니집 내집에서는 스마트 폰이나 웹 페이지를 통해 우리 집의 모든
						것을 제어할 수 있도록 제작되었습니다.<br/><br/> 우리 제품을 통하여 더 많고 다양한 스마트한 생활을 누리세요!
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="features section text-center" style="background: #F6F8FA; padding-top: 100px;">
		<div class="hero-illustration is-revealing">
			<div style="overflow:hidden;">
			<img src="img/info2.jpg" style="width:45%;  margin-top: 30px; float: right; margin-right: 50px; padding-bottom: 100px;">
				<div style="float: left; margin-left:50px; width: 45%; text-align: left;">
					<div style="margin-top: 100px; font-family:Jua; font-size: 30px; color: #000000">니집 내집 개발 진행도</div>
					<div style="margin-top: 10px; font-size: 15px;">현재 니집내집에서는 아이컨트롤 네트웍스에서 제공하는 '2015 스테이트 오브 스마트홈 리포트'를 기준으로
						기능을 개발하는 중입니다.</div>
						<div style="float: left; padding-top: 20px;">
				<ul style="float: right; font-size: 13px; list-style: none; text-align: left;">
					<li><img src="img/check.png"
						style="width: 20px; height: 20px; float: left; padding-right: 10px;">실외등
						자동 원격 조정장치</li>
					<li><img src="img/check.png"
						style="width: 20px; height: 20px; float: left; padding-right: 10px;">자동
						온도조절 장치</li>
					<li><img src="img/check.png"
						style="width: 20px; height: 20px; float: left; padding-right: 10px;">실내
						감시 카메라</li>
					<li><img src="img/check.png"
						style="width: 20px; height: 20px; float: left; padding-right: 10px;">원격
						현관문 잠금장치</li>
					<li><img src="img/check.png"
						style="width: 20px; height: 20px; float: left; padding-right: 10px;">마스터
						리모콘</li>
				</ul>
			</div>
				</div>
			</div>
		</div>
	</section>
	<section class="features section text-center"
		style="background: #F6F8FA; padding-top: 80px;">
		<div class="hero-illustration is-revealing">
			<div style="overflow:hidden; width: 100%">
				<img src="img/phone.jpg" style="width:45%; margin-top: 40px; float: left; margin-left: 50px; padding-bottom: 100px;">
				<div style="float: left; margin-left:30px; width: 45%; text-align: left;">
					<div style="margin-top: 150px; font-family:Jua; font-size: 30px; color: #000000">어플 소개</div>
					<div style="margin-top: 10px; font-family:Do Hyeon; font-size: 20px;">'스마트 폰으로도 우리집을!?'</div>
					<div style="font-size: 15px;">
						모바일 세상 속 우리 집!<br/>
						홈페이지에서만 보던 우리집의 기능이 작은 모바일에도 간편하게 담겨있습니다. <br/>
						모바일을 통해 우리집을 제어해보세요!<br/><br/>지금 확인하세요!
					</div>
				</div>
			</div>
		</div>
	</section>
	</main>
	<!--</div> -->
	<script type="text/javascript" src="resources/dist/js/main.min.js"></script>

</body>
</html>

