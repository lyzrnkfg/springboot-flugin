package com.nasus.helloword.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 访问/myServlet会输出this is MyServlet到页面
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("servlet初始化");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("this is MyServlet");
    }

}