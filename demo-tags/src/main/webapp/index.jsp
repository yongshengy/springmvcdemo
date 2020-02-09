<%--
  Created by IntelliJ IDEA.
  User: yyang
  Date: 2/9/2020
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<form:form modelAttribute="user">
    Id: <form:input path="id" readonly="true"/> <br/>
    Name: <form:input path="name"/> <br/>
    Age: <form:input path="age"/> <br/>
    Gender: <form:input path="gender"/> <br/>
    <input type="submit" value="Submit">

    <!--
    form:input - input text
    form:password
    form:ckeckbox
    form:checkboxes items=
    form:radiobutton
    form:radiobuttons items
    form:select  items
    form:textarea
    form:hidden
    form:errors path

    -->

</form:form>

<hr/>
<!--
EL表达式是用来替代JSP页面中数据访问域对象
pageContext - request - session - application


JSTL标签 主要是页面的逻辑处理
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

JSTL 负责完成模式数据的逻辑处理，如遍历集合、判断等，EL 只负责展示结果，二者相结合的方式可以大大简化 JSP 的代码开发

-->

<%

%>

</body>
</html>
