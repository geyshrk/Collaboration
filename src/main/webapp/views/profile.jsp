<%@ page import="ru.msnigirev.oris.collaboration.entity.Project" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Профиль</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      color: #343a40;
      margin: 0;
      padding: 20px;
    }
    h1, h2, h3 {
      color: #212529;
    }
    .container {
      background: #ffffff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .avatar {
      width: 150px;
      height: 150px;
      border-radius: 50%;
      margin-bottom: 20px;
      border: 2px solid #dee2e6;
    }
    input[type="file"], input[type="submit"] {
      margin-top: 10px;
      padding: 10px;
      background-color: #007bff;
      color: #ffffff;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }
    input[type="submit"]:hover {
      background-color: #0056b3;
    }
    .popular-projects {
      list-style-type: none;
      padding: 0;
    }
    .popular-projects li {
      background: #f1f3f5;
      margin: 10px 0;
      padding: 10px;
      border-radius: 3px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    .popular-projects li a {
      color: #007bff;
      text-decoration: none;
    }
    .popular-projects li a:hover {
      text-decoration: underline;
    }
    p {
      background: #f8f9fa;
      padding: 15px;
      border-radius: 5px;
    }
  </style>
</head>
<body>
<div class="container">
  <a href="<%= request.getContextPath() %>/index" style="text-decoration: none; color: #000; font-weight: bold; margin-right: 10px;">
    На главную
  </a>
  <form action="<%= request.getContextPath() %>/search" method="get" style="flex-grow: 1; display: flex; align-items: center; justify-content: center;">
    <input type="text" name="text" placeholder="Введите запрос для поиска"
           style="width: 300px; padding: 5px; border: 1px solid #ccc; border-radius: 5px;" required />
    <button type="submit" style="padding: 5px 10px; margin-left: 10px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">
      Искать
    </button>
  </form>

  <div style="display: flex; align-items: center; gap: 10px;">
    <a href="<%= request.getContextPath() %>/create"
       style="text-decoration: none; color: #fff; background-color: #28a745; padding: 5px 10px; border-radius: 5px; font-weight: bold;">
      Создать новый проект
    </a>

    <a href="<%= request.getContextPath() %>/logout"
       style="text-decoration: none; color: #fff; background-color: #dc3545; padding: 5px 10px; border-radius: 5px; font-weight: bold;">
      Выйти из аккаунта
    </a>
  </div>
  <h1>Профиль</h1>
  <img src="<%= request.getAttribute("avatar") %>" alt="Аватар" class="avatar">
  <form action="avatarupload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <input type="submit" value="Загрузить">
  </form>
  <h2><%= "Имя: " + request.getAttribute("name") %></h2>
  <h3><%= "Username: @" + request.getAttribute("username") %></h3>

  <h2>Самые популярные проекты:</h2>
  <ul class="popular-projects">
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
    <li>У пользователя пока нет проектов</li>
    <%
      }
    %>
  </ul>

  <h2>Описание профиля:</h2>
  <p><%= request.getAttribute("description") %></p>
</div>
</body>
</html>


