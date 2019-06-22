<%@page import="notice.NoticeVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>공지사항</title>
<head>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|PT+Sans|Poor+Story&display=swap" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">

</head>
<body style="height:100%; width: 100%">
<div style="height:100%; width: 100%">
	<div><img src="img/noticemain.jpg" style="width: 100%;">
	</div>
	<form style="padding: 40px; font-stretch: wider;">
	<table class="type07" align="center">
		<thead>
			<tr>
				<th style="text-align: center;">번호</th>
				<th scope="cols">제목</th>
				<th style="text-align: center;" scope="cols">작성일자</th>
				<th style="text-align: center;" scope="cols">조회수</th>
				<th style="text-align: center;" scope="cols">첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list }" var="vo">
				<tr>
					<td style="font-size: 15px; text-align: center;">${vo.r }</td>
					<td style="font-size: 15px" ;class="type07_title">
					<a onclick="location='detail.no?no=${vo.no}'">${vo.title }</a></td>
					<td style="font-size: 15px; text-align: center;">${vo.writeDate }</td>
					<td style="font-size: 15px; text-align: center;">${vo.readCnt }</td>
					<td><c:if test="${vo.fileName ne null }">
							<img src="img/downicon.png"
								style="padding: 0; width: 18px; height: 18px; margin: 0 auto;">
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>
	<form action="list.no">
		<input type="hidden" name="curPage">
		<div style="margin-left: 40px;">
			<p style="float: left; display: flex; margin-top: 5px;">
				<select name="search" style="width: 60px; height: 30px; font-size: 12px;">
					<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
					<option value="title" ${page.search eq 'title' ? 'selected' : ''}>제목</option>
					<option value="content"
						${page.search eq 'content' ? 'selected' : ''}>내용</option>
				</select> 
				<input type="text" name="keyword" id="keyword"
					style="margin-left: 10px; width: 300px; height: 30px; font-size: 12px;" 
					value="${page.keyword }">
					<input  onclick="$('form').submit()" type="button" value="검색" class="btn" style=" margin-left: 5px; font-size: 13px; 
					width: 70px; height: 30px; border-radius: 20px;">
		
			</p>
			<div align="right" style="margin-right: 70px;">
			<c:if test="${login.id eq 'admin' }">
				<input type="button" value="글쓰기" class="btn" 
				style="margin-left: 5px;  font-size: 13px; width: 70px; height: 30px; border-radius: 20px;"
				 onclick="location='insert.no'">
				<input type="button" value="전체알림" class="btn" 
				style="font-size: 13px; width: 70px; height:30px; border-radius: 20px; margin-left: 5px;"
				onclick="location='allPush.push.android'">
			</c:if>
			</div>
		</div>
	</form>
	<div style="width: 100%; margin-top: 100px; margin-bottom:50px;" align="center">
		<jsp:include page="/WEB-INF/views/include/page.jsp" />
	</div>
	</div>
</body>
</html>