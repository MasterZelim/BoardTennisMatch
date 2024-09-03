package com.example.boardtennismatch.contextListener;

import com.example.boardtennismatch.service.MainService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListenerMainService implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("mainService", new MainService());
    }
}
