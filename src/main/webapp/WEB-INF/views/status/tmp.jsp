<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty my_info}">
	<p style="float: right;">
		${my_info.id } [${my_info.name }] <a class="btn-fill"
			onclick="go_logout()">로그아웃</a>
	</p>
	<table style="width: 40%;">
		<tr>
			<td><c:choose>
					<c:when test="${status_info.getWater() == 0 }">
						물없는 그림
						<img src="img/w1.png" />
					</c:when>

					<c:when test="${status_info.getWater() < 10 }">
						물조금있는 그림
						<img src="img/w2.png" />
					</c:when>

					<c:when
 						test="${status_info.getWater() >= 10 && status_info.getWater() < 20 }"> 
						물 2/3있는 그림
						<img src="img/w3.png" />
					</c:when> --%>

 					<c:when test="${status_info.getWater() >= 20 }">
						물 가득찬 그림
						<img src="img/w4.png" />
 					</c:when> 
 				</c:choose></td>
 			<td><c:choose> --%>
 					<c:when test="${status_info.getLight() == 'Y' }"> 
						<img src="img/lf.jpg" />
					</c:when> 

 					<c:when test="${status_info.getLight() == 'N' }"> 
						<img src="img/le.jpg" />
 					</c:when>
 				</c:choose></td>
			<td>
 				secure 
 			</td> 
 		</tr> 

 		<tr>
 			<td>
 				temper
 			</td> 

 			<td>
 				weather  
 				${weather}
			</td>

 			<td>
 			</td> 
			
		</tr>
	</table>
	 	<p>
	 		${status_info.water }<br/>
	 		${status_info.light }<br/> 
	 		${status_info.timer }<br/> 
	 		${status_info.secure }<br/> 
	 		${status_info.temper }<br/> 
	 		${status_info.weather }<br/> 
	 		${status_info.dust }<br/> 
	 	</p> 
</c:if>

<c:if test="${empty my_info}">
	<p style="float: right;">
		<a class="btn-fill" onclick="get_status()">로그인</a>
	</p>
	<span style="width: 120px; float: right;"> <input type="text"
		id="userid" placeholder="아이디" style="width: 100px; margin-top: 5px" />
		<input onkeypress="if(event.keyCode == 13){ get_status() }"
		type="password" id="userpwd" placeholder="비밀번호"
		style="width: 100px; margin-top: 5px" />
	</span>
</c:if>
</body>
</html>