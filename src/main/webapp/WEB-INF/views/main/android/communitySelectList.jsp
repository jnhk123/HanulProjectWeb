<%@page import="community.CommunityVO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Gson gson = new Gson();
String json = gson.toJson((ArrayList<CommunityVO>)request.getAttribute("list"));

out.println(json);	
%>