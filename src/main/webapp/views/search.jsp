<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.msnigirev.oris.collaboration.entity.Project" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Поиск</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f9f9f9;
      color: #333;
    }
    .search-container {
      padding: 20px;
      background-color: #fff;
      border-bottom: 1px solid #ccc;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    .search-container input[type="text"] {
      width: calc(100% - 120px);
      padding: 10px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .search-container button {
      padding: 10px 20px;
      font-size: 16px;
      background-color: #007BFF;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .search-container button:hover {
      background-color: #0056b3;
    }
    .results {
      padding: 20px;
    }
    .result-item {
      padding: 15px;
      margin: 10px 0;
      background-color: #fff;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }
    .result-item a {
      color: #007BFF;
      text-decoration: none;
    }
    .result-item a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>

<div class="search-container">
  <form action="<%= request.getContextPath() %>/search" method="get">
    <input type="text" name="text" placeholder="Введите запрос" required>
    <button type="submit">Искать</button>
  </form>
</div>

<div class="results">
  <%-- Пример рендеринга результатов поиска. Данные передаются из сервлета --%>
  <h2>Результат поиска: </h2>
  <ul class="result-item">
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
    <li>Ничего не найдено</li>
    <%
      }
    %>
  </ul>
</div>

</body>
</html>
