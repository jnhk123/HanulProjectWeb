<%@page import="status.StatusVO"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Gson gson = new Gson(); 
	String json = gson.toJson((StatusVO)request.getAttribute("vo")); 
	out.println(json);
%>