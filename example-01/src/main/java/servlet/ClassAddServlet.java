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

public class ClassAddServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Integer classId = Integer.parseInt(req.getParameter("id"));
        String headTeacher = req.getParameter("headTeacher");
        Integer stuNum = Integer.parseInt(req.getParameter("stuNum"));
        //编码等设置需要在 PrintWriter变量定义之前设置
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        Connection connection = JdbcOpUtils.getConnection();

        String addSql = "insert into class values(?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addSql);
        preparedStatement.setInt(1, classId);
        preparedStatement.setString(2, headTeacher);
        preparedStatement.setInt(3, stuNum);

        if (preparedStatement.executeUpdate() >= 1){
            //req.getRequestDispatcher("/list").forward(req, resp);
            //使用重定向好些，否则刷新页面会导致重复插入
            resp.sendRedirect(this.getServletContext().getContextPath() + "/list");
        }else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/error.html");
        }

        JdbcOpUtils.close(connection, preparedStatement, null);
    }
}
