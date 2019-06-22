<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d6527f14573836af734688a2d407ee4&libraries=services,clusterer,drawing"></script>
<style type="text/css">
/* img { */
/* 	width: 200px; */
/* 	height: 200px; */
/* } */
</style>
</head>

<body>
	<c:if test="${login.id eq null }">
		<div style="font-size: 30px; margin: 20px;">로그인 후 사용 가능합니다.</div>
	</c:if>

	<c:if test="${login.id ne null }">
		<a onclick="href='newAddr'">집 주소 추가하기</a>

		<div id="homeAddr"></div>
		<%-- <c:choose>
			<c:when test="${login.addr != null }">
				<div id="dust_list" style="border: 1px solid black; width: 500px;">
					<select style="float: left;" id="gu">
						<option value="">구 선택</option>
						<option value="광산구">광산구</option>
						<option value="서구">서구</option>
						<option value="동구">동구</option>
						<option value="북구">북구</option>
						<option value="남구">남구</option>
					</select><br> <br>
					<div id="dPrint" style="float: left;"></div>
				</div>

				<div id="weather" style="border: 1px solid black; width: 500px;">
					<div id="wPrint"></div>
				</div>

				<!-- 카카오 지도 -->
				<div id="map"
					style="border: 1px solid gray; width: 500px; height: 400px;"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d6527f14573836af734688a2d407ee4"></script>

				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					    mapOption = {
					        center: new daum.maps.LatLng(37.56682, 126.97865), // 지도의 중심좌표
					        level: 5, // 지도의 확대 레벨
					        mapTypeId : daum.maps.MapTypeId.ROADMAP // 지도종류
					    }; 
					// 지도를 생성한다 
					var map = new daum.maps.Map(mapContainer, mapOption); 
					
					
					// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
					if (navigator.geolocation) {
					    
					    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
					    navigator.geolocation.getCurrentPosition(function(position) {
					        
					        var lat = position.coords.latitude, // 위도
					            lon = position.coords.longitude; // 경도
					            alert(lat);
					            alert(lon);
				          	/* //마커를 표시할 위치와 title 객체 배열
							var positions = [
								{
									title: '현 위치',
									latlng: new daum.maps.Latlng(lat, lon);
								} 
							];
				          	*/
					        
					         // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
					        var locPosition = new daum.maps.LatLng(lat, lon);
					        
					        // 마커를 표시합니다
					        displayMarker(locPosition);
					            
					      });
					    
					} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치 설정합니다
					    var locPosition = new daum.maps.LatLng(33.450701, 126.570667); 
					    displayMarker(locPosition);
					}

					// 지도에 마커와 인포윈도우를 표시하는 함수입니다
					function displayMarker(locPosition) {

					    // 마커를 생성합니다
					    var marker = new daum.maps.Marker({  
					        map: map, 
					        position: locPosition
					    }); 
					    // 지도 중심좌표를 접속위치로 변경합니다
					    map.setCenter(locPosition);      
					}    
					/* function setCenter() {            
					    // 이동할 위도 경도 위치를 생성합니다 
					    var moveLatLon = new daum.maps.LatLng(33.452613, 126.570888);
					    
					    // 지도 중심을 이동 시킵니다
					    map.setCenter(moveLatLon);
					}
					function panTo() {
					    // 이동할 위도 경도 위치를 생성합니다 
					    var moveLatLon = new daum.maps.LatLng(33.450580, 126.574942);
					    
					    // 지도 중심을 부드럽게 이동시킵니다
					    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
					    map.panTo(moveLatLon);            
					}         */
				</script>
				
			</c:when>
		</c:choose> --%>
		<input type="hidden" name="nx" id="geoX">
		<input type="hidden" name="ny" id="geoY">
	</c:if>
</body>

<script type="text/javascript">
	/* $(document).on('change', '#addr', function(){
		addr_status_list();
	}); */
	
	$(document).ready(function() {
		var i = 0;
		$.ajax({
			type: 'post',
			url: 'getAddr',
			data: {id: '${login.id}' },
			success: function(data){
				console.log(data);
				var tag = '<select id="addr" style="width:500px;">' + '<option value=""> 집 선택 </option>';
				for( i = 0; i<data.length; i++ ){
					tag += '<option class="addrOption" value="' + data[i].addr + '">' + data[i].addr + '</option>'
				}
				
				tag += '</select>';
				//tag += '<a id = "search"; onclick="status_list()";>조회</a>'
				tag += '</table>';
				$('#homeAddr').html(tag);
				
			},
			error: function(req){
				alert(req.status);
			}
		})
	}); 
	
	$(document).on('change', '#addr', function(){
		var idx = $('#addr option').index( $('#addr option:selected') ) ;
		var Waddr =  $('#addr option:eq('+idx+')').text(); 
		//주소-좌표 변환 객체를 생성합니다
		
		var address, post;
		var geocoder = new daum.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(Waddr, function(result, status) {
			// 정상적으로 검색이 완료됐으면 
			if (status === daum.maps.services.Status.OK) {
				var coords = new daum.maps.LatLng(result[0].y, result[0].x);
				//위경도 파싱
				var string = JSON.stringify(coords);
				var a = string.split(',');
				a[0] = (a[0].split(":"))[1];
				a[1] = (a[1].split(":"))[1];
				a[1] = (a[1].split("}"))[0];
				var lat = a[0];
				var lng = a[1];
				$('[name=geoX]').val(lat);
				$('[name=geoY]').val(lng);
			} else {
				alert("위경도 에러")
			}
			Wdust(lat, lng);
		});
	});
	
	function Wdust(lat, lng){
		alert(lat);
		alert(lng);
		$.ajax({
			type: 'post',
			url: 'Wdust',
			data: {geoX: lat, geoY: lng },
			success: function(data){
				console.log(data);
				
			},
			error: function(req){
				alert(req.status);
			}
		});	
	}
	

$(document).on('change', '#gu', function(){
	$('#dPrint').html('');
	$('#wPrint').html('');
	if($('#gu').val() == ''){
		
	}else{
		if($('#gu').val() == '동구'){
			$('#nx').val(60);
			$('#ny').val(74);
		}else if($('#gu').val() == '서구'){
			$('#nx').val(59);
			$('#ny').val(74);
		}else if($('#gu').val() == '남구'){
			$('#nx').val(59);
			$('#ny').val(74);
		}else if($('#gu').val() == '북구'){
			$('#nx').val(60);
			$('#ny').val(75);
		}else if($('#gu').val() == '광산구'){
			$('#nx').val(58);
			$('#ny').val(76);
		}   
		weather();
		dust_list();
	}
});

function dust_list(){
	$.ajax({
		url: 'dust',
		data: {gu: $('#gu').val()},
		success: function(data){
			var pm10t = '';
			var pm25t = '';
			if(0 <= data.pm10 && 30 >= data.pm10){
				pm10t = ' 매우 좋음 ';
			}else if(31 <= data.pm10 && 80 >= data.pm10){
				pm10t = ' 좋음 ';
			}else if(81 <= data.pm10 && 150 >= data.pm10){
				pm10t = ' 나쁨 ';
			}else if(151 <= data.pm10){
				pm10t = ' 매우 나쁨 ';
			}
			if(0 <= data.pm25 && 15 >= data.pm25){
				pm25t= ' 매우 좋음 ';
			}else if(16 <= data.pm25 && 35 >= data.pm25){
				pm25t = ' 좋음 ';
			}else if(36 <= data.pm25 && 75 >= data.pm25){
				pm25t = ' 나쁨 ';
			}else if(76 <= data.pm25){
				pm25t = ' 매우 나쁨 ';
			}
			
			var tag = '<p>'+ data.cityName
			+ '<br/>미세먼지 : '+ pm10t + '(' + data.pm10 + ')'
			+ '<br/>초미세먼지 : '+ pm25t + '(' + data.pm25 + ')' 
			+ '</p>' 
			$('#dPrint').append(tag);
		},
		error: function(req){
			alert(req.status);
		}
	})
}

function weather() {
	$.ajax({
		type: 'post',
		data: {nx: $('#nx').val(), ny: $('#ny').val()},
		dataType : 'json',
		url: 'weather.st',
		success : function(data) {
			var tag = '<table>';
			var da = data.response.body.items.item
			console.log(da);
			$(da).each(function( idx, item ) {
				if(item.category == 'POP'){
					tag+= '<tr><th>강수확률</th><td>'+ item.fcstValue +'%</td></tr>';
				}else if(item.category == 'PTY'){
					tag+= '<tr><th>강수형태</th><td>';
					switch(item.fcstValue) {
					case 0 :
					tag+= '없음';
					break;
					case 1 :
					tag+= '비';
					break;
					case 2 :
					tag+= '눈과비';
					break;
					case 3 :
					tag+= '눈';
					break;
					}
					tag+='</td></tr>';
				}else if(item.category == 'REH'){
					tag+= '<tr><th>습도</th><td>'+ item.fcstValue +'%</td></tr>';
				}else if(item.category == 'SKY'){
					tag+= '<tr><th>하늘상태</th><td>';
					switch(item.fcstValue) {
					case 1 :
					tag+= '맑음';
					break;
					case 2 :
					tag+= '구름조금';
					break;
					case 3 :
					tag+= '구름많음';
					break;
					case 4 :
					tag+= '흐림';
					break;
					}
					tag+='</td></tr>';	
				}else if(item.category == 'T3H'){
					tag+= '<tr><th>현재온도</th><td>'+ item.fcstValue +'℃</td></tr>';
				}else if(item.category == 'TMN'){
					tag+= '<tr><th>최저기온</th><td>'+ item.fcstValue +'℃</td></tr>';
				}else if(item.category == 'TMX'){
					tag+= '<tr><th>최고기온</th><td>'+ item.fcstValue +'℃</td></tr>';
				}
//					console.log(item.category);
//					console.log(item.fcstValue);
			});
			tag += '</table>';
				$('#wPrint').append(tag);
		},
		error : function(req) {
			alert(req.status);
		}
	});
}
</script>
</html>