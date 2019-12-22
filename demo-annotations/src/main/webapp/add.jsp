<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2019/12/17
  Time: 1:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/user/addUser" method="post">
    No. : <input type="text" name="id"/> <br/>
    Name : <input type="text" name="name"/> <br/>
    Address : <input type="textarea" name="address.name"/> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
