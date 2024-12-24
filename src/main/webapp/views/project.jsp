<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("projectName") %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css">
</head>
<body>
<a href="<%= request.getContextPath() %>/projectEdit" class="edit-button">Редактировать проект</a>

<div class="container">
    <div class="header">
        <h1><%= request.getAttribute("projectName") %></h1>
        <div class="avatar">
            <img src="<%= request.getContextPath() + request.getAttribute("avatarUrl") %>" alt="no avatar" />
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
