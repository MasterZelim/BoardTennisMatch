<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Теннисное табло</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #25a1d2;
            color: #ffffff;
        }

        .content {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
            text-align: center;
        }
        .content div {
            background-color: #303030;
            padding: 20px;
            border-radius: 10px;
        }
        .content a {
            color: #1e90ff;
            text-decoration: none;
            font-weight: bold;
        }
        .content a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="content">
    <div>
        <h2>Теннисное табло</h2>
        <p><a href="/new_match">NEW</a> - начать новый матч</p>
        <p><a href="/matches_finished">MATCHES</a> - список завершенных матчей</p>
    </div>
</div>

</body>
</html>