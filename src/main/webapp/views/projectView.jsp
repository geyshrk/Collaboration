<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Файлы проекта</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/project.css">
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    .container {
      margin: 20px;
    }
    .file-entry {
      margin-left: calc(var(--level, 0) * 20px);
      cursor: pointer;
    }
    .file-entry.folder {
      font-weight: bold;
      color: #007BFF;
    }
    .file-entry.folder:hover {
      text-decoration: underline;
    }
    .file-entry.file {
      color: #333;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Файлы проекта</h1>
  <div id="file-container"></div>
</div>

<script>
  const fileContainer = document.getElementById('file-container');

  function loadFolder(path = '') {
    fetch(`${contextPath}/projectView?path=${encodeURIComponent(path)}`)
            .then(response => response.json())
            .then(data => {
              if (data.error) {
                alert(data.error);
                return;
              }
              fileContainer.innerHTML = '';
              renderFiles(data.files, path, 0);
            })
            .catch(error => console.error('Ошибка загрузки папки:', error));
  }

  function renderFiles(files, basePath, level) {
    files.forEach(file => {
      const fileElement = document.createElement('div');
      fileElement.className = `file-entry ${file.type}`;
      fileElement.style.setProperty('--level', level);
      fileElement.textContent = file.name;

      fileElement.addEventListener('dblclick', () => {
        if (file.type === 'folder') {
          loadFolder(`${basePath}/${file.name}`);
        } else if (file.type === 'file') {
          window.location.href = `${contextPath}/projectView?path=${encodeURIComponent(`${basePath}/${file.name}`)}&download=true`;
        }
      });

      fileContainer.appendChild(fileElement);
    });
  }

  // Инициализация
  const contextPath = '<%= request.getContextPath() %>';
  loadFolder(); // Загрузка корневой папки
</script>
</body>
</html>
