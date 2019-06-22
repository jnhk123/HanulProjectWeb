<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"	href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

body {
	font-family: 'Nanum Gothic', sans-serif;
}
#attach_file, #delete-file { display: none; }
</style>
<script type="text/javascript">
	function move(url) {
		location.href = url;
	}
	function boardWriteCheck() {
		var form = document.BoardWriteForm;
		return true;
	}
</script>
<script type="text/javascript">
	$(function() {
		//첨부파일명이 있으면 파일삭제 이미지 보이게
		if ('${!empty vo.fileName}' == 'true') {
			$('#delete-file').css('display', 'inline-block');
			$('#file-name').css('padding-right', '15px');
			$('#attach-file').css('display','none');
		}
	});

	$(function() {
		$('#attach_file').on('change', function() {
			$('#upload-Type').val('image');
			//첨부파일명 보이게
			$('#file-name').html(this.files[0].name);
			//첨부파일있을때 파일선택이미지와의 간격주기
// 			$('#file-name').css('padding-right', '15px');
			//첨부파일 있을때 파일삭제 이미지 보이게
			$('#delete-file').css('display', 'inline-block');
			$('#attach-file').css('display','none');
		});

		$('#delete-file').on('click', function() {
			$('#upload-Type').val('delete');
			$('#attach_file').val('');
			$('#attach-file').css('display','inline-block');
			$('#file-name').html('');
			$('#delete-file').css('display', 'none');
		});
	});
</script>
</head>

<body>
<div style="min-height: 1000px; max-height: 100%">
	<form action="update.qu" method="post" enctype="multipart/form-data" style="padding: 40px;">
		<input type="hidden" value="${vo.no }" name="no">
		<table summary="테이블 구성" style="font-size: 15px;">
			<tr>
				<td>작성자</td>
				<td><input type="hidden" name=writerID size=10 maxlength=8 class="need" title="아이디" value="${login.id }">${login.id }</td>
			</tr>
			<tr>
				<td>제 목</td>
				<td><input type=text name=title value="${vo.title }" style="font-size: 15px; width: 30%"
				 class="need" title="제목"></td>
			</tr>
			<tr>
				<td>내 용</td>
				<td><textarea name=content style="font-size: 15px; width: 95%"
				 rows="10" cols="100" class="need" title="내용">${vo.content }</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td style="text-align: left">
				<label id="file-name" style="font-size: 15px;">${vo.fileName}</label>
				<a><img src="img/delete.png" id="delete-file" style="width: 25px; height: 25px;" /> </a>
				<label> 
					<a><img src="img/select.png" id="attach-file" style="width: 25px; height: 25px;"/></a>
					<input type="file" name="attach_file" id="attach_file" style="display: none;" accept="image/*"/>
					<input type="hidden" name="uploadType" id="upload-Type">
				</label>
				</td>
			</tr>
		</table>
		<div align="center" style="margin-top: 100px;">
				<input type="button" style="width: 70px; height: 35px; font-size: 15px;" class="btn" 
				 value="등록" onclick="if( necessary() ) { $('form').submit() }" ></input>&nbsp;&nbsp;
				<input type="button"  style="width: 80px; height: 35px; font-size: 15px;" class="btn" 
				 value="목록으로" onclick="location='list.qu'">
		</div>
		<input type="hidden" name="attach" />
	</form>
	</div>
	<script type="text/javascript" src="resources/js/need_check.js"></script>
</body>
</html>