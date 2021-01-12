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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DispatcherServlet
 * @Author 周聪
 * @Date 2021/1/9 16:31
 * @Version 1.0
 * @Description DispatcherServlet委派模式案例
 */
public class DispatcherServlet extends HttpServlet {
    /**
     * 容器保存映射关系，单例模式体现，每一个Controller自始至终都使用同一个实例
     */
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    /**
     * 重写init方法,建立好映射关系
     */
    @Override
    public void init() throws ServletException {
        Class<?> memberControllerClass = MemberController.class;
        Class<?> orderControllerClass = OrderController.class;
        Class<?> systemControllerClass = SystemController.class;
        try {
//            简单工厂模式体现
            handlerMapping.add(new Handler().setController(memberControllerClass.newInstance())
                    .setMethod(memberControllerClass.getMethod("getMemberById", String.class))
                    .setUrl("/web/getMemberById.json"));
            handlerMapping.add(new Handler().setController(orderControllerClass.newInstance())
                    .setMethod(orderControllerClass.getMethod("getOrderById", String.class))
                    .setUrl("/web/getOrderById.json"));
            handlerMapping.add(new Handler().setController(systemControllerClass.newInstance())
                    .setMethod(systemControllerClass.getMethod("logout"))
                    .setUrl("/web/logout.json"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        完成调度
        doDispatch(req,resp);

    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        Handler handler = null;
//        策略模式的体现，避免了重复的if...else...代码
        for (Handler h : handlerMapping) {
            if (h.getUrl().equals(uri)){
                handler = h;
                break;
            }
        }
        if (handler != null){
            try {
//                委派模式体现
                Object obj = handler.getMethod().invoke(handler.getController(), req.getParameter("mid"));
                resp.getWriter().write(obj.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



//    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String uri = req.getRequestURI();
//        if ("getMemberById".equals(uri)){
//            String mid = req.getParameter("mid");
//            new MemberController().getMemberById(mid);
//        }else if ("getOrderById".equals(uri)){
//            String oid = req.getParameter("oid");
//            new OrderController().getOrderById(oid);
//        }else if ("getSystemById".equals(uri)){
//            new SystemController().logout();
//        }else {
//            resp.getWriter().write("404 not found");
//        }
//    }

    class Handler{
        private Object controller;
        private Method method;
        private String url;

        /**
         * 修改set方法返回对象，方便链式调用
         */
        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
