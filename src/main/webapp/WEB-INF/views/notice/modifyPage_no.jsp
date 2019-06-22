<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|PT+Sans|Poor+Story&display=swap" rel="stylesheet">

<title>공지사항 수정</title>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

body {
	font-family: 'Nanum Gothic', sans-serif;
}


#attach_file, #delete-file {
	display: none;
}
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
		$('#upload-Type').val('test');
	})

	$(function() {
		//첨부파일명이 있으면 파일삭제 이미지 보이게
		if ('${!empty vo.fileName}' == 'true') {
			$('#delete-file').css('display', 'inline-block');
			$('#file-name').css('padding-right', '15px');
			$('#attach-file').css('display', 'none');
		}
	});

	$(function() {
		$('#attach_file').on('change', function() {
			$('#upload-Type').val('image');
			//첨부파일명 보이게
			$('#file-name').html(this.files[0].name);
			//첨부파일있을때 파일선택이미지와의 간격주기
			//			$('#file-name').css('padding-right', '15px');
			//첨부파일 있을때 파일삭제 이미지 보이게
			$('#delete-file').css('display', 'inline-block');
			$('#attach-file').css('display', 'none');

		});

		$('#delete-file').on('click', function() {
			$('#upload-Type').val('delete');
			$('#attach_file').val('');
			$('#attach-file').css('display', 'inline-block');
			$('#file-name').html('');
			$('#delete-file').css('display', 'none');

		});
	});
</script>
</head>

<body>
<div style="min-height: 1000px; max-height: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;">공지사항 수정
	<div style="padding-left:40px; margin-right: 40px; margin-top:10px;
		height: 2px; background: #007bff;"></div></div>
	<form action="update.no" method="post" enctype="multipart/form-data" style="padding: 40px;">
		<input type="hidden" name=no value="${vo.no }">
		<table summary="테이블 구성" style="font-size: 15px;" >
			<tr>
				<td style="text-align: center; font-weight: bold; background-color: #f2f6f9;">제 목</td>
				<td><input  style="font-size: 15px; width: 30%;" type=text name=title value="${vo.title }"
				class="need" title="제목"></td>
			</tr>
		
			<tr>
				<td style="text-align: center; font-weight: bold; background-color: #f2f6f9;">내 용</td>
				<td><textarea name=content style="font-size: 15px; width: 95%;"
				rows="10" cols="100" class="need" title="내용">${vo.content }</textarea></td>
			</tr>
			<tr>
				<th style="text-align: center; font-weight: bold; background-color: #f2f6f9;">첨부파일</th>
				<td style="text-align: left">
				<label id="file-name">${vo.fileName}</label> 
					<a><img src="img/delete.png" id="delete-file" style="width: 20px; height: 20px;" /></a>
					<label>
					<a><img src="img/select.png" id="attach-file" style="width: 20px; height: 20px;"> </a>
					<input type="file" name="attach_file" id="attach_file" style="display: none;" accept="image/*"/> 
					<input type="hidden" name="uploadType" id="upload-Type">
					</label>
				</td>
			</tr>
			</table>
			<div align="center" id="btnBox2" style="margin-top: 100px;">
				<input class="btn" type="button" value="등록" 
				style="font-size: 13px; width:70px; height:35px; margin-right:5px; border-radius: 20px;" " 
				onclick="if( necessary() ) { $('form').submit() }" ></input>
				<input class="btn" style="font-size: 13px; width:80px; height:35px; border-radius: 20px;" 
				 type="button" value="목록으로" onclick="location='list.no'">
			</div>
	</form>
	<script type="text/javascript" src="resources/js/need_check.js"></script>
</div>
</body>
</html>