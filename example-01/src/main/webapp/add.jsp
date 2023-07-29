<%--
  Created by IntelliJ IDEA.
  User: qin
  Date: 2023/7/29
  Time: 上午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增班级</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/add">
        班级编号:<input type="text" name="id" value=""><br>
        班主任:<input type="text" name="headTeacher" value=""><br>
        人数:<input type="text" name="stuNum" value=""><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
