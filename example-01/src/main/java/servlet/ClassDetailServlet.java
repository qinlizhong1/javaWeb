package servlet;

import lombok.SneakyThrows;
import utils.JdbcOpUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassDetailServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer classId = Integer.parseInt(req.getParameter("id"));

        //编码等设置需要在 PrintWriter变量定义之前设置
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        req.setCharacterEncoding("utf-8");
        Connection connection = JdbcOpUtils.getConnection();

        String queryClassByIdSql = "select * from class where class_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(queryClassByIdSql);
        preparedStatement.setInt(1, classId);

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("  <meta charset='UTF-8'>");
        out.print("  <title>班级详情</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("  <h1>班级详情</h1>");
        out.print("  <hr>");


        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("class_id");
            String headTeacher = resultSet.getString("head_teacher");
            Integer stuNum = resultSet.getInt("stu_num");
            out.print("  <p>班级编号："+ id +"</p>");
            out.print("  <p>班级名称："+ headTeacher +"</p>");
            out.print("  <p>班级人数："+ stuNum +"</p>");
        }

        out.print("<input type='button' value='后退' οnclick='window.history.go(-1)'>");
        out.print("</body>");
        out.print("</html>");

        JdbcOpUtils.close(connection, preparedStatement, resultSet);
    }
}
