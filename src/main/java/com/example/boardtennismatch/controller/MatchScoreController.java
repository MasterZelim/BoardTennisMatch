package com.example.boardtennismatch.controller;

import com.example.boardtennismatch.model.MatchScore;
import com.example.boardtennismatch.service.MainService;
import com.example.boardtennismatch.util.ConfigurationUtil;
import com.example.boardtennismatch.validation.MatchValidate;
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

@WebServlet(name = "MatchScoreController", value = "/match_score")
public class MatchScoreController extends HttpServlet {
    private MainService mainService;
    private final PlayerValidate playerValidate = PlayerValidate.getInstance();
    private final MatchValidate matchValidate = MatchValidate.getInstance();

    public void init(ServletConfig servletConfig) {
        mainService = (MainService) servletConfig.getServletContext().getAttribute("mainService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        MatchScore matchScore = mainService.gameScoring(getPlayerWhoWonPoint(request), getUuidMatch(request))
                .orElseThrow(() -> new EntityNotFoundException("Object MatchScore not found!"));
        request.setAttribute("matchScore", matchScore);
        String targetPage = matchScore.isMatchEnd() ? ConfigurationUtil.get("match_winner_jsp") : ConfigurationUtil.get("match_score_jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
        dispatcher.forward(request, response);
    }

    private String getPlayerWhoWonPoint(HttpServletRequest request) {
        if (playerValidate.isEmptyOrNull(request.getParameter("playerWhoWonPoint"))) {
            throw new IllegalArgumentException("PlayerWhoWonPoint id is empty or null");
        }
        if (playerValidate.isNumber(request.getParameter("playerWhoWonPoint"))) {
            throw new IllegalArgumentException("PlayerWhoWonPoint  is  numeric");
        }
        return request.getParameter("playerWhoWonPoint");
    }

    private UUID getUuidMatch(HttpServletRequest request) {
        //todo chance on match validate
        if (matchValidate.isEmptyOrNull(request.getParameter("uuidMatch"))) {
            throw new IllegalArgumentException("UuidMatch is empty or null");
        }
        return UUID.fromString(request.getParameter("uuidMatch"));
    }

}
