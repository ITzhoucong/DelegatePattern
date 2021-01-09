package com.zc.pattern.delegate.mvc;

import com.zc.pattern.delegate.mvc.controllers.MemberController;
import com.zc.pattern.delegate.mvc.controllers.OrderController;
import com.zc.pattern.delegate.mvc.controllers.SystemController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName DispatcherServlet
 * @Author 周聪
 * @Date 2021/1/9 16:31
 * @Version 1.0
 * @Description
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        完成调度
        doDispach(req,resp);
    }

    private void doDispach(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        if ("getMemberById".equals(uri)){
            String mid = req.getParameter("mid");
            new MemberController().getMemberById(mid);
        }else if ("getOrderById".equals(uri)){
            String oid = req.getParameter("oid");
            new OrderController().getOrderById(oid);
        }else if ("getSystemById".equals(uri)){
            new SystemController().logout();
        }else {
            resp.getWriter().write("404 not found");
        }
    }
}
