<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6d6527f14573836af734688a2d407ee4&libraries=services,clusterer,drawing"></script>
<link rel="stylesheet" href="resources/css/btn.css?<%=new java.util.Date().getTime()%>">
<link rel="stylesheet" href="resources/css/jquery-ui.min.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<style type="text/css">
.btn-style{
   text-align: center;
   height: 40px; 
   font-size: 15px; 
   width: 120px; 
   margin-left: 10px;
}

.off {color: red; font-weight: bold; }
.on {color: blue; font-weight: bold; }

.fonts{ font-family: Jua; }
</style>

<script>

$(function() {
	if(${empty login.id}){
		alert('로그인 후 사용가능합니다');
		location = "login.cs";
	}
});

function get_items(){
	$.ajax({
		type: 'post',
		data: {id: '${login.id}' },
		url: "get_items",
		success: function(data){
			if(data != "N"){
				location.reload();
			}else {
				alert("집사셈");
			}
		},
		error: function(){
			alert(req.status);
		}
	}) 
}


function close_dialog(){
   $('#dialog').dialog('close');
}

function submit_chk(){
   if ($('[name=addr]').val().trim() == "") {
      alert("주소를 입력해 주세요.");
   }else {
      $('form').submit();
   }
   window.location.reload();
};


$(function() {
  $('#dialog').dialog({
    autoOpen: false,
    resizable: false,
    width: 800,
    modal:true,
  });
  $('#add_address').click( function(){
    $('#dialog').dialog('open');
  });
 
});


$(document).on('change', '#addr_list', function(){
   
});


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
            if (data.buildingName.trim() != '')
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
         console.log( 'extra' ,address );
      }
   }).open();
}

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

$(document).ready(function() {
   var i = 0;
   $.ajax({
      type: 'post',
      url: 'getAddr',
      data: {id: '${login.id}' },
      success: function(data){
         console.log(data);
         var tag = '<select id="addr_list" style="width:500px; height:40px; margin-bottom: 6px;">' + '<option value=""> 집 선택 </option>';
         for( i = 0; i<data.length; i++ ){
            tag += '<option  class="addrOption" value="' + data[i].addr + '" ${myHomeExistense eq "Y" ? "selected" : ""} >' + data[i].addr + '</option>'
            $('#add_address').css('display','none');
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

function status_change(urlAd) {
	$.ajax({
		type: 'post',
		data: {id: '${login.id}' },
		url: urlAd,
		success: function(data){
			$.ajax({
				type: 'post',
				data: {id: '${login.id}' },
				url: "get_items",
				success: function(data){
					location.reload();
				},
				error: function(){
					alert(req.status);
				}
			}) 
		},
		error: function(){
			alert(req.status);
		}
	}) 
}


</script>
</head>
<body>

	<div><img src="img/my_home_main.jpg" style="width: 100%;">
	</div>
	 
	 
   <div style="min-height: 800px; max-height: 100%; margin: 0 auto; padding-top: 30px ">
     
      <c:if test="${login.id ne null }">
         
        <div id="dialog" title="집 추가하기" style="padding-top: 20px">
          <form method="post" action="insertAddr.cs">
              <table>
                 <tr>
                    <td><input onclick="go_addr()" type="button" class="btn btn-lg btn-primary btn-block" style="text-align: center; height: 40px; font-size: 15px; width: 130px;" value="우편주소 찾기" /> 
                    <input type="text" name="post" style="width: 230px; margin-bottom: 10px" /><br> 
                    <input type="text" name="addr" style="width: 99%; margin-bottom: 3px" /><br>
                    <input type="text" name="addr" style="width: 99%" /></td>
                 </tr>
              </table>
              <div align="center">
                 <button id="save" class="btn btn-lg btn-primary btn-block btn-style" type="button" onclick="submit_chk()">저장</button>
                 <button id="cancel" class="btn btn-lg btn-primary btn-block btn-style" type="button" onclick="close_dialog()">취소</button>
                 <input type="hidden" id="nx" name="nx" /> 
                 <input type="hidden" id="ny" name="ny" />
                 <input type="hidden" id="id" name="id" />
              </div>
           </form>
        </div>
         
        <div id="homeAddr" style="width: 505px; float: left;"></div>
        <button id='house_status' class="btn" style="border-radius:20px; width:150px; margin-bottom: 6px;" onclick="get_items()">집 상태확인</button>
        <button id='add_address' class="btn" style="border-radius:20px; width:150px; margin-bottom: 6px;">집 추가하기</button>
        <input type="hidden" name="nx" id="geoX">
        <input type="hidden" name="ny" id="geoY">
        
		<c:if test="${!empty status_info }">
			<div style="width: 1100px; ;height: 600px;">
		         <table class="type07" style="width: 1100px; height: 100px; float: left; margin: 0 auto;" border="3">
		            <tr>
		               <th style="padding-left:10px; width: 200px;"><span>현재기온</span></th>
		               <td style="text-align: center;">${weather_info.getT3h() } ℃</td>
		               <th style="padding-left:10px;"><span>날씨상태</span></th>
		               <td style="width: 200px; text-align: center; padding-left: 70px">
		               
		               <c:choose>
			               <c:when test="${weather_info.getPty() eq '0' }">
								<c:choose>
									<c:when test="${weather_info.getSky() eq '1' }">
										<!-- 맑음 -->
										<img width=60px src="img/icon/sunny_icon.png"/>
									</c:when>
									<c:when test="${weather_info.getSky() eq '2' }">
										<!-- 구름조금 -->
										<img width=60px src="img/icon/littlecloud_icon.png">
									</c:when>
									<c:when test="${weather_info.getSky() eq '3' }">
										<!-- 구름많음 -->
										<img width=60px src="img/icon/littlecloud_icon.png">
									</c:when>
									<c:when test="${weather_info.getSky() eq '4' }">
										<!-- 흐림 -->
										<img width=60px src="img/icon/cloud_icon.png">
									</c:when>
								</c:choose>
			               </c:when>
			               
			               <c:when test="${weather_info.getPty() eq '1' }">
			               		<!-- 비 -->
			               		<img width=60px src="img/icon/rain_icon.png">
			               </c:when>
			               <c:when test="${weather_info.getPty() eq '2' }">
			               		<!-- 진눈개비 -->
			               		<img width=60px src="img/icon/snowandrain_icon.png">
			               </c:when>
			               <c:when test="${weather_info.getPty() eq '3' }">
			               		<!-- 눈 -->
			               		<img width=60px src="img/icon/snow_icon.png">
			               </c:when>
		               </c:choose>
		               
		               </td>
		               <th style="padding-left:10px;"><span>강수확률</span></th>
		               <td style="text-align: center; padding-right: 16px;" >${weather_info.getPop() }%</td>
		            </tr>
		         </table>
		         
		         <table class="type07" style="width: 1100px; height: 500px; float: left; margin: 0 auto; border-top-color: white;">
		            <tr>
		               	<td style="text-align: center;">
		               		<c:if test="${status_info.getLight() == 'N' }"><img width=60px src="img/icon/light_off.png" style="display: inline;"></c:if> 
		               		<c:if test="${status_info.getLight() == 'Y' }"><img width=60px src="img/icon/light.png" style="display: inline;"></c:if>
		               		현재 전등이 
		               		<c:if test="${status_info.getAutoWindow() == 'N' }">
		               		<span onclick="status_change('light_on_off.status')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getAutoWindow() == 'Y' }">
		               		<span onclick="alert('집 감지모드를 Off 해주세요')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getLight() == 'N' }"><span class="off">꺼져</span></c:if> 
		               		<c:if test="${status_info.getLight() == 'Y' }"><span class="on">켜져</span></c:if>
		              		</span>
		              		
		               	있습니다</td>
		               	<td style="text-align: center;">
		               		<c:if test="${status_info.getBoiler() == 'N' }"><img width=60px src="img/icon/boiler_off.png" style="display: inline;"></c:if> 
		               		<c:if test="${status_info.getBoiler() == 'Y' }"><img width=60px src="img/icon/boiler.png" style="display: inline;"></c:if>
		               		현재 보일러가 
		               		<c:if test="${status_info.getAutoWindow() == 'N' }">
		               		<span onclick="status_change('boiler_on_off.status')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getAutoWindow() == 'Y' }">
		               		<span onclick="alert('집 감지모드를 Off 해주세요')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getBoiler() == 'N' }"><span class="off">꺼져</span></c:if> 
		               		<c:if test="${status_info.getBoiler() == 'Y' }"><span class="on">켜져</span></c:if>
		               		</span>
		               	 있습니다</td>
		               	
		            </tr>
		            <tr>
		               	<td style="text-align: center;">
		               		<c:if test="${status_info.getDoor() == 'N' }"><img width=60px src="img/icon/alarm_off.png" style="display: inline;"></c:if> 
		               		<c:if test="${status_info.getDoor() == 'Y' }"><img width=60px src="img/icon/alarm.png" style="display: inline;"></c:if>
		               		현재 문이 
		               		<c:if test="${status_info.getDoor() == 'N' }"><span class="off">닫혀</span></c:if> 
		               		<c:if test="${status_info.getDoor() == 'Y' }"><span class="on">열려</span></c:if>
		               	 있습니다</td>
		               	 <td style="text-align: center;">
		                	<c:if test="${status_info.getWindow() == 'C' }"><img width=60px src="img/icon/window_off.png" style="display: inline;"></c:if> 
		               		<c:if test="${status_info.getWindow() == 'O' }"><img width=60px src="img/icon/window.png" style="display: inline;"></c:if>
		                	현재 창문이
		               		<c:if test="${status_info.getWindow() == 'C' }">
		               		<c:if test="${status_info.getAutoWindow() == 'N' }">
		               		<span class="off" onclick="status_change('controll_windowOpen')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getAutoWindow() == 'Y' }">
		               		<span class="off" onclick="alert('집 감지모드를 Off 해주세요')" style="cursor: pointer;">
		               		</c:if>
		               		닫혀</span></c:if>
		               		<c:if test="${status_info.getWindow() == 'O' }">
		               		<c:if test="${status_info.getAutoWindow() == 'N' }">
		               		<span class="on" onclick="status_change('controll_windowClose')" style="cursor: pointer;">
		               		</c:if>
		               		<c:if test="${status_info.getAutoWindow() == 'Y' }">
		               		<span class="on" onclick="alert('집 감지모드를 Off 해주세요')" style="cursor: pointer;">
		               		</c:if>
		               		열려</span></c:if>
		               	 있습니다</td>
		            </tr>
<!-- 		            <tr> -->
		                
<!-- 		               	 <td style="text-align: center;"> -->
<%-- 		               		<c:if test="${status_info.getGas() == 'N' }"><img width=60px src="img/icon/gas_off.png" style="display: inline;"></c:if>  --%>
<%-- 		               		<c:if test="${status_info.getGas() == 'Y' }"><img width=60px src="img/icon/gas.png" style="display: inline;"></c:if> --%>
<!-- 		               		현재 가스밸브가 -->
<%-- 		               		<c:if test="${status_info.getGas() == 'N' }"><span class="off">닫혀</span></c:if>  --%>
<%-- 		               		<c:if test="${status_info.getGas() == 'Y' }"><span class="on">열려</span></c:if> --%>
<!-- 		               	있습니다</td> -->
<!-- 		               	 <td style="text-align: center;"> -->
<%-- 		               		<c:choose> --%>
<%-- 		               			<c:when test="${status_info.getWater() == 0}"> --%>
<!-- 		               				<img width=60px src="img/icon/0.png" style="display: inline;"> -->
<%-- 		               			</c:when> --%>
<%-- 		               			<c:when test="${status_info.getWater() > 0 && status_info.getWater() <= 2}"> --%>
<!-- 		               				<img width=60px src="img/icon/1_2.png" style="display: inline;"> -->
<%-- 		               			</c:when> --%>
<%-- 		               			<c:when test="${status_info.getWater() > 2 && status_info.getWater() <= 5}"> --%>
<!-- 		               				<img width=60px src="img/icon/3_5.png" style="display: inline;"> -->
<%-- 		               			</c:when> --%>
<%-- 		               			<c:when test="${status_info.getWater() > 5 && status_info.getWater() <= 9}"> --%>
<!-- 		               				<img width=60px src="img/icon/6_9.png" style="display: inline;"> -->
<%-- 		               			</c:when> --%>
<%-- 		               			<c:when test="${status_info.getWater() > 9}"> --%>
<!-- 		               				<img width=60px src="img/icon/10.png" style="display: inline;"> -->
<%-- 		               			</c:when> --%>
<%-- 		               		</c:choose> --%>
<!-- 		               		현재 물이  -->
<%-- 		               		<span class="${status_info.getWater() < 3 ? 'off' : 'on'}">${status_info.getWater()}병</span>  --%>
<!-- 		               		있습니다</td> -->
<!-- 		            </tr> -->
		         
		         </table>
		         <span style="font-size: 10px;">※ 감지모드가 on상태일시만 문 상태를 실시간으로 확인할 수 있습니다.</span>
		     </div>
		</c:if>
		
		
		<c:if test="${empty status_info }">
			<div style="height: 650px;">
		         <table style="width: 1100px; height: 550px; float: left;" border="3">
		        	<tr>
		        		<td align="center" style="color: red; font-size: 20pt;"><span class="fonts">집을 선택해 주세요</span></td>
		        	</tr>
		         </table>
		     </div>
		</c:if>
      </c:if>
   </div>
</body>
</html>