<%--
  Created by IntelliJ IDEA.
  User: qin
  Date: 2023/7/29
  Time: 上午6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级信息</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>班级编号</th>
        <th>班主任</th>
        <th>人数</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td>1</td>
        <td>郭靖</td>
        <td>10</td>
        <td>
            <a href="add.jsp">详情</a>&nbsp;&nbsp;
            <a href="del.jsp">删除</a>&nbsp;&nbsp;
            <a href="edit.jsp">修改</a>
        </td>
    </tr>

    <tr>
        <td>2</td>
        <td>杨坤</td>
        <td>20</td>
        <td>修改</td>
    </tr>

    <tr>
        <td>3</td>
        <td>刘德华</td>
        <td>30</td>
        <td>删除</td>
    </tr>
    </tbody>
</table>
<a href="add.jsp">新增班级</a>
</body>
</html>
