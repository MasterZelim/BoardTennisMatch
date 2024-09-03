<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
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
        <h2>Match score</h2>
        <table>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Games</th>
                <th>Points</th>
            </tr>
            <tr>
                <td><%= matchScore.getPlayerFirst().getName()%></td>
                <td><%= matchScore.getPlayerFirstSets()%></td>
                <td><%= matchScore.getPlayerFirstGames()%></td>
                <td><%= matchScore.getPlayerFirstPoints()%></td>
            </tr>
            <tr>
                <td><%= matchScore.getPlayerSecond().getName()%></td>
                <td><%= matchScore.getPlayerSecondSets()%></td>
                <td><%= matchScore.getPlayerSecondGames()%></td>
                <td><%= matchScore.getPlayerSecondPoints()%></td>
            </tr>
        </table>
        <form action="/match_score" method="post">
            <input type="hidden" name="player" value="player1">
            <input type="hidden" name="playerWhoWonPoint" value="player1">
            <input type="hidden" name="uuidMatch" value=<%=matchScore.getUuidMatch()%>>
        <button type="submit" class="button">Первый игрок выиграл очко!</button>
        </form>
        <form action="/match_score" method="post">
            <input type="hidden" name="player" value="player2">
            <input type="hidden" name="playerWhoWonPoint" value="player2">
            <input type="hidden" name="uuidMatch" value=<%=matchScore.getUuidMatch()%>>
            <button type="submit" class="button">Второй игрок выиграл очко!</button>
        </form>

    </div>
</body>
</html>
