<%@page import="notice.NoticeVO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	Gson gson = new Gson(); 
	String json = gson.toJson((NoticeVO)request.getAttribute("vo")); 
	out.println(json);
%>