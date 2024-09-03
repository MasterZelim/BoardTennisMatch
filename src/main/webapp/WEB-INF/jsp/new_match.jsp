<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новый матч</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #25a1d2;
            color: #ffffff;
        }
        .match {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
            text-align: center;
        }
        .match div {
            background-color: #303030;
            padding: 20px;
            border-radius: 10px;
        }

    </style>
</head>
<body>
<div class="match">
    <div>
<form action="/new_match" method="post">
    <p><label for="player1">Имя первого игрока:</label></p>
    <p><input type="text" id="player1" name="player1"><br><br></p>
    <p><label for="player2">Имя второго игрока:</label></p>
    <p><input type="text" id="player2" name="player2"><br><br></p>
    <p><input type="submit" value="Начать"></p>
</form>
    </div>
</div>
</body>
</html>