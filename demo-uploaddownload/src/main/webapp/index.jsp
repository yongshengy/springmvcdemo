<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<h2>Hello World!</h2>
<form action="upload" method="post" enctype="multipart/form-data">
    <input type="file" name="img">
    <input type="submit" name="Submit">
</form>
<a href="download?fileName=test.jpg">Download test.jpg</a>
<c:if test="${filePath!=null}">
    <h1>Uploaded Image</h1>
    <br/>
    <img width="300px" src="<%=basePath%>${filePath}"/>
</c:if>

</body>
</html>
