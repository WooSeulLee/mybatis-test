<%@page import="com.mybatis.test.vo.UserVO"%>
<%@page import="com.mybatis.test.repository.UserRepository"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
UserRepository ur = new UserRepository();
List<UserVO> users = ur.selectUsers(null);

Gson g = new Gson();
String json = g.toJson(users);
out.println(json);
    
%>