package com.example.boardtennismatch.controller;

import com.example.boardtennismatch.model.Match;
import com.example.boardtennismatch.repository.MatchRepositoryImpl;
import com.example.boardtennismatch.repository.SpecMatchRepository;
import com.example.boardtennismatch.service.FinishedMatchesPersistenceService;
import com.example.boardtennismatch.service.MainService;
import com.example.boardtennismatch.util.ConfigurationUtil;
import com.example.boardtennismatch.util.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MatchesFinishedList", value = "/matches_finished")
public class MatchesFinishedController extends HttpServlet {
    private MainService mainService;
    public void init(ServletConfig servletConfig){
        mainService = (MainService) servletConfig.getServletContext().getAttribute("mainService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        List<Match> dataList = mainService.getAllMatch();
        request.setAttribute("dataList", dataList);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationUtil.get("matches_finished"));
        dispatcher.forward(request, response);


    }
}
