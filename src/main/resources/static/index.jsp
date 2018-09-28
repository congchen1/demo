<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.chen.web.onelogin.vo.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>帐户登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body> 
    此工程用于防止单一帐号登陆重复登陆问题 <br><hr/>
    <form action="<%=request.getContextPath() %>/login" method="post">
    <table>
    <tr><td>UID:<td><td><input type="text" name="uid"/><td></tr>
    <tr><td>name:<td><td><input type="text" name="name"/><td></tr>
    <tr><td>submit:<td><td><input type="submit" value="确定"/><td></tr>
    </table>    
    </form>
    <br/>
    此用户登陆UID和用户名可以随便设置,请务必填写!
    <br/>
    当前已经登陆的用户列表：
    <%
    Map list = (Map)application.getAttribute("ulist");
    if(list != null){
      request.setAttribute("list",list.values());
    }
     %>
     <c:forEach items="${list}" var="user">
      ${user.name }
     </c:forEach>
  </body>
</html>
