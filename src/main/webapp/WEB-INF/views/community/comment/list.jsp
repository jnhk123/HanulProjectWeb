<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<style type="text/css">
.text {
	font-size: 0.7em;
	float: left;
	margin: 0 10px;
}

.text:hover {
	cursor: pointer;
}
</style>
<script type="text/javascript">
$(function () {
	$('.textArea').css('display','none');
	$('.textInsert').css('display','none');
});
// 수정을 클릭하면 발생
function modify(no) {
	$(function () {
		$('#textArea'+no).css('display','inline-block');
		$('#text'+no).css('display','none');
		$('#modify'+no).css('display','none');
		$('#insert'+no).css('display','inline');
	});
}
//삭제 클릭시 발생
function del(no) {
	location="com_delete?comu_no="+${vo.no}+"&no="+no;
}
//등록 클릭시 발생
function update(no) {
	$(function() {
		var content = $('#textArea'+no).val();
		$('#TA').val(content);
		$('#u_no').val(no);
		$('#com_form').submit();
	});
}
//글 등록
function insert(no) {
	$(function() {
		var content = $('#in_textArea').val();
		$('#in_TA').val(content);
		$('#com_in_form').submit();
	});
}
</script>
</head>
<body >
<div style="height: 100%; margin-bottom: 40px; margin-left: 25px;">
<div style="height: 1px; background: #cccccc;"></div>
	<c:if test="${!empty login.id }">
	
		<div style=" margin-top: 40px; margin-bottom: 50px;">
			<span style="font-size: 15px; margin-left:30px; margin-right:60px; float:left;">${login.id }</span>
			<input type="text" id="in_textArea" 
				style="float:left; width: 70%; font-size: 15px;"></input> 
				<input class="btn" value="등록" type="button"
				style="font-size: 13px; width: 70px; height: 30px; float: left; margin-left: 10px;
				 border-radius: 20px;" onclick="insert()" class="text">
				</input>
		</div>
	</c:if>
	<!-- 인서트용 폼 -->
	<form method="post" id="com_in_form" action="com_insert">
		<input type="hidden" name="writer" value="${login.id }"> 
		<input type="hidden" name="content" id="in_TA"> 
		<input type="hidden" name="comu_no" value="${vo.no }">
	</form>
	<!-- 업데이트용 폼 -->
	<form method="post" id="com_form" action="com_update">
		<input type="hidden" name="content" id="TA"> 
		<input type="hidden" name="no" id="u_no">
		<input type="hidden" name="comu_no" value="${vo.no }">
	</form>
	<br />
	<table align="center" style="width: 90%;">
		<c:forEach items="${com_list }" var="com_vo">
			<tr>
				<td>
					<p class="text">${com_vo.writer}</p>
				</td>
				<td>
					<p class="text" style="width: 550px;"id="text${com_vo.no}">${com_vo.content }
					<p>
						<input style="font-size: 15px; width: 500px; margin-top: 20px;" class="textArea"
							id="textArea${com_vo.no}" value="${com_vo.content }"></input>
				</td>
				<td>
				<c:if test="${com_vo.writer eq login.id || login.id eq 'admin' }">
					<div class="text" onclick="modify(${com_vo.no})"
						id="modify${com_vo.no}">수정</div>
					<div class="textInsert text" onclick="update(${com_vo.no})"
						id="insert${com_vo.no}">등록</div>
					<div class="text"
						onclick=" if(confirm('삭제하시겠습니까?')) {del(${com_vo.no })} "
						id="delete${com_vo.no}">삭제</div>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>