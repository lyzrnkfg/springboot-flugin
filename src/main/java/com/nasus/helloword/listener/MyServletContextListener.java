package com.nasus.helloword.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("web容器   启动......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("web容器   销毁......");
    }
}
