<%@ page import="ru.msnigirev.oris.collaboration.entity.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Начальная страница</title>
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
    <h1>Начальная страница</h1>

    <h2>Популярные проекты: </h2>
    <ul class="project-list">
        <%
            List<Project> projects = (List<Project>) request.getAttribute("projects");
            if (projects != null && !projects.isEmpty()) {
                for (Project project : projects) {
                    String name = project.getName();
                    int id = project.getId();
        %>
        <li>
            <a href="<%= request.getContextPath() %>/project?id=<%= id %>"><%= name %> </a>
        </li>
        <%
            }
        } else {
        %>
        <li>К сожалению, на сайте пока нет проектов</li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>
