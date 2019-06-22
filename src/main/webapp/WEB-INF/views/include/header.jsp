<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Cute+Font|Do+Hyeon|Jua|Noto+Sans+KR|PT+Sans+Narrow|Poor+Story&display=swap" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
 	#ga{
		overflow: hidden; 
	} 
 	#la{ 
		padding-right: 25px;
 		float: right;
 		font-family: Jua;
 		font-size: 22px;
	} 
	.icon_on{
	font-weight: bold;
	color: #007bff;
	}
	.icon_off:hover {
	font-weight: bold;
	}
	.category{
		color: #007bff; font-weight: bold;
	}
</style>
</head>
<body>
<header class="site-header">
            <div class="container">
                <div style="height: 100px; " class="site-header-inner">
                    <div class="brand header-brand">
                        <h1 class="m-0">
                            <a onclick="location='<c:url value="/"/>'">
                                <svg width="48" height="32" viewBox="0 0 48 32" xmlns="http://www.w3.org/2000/svg">
                                    <title>니집내집 : ${title }</title>
                                    <defs>
                                        <linearGradient x1="0%" y1="100%" y2="0%" id="logo-a">
                                            <stop stop-color="#007CFE" stop-opacity="0" offset="0%"/>
                                            <stop stop-color="#007DFF" offset="100%"/>
                                        </linearGradient>
                                        <linearGradient x1="100%" y1="50%" x2="0%" y2="50%" id="logo-b">
                                            <stop stop-color="#FF4F7A" stop-opacity="0" offset="0%"/>
                                            <stop stop-color="#FF4F7A" offset="100%"/>
                                        </linearGradient>
                                    </defs>
                                    <g fill="none" fill-rule="evenodd">
                                        <img src="resources/img/titleicon.png" style="width: 200px;"/>
                                    </g>
                                </svg>
                            </a>
                        </h1>
                    </div>
                </div>
            </div>
        </header>
        <div style="margin-bottom: 60px" id="ga" align="right">
        <a id="la" class="${category eq 'qu' ? 'category' : ''}" onclick="location='list.qu'">문의하기</a>
        <a id="la" class="${category eq 'co' ? 'category' : ''}" onclick="location='list.co'">커뮤니티</a>
        <a id="la" class="${category eq 'st' ? 'category' : ''}"  onclick="location='home.st'">우리집 현황</a>
        <a id="la" class="${category eq 'no' ? 'category' : ''}"  onclick="location='list.no'">공지사항</a>
		</div>
</body>
</html>