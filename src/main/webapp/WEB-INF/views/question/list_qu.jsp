<%@page import="qa.QaVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet"
	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
</head>
<body>
<div style="min-height: 1000px; max-height: 100%">
	<div><img src="img/qamain.jpg" style="width: 100%;"></div>
	<form style="padding: 40px;">
		<table class="type07" align="center">
			<thead>
				<tr>
					<th style="text-align: center; width: 100px;">번호</th>
					<th>제목</th>
					<th style="text-align: center; width: 250px;">작성자</th>
					<th style="text-align: center; width: 150px;">첨부파일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list }" var="vo">
					<tr>
						<td style="font-size: 15px; text-align: center;">${vo.r }</td>
						<td style="font-size: 15px;"><a
							onclick="location='detail.qu?no=${vo.no}'">${vo.title }</a></td>
						<td style="font-size: 15px; text-align: center;">${vo.writerID }</td>
						<td><c:if test="${vo.fileName ne null}">
								<img src="img/downicon.png"
									style="width: 18px; height: 18px; margin: 0 auto;">
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<form action="list.qu">
		<input type="hidden" name="curPage">
		<div style="margin-left: 50px;">
			<p style="float: left; display: flex;">
				<select name="search" style="width: 60px; height: 30px; font-size: 12px;">
					<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
					<option value="title" ${page.search eq 'title' ? 'selected' : ''}>제목</option>
					<option value="content"
						${page.search eq 'content' ? 'selected' : ''}>내용</option>
				</select> 
				<input type="text" name="keyword" id="keyword" value="${page.keyword }"
					style="margin-left: 5px; width: 300px; height: 30px; font-size: 12px;"> 
				<input type="button" class="btn" onclick="$('form').submit()" value="검색"
					style="margin-left: 5px; font-size: 13px; width: 70px; height: 30px;  border-radius:20px;">
			</p>
			<c:if test="${!empty login.id }">
				<input type="button" value="글쓰기" class="btn"
					style="font-size: 13px; width: 70px; height: 30px; float: right; border-radius:20px;
					 margin-right: 70px;"
					onclick="location='insert.qu'">
			</c:if>
		</div>
	</form>
	<div align="center" style="margin-top: 100px; margin-bottom: 50px;">
		<jsp:include page="/WEB-INF/views/include/page.jsp" />
	</div>
</div>
</body>
</html>