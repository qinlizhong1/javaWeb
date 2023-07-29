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

public class ClassEditServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Integer classId = Integer.parseInt(req.getParameter("id"));

        //编码等设置需要在 PrintWriter变量定义之前设置
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();

        Connection connection = JdbcOpUtils.getConnection();

        String queryClassByIdSql = "select * from class where class_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(queryClassByIdSql);
        preparedStatement.setInt(1, classId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("class_id");
            String headTeacher = resultSet.getString("head_teacher");
            Integer stuNum = resultSet.getInt("stu_num");

            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("  <meta charset='UTF-8'>");
            out.print("  <title>修改班级信息</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("  <h1>修改班级</h1>");
            out.print("  <hr>");
            out.print("		<form action=" + this.getServletContext().getContextPath() + "/update" + " method='post'>");
            out.print("     班级编号:<input type='text' name='id'" + "value=" +id + "><br>");
            out.print("     班主任:<input type='text' name='headTeacher'" + "value=" + headTeacher + "><br>");
            out.print("     人数:<input type='text' name='stuNum'" + "value=" + stuNum + "><br>");

            out.print("<input type='submit' value='修改'>");
            out.print("		</form>");
            out.print("</body>");
            out.print("</html>");
        }
    }
}
