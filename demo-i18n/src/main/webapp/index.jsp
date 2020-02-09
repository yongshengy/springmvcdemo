<%--
  Created by IntelliJ IDEA.
  User: yyang
  Date: 2/9/2020
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<form:form modelAttribute="user">
    <spring:message code="username"/>
     <form:input path="name"/> <br/>

    <input type="submit" value="<spring:message code='submit'/>">

    <!--
    form:input - input text
    form:password
    form:checkbox
    form:checkboxes items=
    form:radiobutton
    form:radiobuttons items
    form:select  items
    form:textarea
    form:hidden
    form:errors path





    -->

</form:form>



</body>
</html>
