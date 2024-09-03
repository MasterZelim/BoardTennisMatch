package com.example.boardtennismatch.controller;

import com.example.boardtennismatch.model.MatchScore;
import com.example.boardtennismatch.service.MainService;
import com.example.boardtennismatch.util.ConfigurationUtil;
import com.example.boardtennismatch.validation.PlayerValidate;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NewMatchController", value = "/new_match")
public class NewMatchController extends HttpServlet {
    private final PlayerValidate playerValidate = PlayerValidate.getInstance();
    private MainService mainService;

    public void init(ServletConfig servletConfig) {
        mainService = (MainService) servletConfig.getServletContext().getAttribute("mainService");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationUtil.get("new_match_jsp"));
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String firstPlayerName = getPlayerName(request, "player1");
        String secondPlayerName = getPlayerName(request, "player2");
        UUID uuidMatch = mainService.generationMatchScoreService(firstPlayerName, secondPlayerName);
        MatchScore matchScore = mainService.getMatchScore(uuidMatch).orElseThrow(()->new EntityNotFoundException("Object MatchScore not found!")) ;
        request.setAttribute("matchScore", matchScore);
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationUtil.get("match_score_jsp"));
        dispatcher.forward(request, response);
    }

    private String getPlayerName(HttpServletRequest req, String playerName) {
        if (playerValidate.isEmptyOrNull(req.getParameter(playerName))) {
            throw new IllegalArgumentException("Player name is empty or null");
        }
        return req.getParameter(playerName);
    }
    public void destroy() {
    }

}