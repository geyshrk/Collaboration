<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Начальное окно</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .project-list {
            list-style-type: none;
            padding: 0;
        }
        .project-list li {
            background: #e2e2e2;
            margin: 10px 0;
            padding: 10px;
            border-radius: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Начальное окно</h1>
    <h2>Проекты, которые вы администрируете:</h2>
    <ul class="project-list">
        <li>Проект 1</li>
        <li>Проект 2</li>
        <li>Проект 3</li>
    </ul>

    <h2>Проекты, на которые вы подписались:</h2>
    <ul class="project-list">
        <li>Проект A</li>
        <li>Проект B</li>
        <li>Проект C</li>
    </ul>
</div>
</body>
</html>
