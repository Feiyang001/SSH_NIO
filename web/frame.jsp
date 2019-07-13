<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/5 0005
  Time: 上午 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工管理系统</title>
</head>
<frameset rows="80,*">
    <frame name="top" src="${pageContext.request.contextPath}/frame/top.jsp">
    <frameset cols="150,*" id="main">

        <frame src="${pageContext.request.contextPath}/frame/left.jsp">
        <frame name="right" src="${pageContext.request.contextPath}/frame/right.jsp">
    </frameset>
</frameset>