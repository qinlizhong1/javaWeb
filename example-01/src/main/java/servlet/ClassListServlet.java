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

public class ClassListServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        Connection connection = JdbcOpUtils.getConnection();

        String queryAllSql = "select * from class";
        PreparedStatement preparedStatement = connection.prepareStatement(queryAllSql);

        ResultSet resultSet = preparedStatement.executeQuery();

        //编码等设置需要在 PrintWriter变量定义之前设置
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter out = resp.getWriter();

        out.print("<html>");
        out.print("<head>");
        out.print(" <title>班级信息</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1 align='center'>班级列表</h1>");
        out.print("<hr>");
        out.print(" <table align='center' border='1px solid black' width='50%'>");
        out.print("     <thead>");
        out.print("     <tr>");
        out.print("         <th>班级编号</th>");
        out.print("         <th>班主任</th>");
        out.print("         <th>人数</th>");
        out.print("         <th>操作</th>");
        out.print("     </tr>");
        out.print("     </thead>");
        out.print("<tbody>");

        while (resultSet.next()) {
            Integer classId = resultSet.getInt("class_id");
            String headTeacher = resultSet.getString("head_teacher");
            Integer stuNum = resultSet.getInt("stu_num");

            out.print("     <tr>");
            out.print("         <td>" + classId + "</td>");
            out.print("         <td>" + headTeacher + "</td>");
            out.print("         <td>" + stuNum + "</td>");
            out.print("         <td>");

            //向服务器提交数据的格式：uri?param1=value&param2=value&param3=value&name=value
            out.print("            <a href=" + this.getServletContext().getContextPath() + "/detail?id=" + classId+ ">详情</a>");
            out.print("            <a href=" + this.getServletContext().getContextPath() + "/del?id=" + classId+ ">删除</a>");
            out.print("            <a href=" + this.getServletContext().getContextPath() + "/edit?id=" + classId+ ">修改</a>");
            out.print("        </td>");
            out.print("     </tr>");
        }

        out.print("    </tbody>");
        out.print("</table>");
        out.print("<hr>");
        out.print("<a href=" +this.getServletContext().getContextPath()  +  "/add.jsp" + ">新增班级</a>");
        out.print("</body>");
        out.print("</html>");
        JdbcOpUtils.close(connection, preparedStatement, resultSet);
    }

    /**
     * 新增班级使用post请求，转发到这里还是post请求，一种方案是这里实现doPost方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
