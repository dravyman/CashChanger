<%@ page import="authorization.User" %>
<%@ page import="utils.Global" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% User user = (User) (session.getAttribute(Global.session_attr_currentUser));
    if (user != null && !user.getLogin().isEmpty()) {
        response.sendRedirect("/indexServlet");
    } else {
%>
<head>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
</head>
<body>
<div class="container">
    <div align="center">
        <h3> Добро пожаловать! Войдите в систему</h3>
        <div class="wrapper col-xs-offset-5 col-xs-2">
            <form action="indexServlet" method="post">
                <hr>
                <input type="text" class="form-control" name="username" placeholder="Логин" required autofocus/><br>
                <input type="password" class="form-control" name="password" placeholder="Пароль" required/><br>
                <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Войти" type="submit">Войти</button>
            </form>
        </div>
    </div>
</div>
</body>
<%}%>
</html>
