<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<style type="text/css">
.heart {
	width: 50px;
	height: 50px;
}
</style>
</head>
<body>
<div style="min-height: 600px; max-height: 100%;">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >커뮤니티
		<div style="padding-left:40px; margin-right: 40px; margin-top:10px;
			height: 2px; background: #007bff;"></div>
	</div>
	<form style="padding: 40px;">
		<table style="font-size: 15px;">
			<tr>
				<th style="width: 150px; text-align: center; background-color: #f2f6f9;">제목</th>
				<td colspan="2">${vo.title }</td>
				<td style="width: 100px;">
				<!-- 이미지 즐겨찾기  -->
			 	<c:if test="${login.id ne null }">
				<c:choose>
				<c:when test="${like.id eq null }">
					<a onclick="location='liketo.co?no=${vo.no }&id=${login.id }'">
					<img class="heart" style="width: 30px; height: 30px;" 
					src="img/star.png" alt="즐겨찾기"></a>
				</c:when>
				<c:when test="${login.id eq like.id}">
					<a onclick="location='likeCancel.co?no=${vo.no }&id=${login.id }'">
					<img class="heart" style="width: 30px; height: 30px;"
					 src="img/starfill.png" alt="즐겨찾기 해제"></a>
				</c:when> 
				</c:choose>
				</c:if> </td>
			</tr>
			<tr>
				<th style="text-align: center; background-color: #f2f6f9;">작성자</th>
				<td>${vo.writer}</td>
				<th style="width: 100px; text-align: center; background-color: #f2f6f9;">조회수</th>
				<td>${vo.readCnt }</td>
				
			</tr>
			<tr>
				<th rowspan="2" style="text-align: center; background-color: #f2f6f9;">내용</th>
				<c:if test="${vo.filePath != null }">
					<td colspan="3" align="center">
					<img style="margin: 0 auto; margin-top: 20px; width: 30%; border-radius: 5px;" src="${vo.filePath }">
					</td>
				</c:if>
				</tr>
				<tr>
				<td colspan="3"  valign="top" style="height: 600px; max-height: 100%;">
					<div style="font-size: 15px; margin-left: 15px;">
						${fn:replace(vo.content, newLineChar, "<br/>")}</div>
				</td>
				</tr>
		</table>
	</form>
	<div id="btnBox2" align="center" style="margin-top: 100px; margin-bottom: 50px">
		<c:if test="${login.id eq vo.writer}">
		<input type="button" value="수정"
				style="font-size: 13px; width: 80px; height: 35px; border-radius: 20px;" class="btn"
				onclick="location='modifyPage.co?no=${vo.no}'">
		</c:if>
		<c:if test="${login.id eq vo.writer || login.admin eq 'Y'}">
			<input type="button" value="삭제"
				style="font-size: 13px; width: 80px; height: 35px; border-radius: 20px;" class="btn"
				onclick="if(confirm('정말 삭제하시겠습니까?')) {location='delete.co?no=${vo.no}'}">
		</c:if>
		<input type="button" value="목록으로"
			style="font-size: 13px; width: 80px; height: 35px; border-radius: 20px;" class="btn"
			onclick="location='list.co'">
	</div>
	
</div>
<div style="height: 100%; margin-bottom: 50px; margin: 0 auto; width: 95%">
	<jsp:include page="/WEB-INF/views/community/comment/list.jsp"></jsp:include>
</div>
</body>
</html>