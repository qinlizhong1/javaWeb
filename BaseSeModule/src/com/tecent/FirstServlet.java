package com.tecent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
        System.out.println("第一个servlet程序运行3");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("第一个servlet程序运行3");

    }
}
