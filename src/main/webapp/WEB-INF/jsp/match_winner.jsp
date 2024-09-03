<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match winner!</title>
    <style>
        body {
            background-color: #25a1d2;
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .scoreboard {
            background-color: #333;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #555;
        }
        .button {
            margin: 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<%@ page import="com.example.boardtennismatch.model.MatchScore" %>
<%  MatchScore matchScore = (MatchScore) request.getAttribute("matchScore");%>
<div class="scoreboard">
    <h2>Матч закончился! Победитель <%= matchScore.getWinner().getName() %>!</h2>
    <table>
        <tr>
            <th>Player</th>
            <th>Sets</th>
        </tr>
        <tr>
            <td><%= matchScore.getPlayerFirst().getName() %></td>
            <td><%= matchScore.getPlayerFirstSets()%></td>
        </tr>
        <tr>
            <td><%= matchScore.getPlayerSecond().getName()%></td>
            <td><%= matchScore.getPlayerSecondSets()%></td>
        </tr>
    </table>
    <form action="/forward_index_page" method="get">
        <button type="submit" class="button">Главная страница!</button>
    </form>
</div>
</body>
</html>
