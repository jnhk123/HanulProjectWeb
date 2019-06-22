<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
body {
	font-family: 'Nanum Gothic', sans-serif;
}
 #delete-file { display: none; }
</style>
<script type="text/javascript">
	function move(url) {
		location.href=url;
	}
	function boardWriteCheck() {
		var form = document.BoardWriteForm;
		return true;
	}
</script>
<script type="text/javascript">

	$(function() {
		$('#attach_file').on('change', function() {
			//첨부파일명 보이게
			$('#file-name').html(this.files[0].name);
			//첨부파일있을때 파일선택이미지와의 간격주기
// 			$('#file-name').css('padding-right', '15px');
			//첨부파일 있을때 파일삭제 이미지 보이게
			$('#delete-file').css('display', 'inline-block');
			$('#attach-file').css('display','none');
			$('#upload-Type').val('image');
		});

		$('#delete-file').on('click', function() {
			$('#attach_file').val('');
			$('#attach-file').css('display','inline-block');
			$('#file-name').html('');
			$('#delete-file').css('display', 'none');
			$('#upload-Type').val('delete');
		});
	});
</script>
</head>

<body>
<div style="min-height: 1000px; max-height: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >커뮤니티 작성
         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
            height: 2px; background: #007bff;"></div>
      </div>
	<form action="insertData.co" method="post" enctype="multipart/form-data" style="padding: 40px;">
	<table summary="테이블 구성" style="font-size: 15px;">
    	<tr>
			<th  style="text-align: center; background-color: #f2f6f9;">작성자</th>
			<td>
				<input type="hidden" name="writer" size=10 maxlength=8 value="${login.id }" class="need" title="아이디">
				${login.id }
			</td>
		</tr>
    	<tr>
     		<th  style="text-align: center; background-color: #f2f6f9;">제 목</th>
     		<td><input style="width: 30%; font-size: 15px;" type="text" 
     			name="title" class="need" title="제목"></td>
    	</tr>
    	<tr>
     		<th  style="text-align: center; background-color: #f2f6f9;">내 용</th>
     		<td><textarea name="content" style="width: 95%; font-size: 15px;"
     		rows ="10" cols="100" class="need" title="내용"></textarea></td>
    	</tr>
    	<tr>
				<th  style="text-align: center; background-color: #f2f6f9;">이미지 첨부</th>
				<td style="text-align: left"><img type="hidden" style="width: 25px; height: 25px;" src="img/delete.png" id="delete-file" /> 
				<label id="file-name"></label> 
				<label>
					<img src="img/select.png" style="width: 25px; height: 25px;" id="attach-file"/> 
					<input type="file" name="attach_file" id="attach_file" style="display: none;" accept="image/*" />
					<input type="hidden" name="uploadType" id="upload-Type">
				</label></td>
			</tr>
    </table>
<!--     	<tr> -->
<!--      		<td>비밀번호</td>  -->
<!--      		<td><input type=password name=qPassword size=15 maxlength=15></td> -->
<!--     	</tr> -->
    	
    	
     		<div align="center" id="btnBox2">
     		<input  class="btn" type="button" style="font-size: 13px;  width: 70px; height: 30px; border-radius: 20px;"
     		 onclick="if( necessary() ) { $('form').submit() }" value="등록"></input>&nbsp;&nbsp;
         	<input  class="btn" type="button" style="font-size: 13px;  width: 80px; height: 30px;  border-radius: 20px;"
         	value="목록으로" onclick="location='list.co'"></input></div>
     	

	</form>
	<script type="text/javascript" src="resources/js/need_check.js"></script>
	</div>
</body>
</html>