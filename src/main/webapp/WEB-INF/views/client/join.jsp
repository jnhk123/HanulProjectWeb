<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/table.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="resources/js/join_check.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d6527f14573836af734688a2d407ee4&libraries=services,clusterer,drawing"></script>

<style type="text/css">
input[name=phone], input[name=post] {
	width: 50px;
	text-align: center;
}
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
<script>
	/* $('#post').on('change', function() {
		$('#post').html(this.files[0].name);
		$('#delete-file').css('display', 'inline-block');
		$('#attach-file').css('display','none');
		$('#upload-Type').val('image');
	}); */

</script>
<script type="text/javascript">
	function go_post() {
		new daum.Postcode({
			oncomplete : function(data) {
				//console.log(data);
				var address, post, nx, ny;
				//주소-좌표 변환 객체를 생성합니다
				var geocoder = new daum.maps.services.Geocoder();
				//사용자의 선택에 달려있다.: userSelectedType(J/R)

				//지번: 주소 jibunAddress
				if (data.userSelectedType == 'J') {
					address = data.jibunAddress;
					post = data.postcode;
				} else {
					//도로명: 주소 roadAddress
					post = data.zonecode;
					address = data.roadAddress;
					var extra = '';
					if (data.bname != '')
						extra = data.bname;
					if (data.buildingName != '')
						extra += (extra == '' ? '' : ',') + data.buildingName;
					if (extra != '')
						address += '(' + extra + ')';
				}
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(address, function(result, status) {
					console.log(result);
					//console.log(status);
					// 정상적으로 검색이 완료됐으면 
					if (status === daum.maps.services.Status.OK) {
						var coords = new daum.maps.LatLng(result[0].y,
								result[0].x);
						//alert(coords);
						//위경도 파싱
						var string = JSON.stringify(coords);
						//alert(string);
						var a = string.split(',');
						a[0] = (a[0].split(":"))[1];
						a[1] = (a[1].split(":"))[1];
						a[1] = (a[1].split("}"))[0];
						var lat = a[0];
						var lng = a[1];
						$('[name=nx]').val(lat);
						$('[name=ny]').val(lng);
					} else {
						alert("위경도 에러")
					}
				});

				$('[name=post]').val(post);
				//$('[name=address]').eq(0).val(address);
				$('[name=addr]:eq(0)').val(address);
			}
		}).open();
	}
</script>

<script>
	function validate(tag) {
		var data = $('[name=' + tag + ']').val();
		if (tag == 'id') {
			data = join.id_status(data);
		} else if (tag == 'pw') {
			data = join.pwd_status(data);
		} else if (tag == 'pwd_ck') {
			data = join.pwd_ck_status(data);
		} else if (tag == 'email'){
			data = join.email_status(data);
		} else if (tag == 'post'){
			data = join.post_status(data);
		}

		$('#' + tag + '_status').text(data.desc);
		$('#' + tag + '_status').removeClass('valid invalid');
		$('#' + tag + '_status').addClass(
				data.code == 'valid' ? 'valid' : 'invalid');
		return data;
	}

	function usable() {
		//alert("아이디체크");
		var result = validate('id');
		if (result.code != 'valid') {
			alert(result.desc);
			return;
		}

		$.ajax({
			type : 'post',
			url : 'id_check',
			data : {
				id : $('[name=id]').val()
			},
			//멤버컨트롤러의 아이디 중복 확인 요청의 값을 가져옴
			success : function(data) {
				data = join.id_usable(data);
				$('#id_status').text(data.desc);
				$('#id_status').removeClass('valid invalid');
				$('#id_status').addClass(
						data.code == 'usable' ? 'valid' : 'invalid');
				$('#id_check').val(data.code); //usable | unusable
			},
			error : function(req) {
				alert(req.status);
			}
		});
	}

	function go_join() {
		if ($('[name=name]').val() == '') {
			alert('이름을 입력해주세요');
			$('[name=name]').focus();
			return;
		}
		//각 항목의 유효성을 확인한다.
		//아이디
		//중복확인하지 않은 경우
		if ($('#id_check').val() == 'n') {
			//유효한 조합인지부터 판단
			if (!item_check('id'))
				return;
			else {
				alert(join.id.valid.desc);
				$('#btn_usable').focus();
				return;
			}
		} else if ($('#id_check').val() == 'unusable') {
			//유효하지 않은 경우: 이미 사용중인 경우	
			alert(join.id.unusable.desc);
			$('[name=id]').focus();
			return;
		}
		if (!item_check('id'))
			return;
		//비밀번호
		if (!item_check('pw'))
			return;
		//비밀번호 확인
		if (!item_check('pwd_ck'))
			return;
		//이메일 확인
		if (!item_check('email'))
			return; 
		//주소		
		if(!item_check('post'))
			return;
		
		$('form').submit();
		alert("회원가입 되었습니다.")
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
<body>
	<div style="height: 1500px; width: 100%">
	<div style="margin-left: 40px; font-size: 30px; font-family: Jua;" >회원가입
         <div style="padding-left:40px; margin-right: 40px; margin-top:10px;
            height: 2px; background: #007bff;"></div>
      </div>
		<form method="post" action="insert.cs" style="padding: 40px;">
			<table class="type07" align="center" style="font-size: 15px; width: 80%">
			<tr>
				<th style="width: 150px"><div style="font-size: 18px; color:red; float:left;">*</div>성명</th>
				<td><input type="text" name="name" style="width: 30%; height: 35px; font-size: 15px;"/></td>
			</tr>
			<tr>
				<th>아이디<div style="font-size: 18px; color:red; float:left;">*</div></th>
				<td><input type="text" name="id" style="float:left;"
					onkeyup="$('#id_check').val('n'); validate('id')" /> 
					<input type="button" id="btn_usable" value="중복확인" class="btn" onclick="usable()" 
					style="height: 35px; font-size: 13px; width: 80px; margin-left:10px;" />
					<div style="margin-top: 10px;"id="id_status" class="valid">아이디를 입력해주세요(영어 소문자와 숫자만 사용해주세요)</div></td>
			</tr>
			<tr>
				<th>비밀번호<div style="font-size: 15px; color:red; float:left;">*</div></th>
				<td><input type="password" name="pw" onkeyup="validate('pw')" />
				<div style="margin-top: 10px;" id="pw_status" class="valid">영소문자와 숫자를 사용해주세요</div></td>
			</tr>
			<tr>
				<th>비밀번호 확인<div style="font-size: 15px; color:red; float:left;">*</div></th>
				<td><input type="password" name="pwd_ck" onkeyup="validate('pwd_ck')" />
					<div style="margin-top: 10px;" id="pwd_ck_status" class="valid">비밀번호를 확인해주세요</div></td>
			</tr>
			<tr>
				<th><div style="font-size: 15px; color:red; float:left;">*</div>이메일</th>
				<td><input type="text" name="email" id="email"
					onkeyup="validate('email')" /><br>
					<div id="email_status" class="valid">이메일을 입력하세요</div></td>
			</tr>
			<tr>
				<th><div style="font-size: 15px; color:red; float:left;">*</div>주소</th>
				<td><input onclick="go_post()" type="button" 
					class="btn btn-lg btn-primary btn-block"
					style="text-align: center; height: 35px; width: 120px; font-size: 13px;" value="우편주소 찾기" /> 
					<input type="text" name="post" style="width: 20%; margin-top: 5px;" /><br> 
					<input type="text" name="addr" style="width: 99%; margin-top: 5px; font-size: 15px;"/>
					<input type="text" name="addr" style="width: 99%; margin-top: 5px; font-size: 15px;"/>
					<div id="post_status" class="valid">주소를 입력하세요</div>
				</td>
			</tr>
			<tr>
				<th></th>
				<td style="font-size: 12px; color: black; height: 30px;">
				<div style="font-size: 18px; color:red; float:left; margin-top:3px;">*</div>&nbsp 은 필수 사항입니다.</td>
			</tr>
			</table>
		<div style="width: 100%; margin-top: 80px;" align="center">
			<button id="save" class="btn" type="button" onclick="go_join()" 
			style="height: 35px; font-size: 13px; border-radius:20px; width: 70px;">저장</button>
			<button id="cancel" class="btn" type="button" onclick="location='home'"
			style="height: 35px; border-radius:20px;  font-size: 13px;
			width: 70px; margin-left: 10px;">취소</button>
		</div>
		<input type="hidden" id="id_check" />
		<input type="hidden" id="nx" name="nx"/>
		<input type="hidden" id="ny" name="ny"/>
		</form>
		
	</div>
</body>
</html>