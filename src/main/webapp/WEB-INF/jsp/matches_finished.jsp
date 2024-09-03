<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Завершенные матчи</title>
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

        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #555;
        }
        .container {
            background-color: #2c2c2c;
            border-radius: 8px;
            padding: 20px;
            width: 400px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .form-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }
        .form-group label {
            flex: 1;
            margin-right: 10px;
        }
        .form-group input {
            flex: 3;
            padding: 8px;
            border: 1px solid #444;
            border-radius: 4px;
            background-color: #333;
            color: #f0f0f0;
        }
        .form-group button {
            padding: 8px 16px;
            margin-left: 10px;
            border: none;
            border-radius: 4px;
            background-color: #42a5f5;
            color: #fff;
            cursor: pointer;
        }
        .form-group button.clear {
            background-color: #ef5350;
        }
        .hidden {
            display: none;
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
    <div class="container">
        <h2>Завершенные матчи</h2>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <button type="button" onclick="search()">Search</button>
            <button type="button" class="clear" onclick="clearInput()">Clear</button>
        </div>
        <table id="matches">
            <thead>
            <tr>
                <th>ID</th>
                <th>PlayerFirst</th>
                <th>PlayerSecond</th>
                <th>Winner</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${dataList}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.playerFirst.name}</td>
                    <td>${item.playerSecond.name}</td>
                    <td>${item.winner.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <form action="/forward_index_page">
                <button type="submit" class="button">На главную страницу!</button>
            </form>

        </div>
    </div>

    <script>
        function search() {
            var input = document.getElementById('name').value.toLowerCase();
            var rows = document.getElementById('matches').getElementsByTagName('tbody')[0].getElementsByTagName('tr');

            for (var i = 0; i < rows.length; i++) {
                var cells = rows[i].getElementsByTagName('td');
                var matchFound = false;

                for (var j = 0; j < cells.length; j++) {
                    if (cells[j].innerText.toLowerCase().includes(input)) {
                        matchFound = true;
                        break;
                    }
                }

                if (matchFound) {
                    rows[i].classList.remove('hidden');
                } else {
                    rows[i].classList.add('hidden');
                }
            }
        }

        function clearInput() {
            document.getElementById('name').value = '';
            search();
        }
    </script>
</body>
</html>
