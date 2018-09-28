<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆成功</title>
</head>
<body>
恭喜您!登陆成功!
<hr/>
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
<hr/>

如果您要看到效果，请再新建一个浏览器（注意本页面不要关闭），<br/>
用同样的UID登陆（用户名可以随便取，但是UID一定要相同），<br/>
登陆后再重新打开本页面（期间时间段最少为五秒），刷新！<br/>
<a href="admin/test.jsp">test</a>
</body>
</html>