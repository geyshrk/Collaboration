<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Профиль</title>
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
      text-align: center;
    }
    .avatar {
      width: 150px;
      height: 150px;
      border-radius: 50%;
      margin-bottom: 20px;
    }
    .popular-projects {
      list-style-type: none;
      padding: 0;
    }
    .popular-projects li {
      background: #e2e2e2;
      margin: 10px 0;
      padding: 10px;
      border-radius: 3px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Профиль</h1>
  <img src="<%= request.getAttribute("avatar") %>" alt="Аватар" class="avatar">
  <form action="avatarupload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <input type="submit" value="Загрузить">
  </form>
  <h2><%= request.getAttribute("name") %></h2>
  <h3><%= request.getAttribute("username") %></h3>

  <h2>Самые популярные проекты:</h2>
  <ul class="popular-projects">
    <li>Популярный проект 1</li>
    <li>Популярный проект 2</li>
    <li>Популярный проект 3</li>
  </ul>

  <h2>Описание профиля:</h2>
  <p><%= request.getAttribute("description") %></p>
</div>
</body>
</html>
