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

public class ClassDelServlet extends HttpServlet {
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

        String delSql = "delete from class where class_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delSql);
        preparedStatement.setInt(1, classId);

        if (preparedStatement.executeUpdate() >= 1){
            //req.getRequestDispatcher("/list").forward(req, resp);
            //使用重定向好些
            resp.sendRedirect(this.getServletContext().getContextPath() + "/list");

            //或者如下
            //resp.sendRedirect(req.getContextPath() + "/list");
        }else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/list");
        }

        JdbcOpUtils.close(connection, preparedStatement, null);
    }
}
