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
#download {
	width: 30px;
	height: 30px;
	float: left;
}

#download:hover {
	cursor: pointer;
}

#fileName {
	margin-left: 10px;
	float: left;
}
</style>
</head>
<body>
	<div style="min-height: 1000px; max-height: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >문의사항
         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
            height: 2px; background: #007bff;"></div>
      </div>
		<form style="padding: 40px;">
			<table style="font-size: 15px;">
				<tr>
					<th style="width:100px; text-align:center;  background-color: #f2f6f9;">제목</th>
					<td colspan="3"  style="font-size: 15px;">${vo.title }</td>
				</tr>
				<tr>
					<th  style="text-align:center;  background-color: #f2f6f9;">작성자</th>
					<td>${vo.writerID }</td>
				</tr>
				<tr style="height: 600px; max-height: 100%;">
					<th style="width:100px; text-align:center;  background-color: #f2f6f9;">본문</th>
					<td colspan="3" valign="top"
					style="font-size: 15px; min-height: 600px; max-height: 100%;">
							${fn:replace(vo.content, newLineChar, "<br/>")}</td>
				</tr>
				<c:if test="${!empty vo.fileName }">
					<tr>
						<th style="text-align:center;  background-color: #f2f6f9;">첨부파일</th>
						<td colspan="3">
							<span id="fileName">${vo.fileName }</span>
							<img alt="download" src="img/downicon.png" id="download" 
								style="width: 18px; height: 18px; margin-top: 5px; margin-left: 20px;"
								onclick="location='cmDownload.qu?no=${vo.no}'">
						</td>
					</tr>
				</c:if>
			</table>
			<div id="btnBox2" align="center" style="margin-top:100px;">
				<c:if test="${login.id eq vo.writerID || login.admin eq 'Y'}">
					<input type="button" value="수정" class="btn" style="font-size: 13px; width: 70px; height: 30px; border-radius: 20px;"
						onclick="location='modifyPage.qu?no=${vo.no}'">
					<input type="button" value="삭제" class="btn"  style="font-size: 13px; width: 70px; height: 30px;  border-radius: 20px;"
						onclick="if(confirm('정말 삭제하시겠습니까?')) {location='delete.qu?no=${vo.no}'}">
				</c:if>
				<input type="button" value="목록으로" class="btn"  style="font-size: 13px; width: 80px; height: 30px;  border-radius: 20px;"
					onclick="location='list.qu'">
			</div>
		</form>
	</div>
</body>

</html>