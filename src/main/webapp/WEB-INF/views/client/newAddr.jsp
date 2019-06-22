<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소 추가</title>
</head>
<link rel="stylesheet"
	href="resources/dist/css/style.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="resources/js/join_check.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d6527f14573836af734688a2d407ee4&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
	/* function go_join(){
		$('form').submit();
	} */

	function go_addr() {
		new daum.Postcode({
			oncomplete : function(data) {
				//console.log(data);
				var address, post;
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
					//console.log(result);
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
						var id = '${login.id}';
						$('[name=nx]').val(lat);
						$('[name=ny]').val(lng);
						$('[name=id]').val(id);
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
<body>
	<div style="height: 1000px; width: 100%">
		<form method="post" action="insertAddr.cs">
			<table style="width: 60%" >
				<tr>
					<th>주소</th>
					<td><input onclick="go_addr()" type="button"
						class="btn btn-lg btn-primary btn-block"
						style="text-align: center; height: 40px; font-size: 15px; width: 130px;"
						value="우편주소 찾기" /> <input type="text" name="post"
						style="width: 70px;" /><br> <input type="text" name="addr"
						style="width: 99%" /></td>
				</tr>
			</table>
			<br>
			<button id="save" class="btn btn-lg btn-primary btn-block"
				type="button" onclick="submit()"
				style="text-align: center; height: 40px; font-size: 15px; width: 120px; margin-left: 10px;">저장</button>
			<button id="cancel" class="btn btn-lg btn-primary btn-block"
				type="button" onclick="location='home'"
				style="text-align: center; height: 40px; font-size: 15px; width: 120px; margin-left: 10px;">취소</button>
			<input type="hidden" id="nx" name="nx" /> 
			<input type="hidden" id="ny" name="ny" />
			<input type="hidden" id="id" name="id" />
		</form>
	</div>
</body>

</html>