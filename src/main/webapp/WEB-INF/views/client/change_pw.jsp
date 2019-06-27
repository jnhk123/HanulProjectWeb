<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<script type="text/javascript" src="resources/js/need_check.js"></script>
<script type="text/javascript" src="resources/js/join_check.js"></script>
<style type="text/css">
.valid, .invalid {
	font-size: 9pt;
	font-weight: bold;
}
.valid {
	color: green;
}
.invalid {
	color: red;
}
</style>
<script type="text/javascript">
function changePw() {
	//비밀번호
	if (!item_check('pw'))
		return;
	//비밀번호 확인
	if (!item_check('pwd_ck'))
		return;
	if(confirm('새로운 비밀번호로 변경하시겠습니까?')){
		$('form').submit();
	}
}

function validate(tag) {
	var data = $('[name=' + tag + ']').val();
	if (tag == 'pw') {
		data = join.pwd_status(data);
	} else if (tag == 'pwd_ck') {
		data = join.pwd_ck_status(data);
	}

	$('#' + tag + '_status').text(data.desc);
	$('#' + tag + '_status').removeClass('valid invalid');
	$('#' + tag + '_status').addClass(
			data.code == 'valid' ? 'valid' : 'invalid');
	return data;
}

function item_check(item) {
	//유효한 형태인지 부터 판단
	var data = validate(item);
	if (data.code != 'valid') {
		alert(data.desc);
		$('[name=' + item + ']').focus();
		return false;
	} else {
		return true;
	}
}

</script>


</head>
<body>
<div style="height: 1000px; width: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >비밀번호 변경
	         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
	            height: 2px; background: #007bff;"></div>
	      </div>
		<form style="padding: 50px;" action="new.pw" method="post">
		<input type="hidden" name="id" value="${id }">
	<table class="type07" style="font-size: 13px;">
		<tr>
			<th>새로운 비밀번호</th>
			<td colspan="2"><input type="password" id="pw" name="pw" style="font-size: 13px; width:  250px;" onkeyup="validate('pw')">
			<div style="margin-top: 10px;" id="pw_status" class="valid">영소문자와 숫자를 사용해주세요</div>
		</td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" id="check_pw" name="pwd_ck" style="font-size: 13px; width:  250px;" onkeyup="validate('pwd_ck')">
				<div style="margin-top: 10px;" id="pwd_ck_status" class="valid">비밀번호를 확인해주세요</div>
			</td>
			<td><input type="button" class="btn" value="비밀번호 변경" onclick="changePw()"
				style="font-size: 13px; border-radius:10px; height: 35px;"></td>
		</tr>
	</table>
	<div align="center" style="font-size: 13px;">
	<span id="fpw"></span><span id="findpw"></span><span id="find"></span>
		</div>
			<div align="center" style="margin-top: 100px;">
			<input onclick="location='home'" class="btn" type="button" value="홈으로" 
			style="font-size: 13px; border-radius:10px; height: 35px">
			</div> 
		</form>
	</div>
	
</body>
</body>
</html>