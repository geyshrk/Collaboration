<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("projectName") %></title>
    <link rel="stylesheet" type="text/css">
    <style>
        .edit-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
        }
        .edit-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<a href="<%= request.getContextPath() %>/projectEdit" class="edit-button">Редактировать проект</a>

<div class="container">
    <div class="header">
        <h1><%= request.getAttribute("projectName") %></h1>
        <div class="avatar">
            <img src="<%= request.getContextPath() + request.getAttribute("avatarUrl") %>" alt="Avatar" />
        </div>
    </div>

    <div class="creator">
        <strong>Создатель:</strong> <%= request.getAttribute("creatorName") %>
    </div>

    <p class="description"><%= request.getAttribute("description") %></p>

    <div class="tags">
            <span class="tag">
                <a href="search?teacherId=<%= request.getAttribute("teacherName") %>">Преподаватель: <%= request.getAttribute("teacherName") %></a>
            </span>
        <span class="tag">
                <a href="search?instituteId=<%= request.getAttribute("instituteName") %>">Институт: <%= request.getAttribute("instituteName") %></a>
            </span>
        <span class="tag">
                <a href="search?year=<%= request.getAttribute("year") %>">Год: <%= request.getAttribute("year") %></a>
            </span>
        <span class="tag">
                <a href="search?subjectId=<%= request.getAttribute("subjectName") %>">Предмет: <%= request.getAttribute("subjectName") %></a>
            </span>
    </div>

    <h2>Материалы проекта</h2>

    <!-- Контейнер для отображения файлов и папок -->
    <div id="file-container"></div>
</div>

</body>
</html>
