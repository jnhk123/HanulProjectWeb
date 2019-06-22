<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="community.CommunityVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet"
	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
</head>
<script type="text/javascript">
	function likeListCheck(){
		$('#like').val('like');
		//alert("12");
// 		$.ajax({
// 			type : 'post',
// 			url : 'likeListCheck.co',
// 			data : {
// 				id: '${login.id }'
// 			},
// 			success : function(data) {
// 				//alert(data);
// 				//console.log("effInitPage==" +data);
// 				//console.log(data);
// 				//console.log("12");
// 				//location.reload();
// 				//console.log(${page.list});
// 				//alert(${page.list});
				
// 				location.reload(data);
// 				location = "list.co";
				
// 			},
// 			error : function(req) {
// 				alert(req.status);
// 			}
// 		});
		$('form').submit();
	}
</script>
<body>
	<div style="max-height: 100%">
	<div><img src="img/communitymain.jpg" style="width: 100%;">
	</div>
<!-- 		<form style="padding: 40px;"> -->
			 <ul style="list-style: none;">
				<c:forEach items="${page.list }" var="vo">
				<li style="float: left; margin-right: 35px; margin-left: 15px; margin-top: 80px; position: relative;">
					<a onclick="location='detail.co?no=${vo.no}&id=${login.id }'">
						<c:if test="${login.id ne null }">
							<c:forEach items="${like_list }" var="like">
								<c:if test="${like.no eq vo.no }">
									<img src="img/starfill.png" style="position:absolute; top:5px; left: 5px; width: 30px; height: 30px; margin: 0px; padding: 0px;">
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${vo.filePath ne null }">
							<img style="width: 240px; height: 240px; border-radius:5px;" alt="file" src="${vo.filePath }">
						</c:if>
						<c:if test="${vo.fileName eq null }">
								<img src="img/emptyimg.png"	style="width: 240px; height: 240px; border-radius:5px;">
						</c:if>
					<div style="text-align:center; font-size: 14px; margin-top: 10px;">${vo.title }</div>
					</a>
				</li>
			</c:forEach>
<!-- 		</form>  -->
	</div>
	<form action="list.co" style="margin-top: 50px;">
			<input type="hidden" name="${login.id }">
			 <input type="hidden" name="curPage">
			 <input type="hidden" name="like" id="like"> 
			<div style="margin-left: 50px;">
				<p style="float: left; display: flex;">
					<select name="search" style="width: 60px; height: 30px; font-size: 12px;">
						<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
						<option value="title" ${page.search eq 'title' ? 'selected' : ''}>제목</option>
						<option value="content"
							${page.search eq 'content' ? 'selected' : ''}>내용</option>
						<option value="like"
							${page.search eq 'like' ? 'selected' : ''}>즐겨찾기</option>
					</select> <input type="text" name="keyword" id="keyword"
						style="margin-left: 5px; width: 300px; height: 30px; font-size: 12px;"
						value="${page.keyword }">
					<input onclick="$('form').submit()" type="button" value="검색" class="btn" 
					style=" margin-left: 5px; font-size: 13px; width: 70px; height: 30px; border-radius: 20px;">
				</p>
			<div>
			<c:if test="${login.id != null}">
				<input type="button" value="글쓰기" class="btn" 
				style="margin-right: 70px;  font-size: 13px; width: 70px; height: 30px; 
				border-radius: 20px; float:right;"
				 onclick="location='insert.co'">
				<input type="button" value="즐겨찾기만 보기" class="btn" 
				style="margin-right: 10px;  font-size: 13px; width: 120px; height: 30px; 
				border-radius: 20px; float:right;"
				 onclick="likeListCheck()">
			</c:if>
			</div>
			</div>
		</form>
	<div style="width: 100%; margin-top: 100px; margin-bottom:50px;" align="center">
			<jsp:include page="/WEB-INF/views/include/page.jsp" />
		</div>
</body>
</html>
