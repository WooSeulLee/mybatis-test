<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//page, request, session, application
RequestDispatcher rd = request.getRequestDispatcher("/request.jsp");
request.setAttribute("name", "동동");
session.setAttribute("name","redfila");
rd.forward(request, response);
%>

