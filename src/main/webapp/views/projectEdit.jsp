<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Редактирование проекта</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    ul {
      list-style-type: none;
      padding-left: 20px;
    }
    .folder, .file {
      display: flex;
      align-items: center;
      margin-bottom: 5px;
    }
    .folder-header, .file-header {
      display: flex;
      align-items: center;
    }
    .folder-header strong, .file-header span {
      margin-right: 10px;
    }
    .button, .delete-button {
      cursor: pointer;
      background-color: #007BFF;
      color: white;
      border: none;
      padding: 5px 10px;
      margin-left: 10px;
      border-radius: 3px;
    }
    .delete-button {
      background-color: #dc3545;
    }
    .button:hover {
      background-color: #0056b3;
    }
    .delete-button:hover {
      background-color: #b02a37;
    }
  </style>
</head>
<body>
<h1>Редактирование проекта</h1>
<div id="project-container">
  <h3>Проект: ${projectName}</h3>
  <ul id="project-structure">
    <!-- Динамическая структура проекта -->
    <c:forEach var="folder" items="${folders}">
      <li class="folder">
        <div class="folder-header">
          <strong>📁 ${folder.name}</strong>
          <button class="button" onclick="createSubFolder('${folder.id}')">Добавить вложенную папку</button>
          <button class="button" onclick="addFile('${folder.id}')">Добавить файл</button>
          <button class="delete-button" onclick="deleteFolder('${folder.id}')">Удалить папку</button>
        </div>
        <ul id="folder-${folder.id}">
          <c:forEach var="file" items="${folder.files}">
            <li class="file">
              <div class="file-header">
                <span>📄 ${file.name}</span>
                <button class="delete-button" onclick="deleteFile('${file.id}')">Удалить файл</button>
              </div>
            </li>
          </c:forEach>
        </ul>
      </li>
    </c:forEach>
  </ul>
</div>
<div>
  <button class="button" onclick="createFolder()">Добавить папку</button>
</div>

<script>
  const projectId = "${projectId}";

  // Создание новой папки
  function createFolder() {
    const folderName = prompt("Введите имя новой папки:");
    if (folderName) {
      fetch(`/ProjectEditServlet?action=createFolder`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ projectId, folderName, parentId: null })
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  location.reload(); // Перезагружаем страницу для отображения изменений
                } else {
                  alert("Ошибка создания папки.");
                }
              });
    }
  }

  // Создание вложенной папки
  function createSubFolder(parentId) {
    const folderName = prompt("Введите имя вложенной папки:");
    if (folderName) {
      fetch(`/ProjectEditServlet?action=createFolder`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ projectId, folderName, parentId })
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  location.reload();
                } else {
                  alert("Ошибка создания вложенной папки.");
                }
              });
    }
  }

  // Добавление файла
  function addFile(parentId) {
    const fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.style.display = "none";

    fileInput.onchange = function () {
      const formData = new FormData();
      formData.append("projectId", projectId);
      formData.append("parentId", parentId);
      formData.append("file", fileInput.files[0]);

      fetch(`/ProjectEditServlet?action=addFile`, {
        method: "POST",
        body: formData
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  location.reload();
                } else {
                  alert("Ошибка добавления файла.");
                }
              });
    };

    document.body.appendChild(fileInput);
    fileInput.click();
    document.body.removeChild(fileInput);
  }

  // Удаление папки
  function deleteFolder(folderId) {
    if (confirm("Вы действительно хотите удалить эту папку?")) {
      fetch(`/ProjectEditServlet?action=deleteFolder`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ folderId })
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  location.reload();
                } else {
                  alert("Ошибка удаления папки.");
                }
              });
    }
  }

  // Удаление файла
  function deleteFile(fileId) {
    if (confirm("Вы действительно хотите удалить этот файл?")) {
      fetch(`/ProjectEditServlet?action=deleteFile`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ fileId })
      })
              .then(response => response.json())
              .then(data => {
                if (data.success) {
                  location.reload();
                } else {
                  alert("Ошибка удаления файла.");
                }
              });
    }
  }
</script>
</body>
</html>
