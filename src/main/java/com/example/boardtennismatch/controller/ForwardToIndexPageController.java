package com.example.boardtennismatch.controller;

import com.example.boardtennismatch.util.ConfigurationUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForwardToIndexPageController", value = "/forward_index_page")
public class ForwardToIndexPageController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationUtil.get("index_jsp"));
        dispatcher.forward(request, response);
    }
}
