<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("projectName") %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/project.css">
    <style>
        .folder, .file {
            cursor: pointer;
            padding: 5px;
            margin-left: 20px;
        }
        .folder {
            font-weight: bold;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
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
</div>
<div class="container">
    <div style="margin-top: 20px;">
        <h2>Добавить администратора</h2>
        <form action="<%= request.getContextPath() %>/addAdmin" method="post" style="display: flex; align-items: center; gap: 10px;">
            <input type="text" name="username" placeholder="Введите имя пользователя"
                   style="padding: 5px; border: 1px solid #ccc; border-radius: 5px;" required />
            <button type="submit" style="padding: 5px 10px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">
                Добавить
            </button>
        </form>
    </div>
    <div class="header">
        <h1><%= request.getAttribute("projectName") %></h1>
        <div class="avatar">
            <img src="<%= request.getContextPath() + request.getAttribute("avatarUrl") %>" alt="no avatar" />
        </div>
    </div>


    <div class="creator">
        <strong>Создатель:</strong>
        <%= request.getAttribute("creatorName") %>
    </div>


    <p class="description"><%= request.getAttribute("description") %></p>

    <div class="tags">
        <span class="tag">
        Преподаватель: <%= request.getAttribute("teacherName") %>
        </span>
        <span class="tag">
        Институт: <%= request.getAttribute("instituteName") %>
        </span>
        <span class="tag">
        Год: <%= request.getAttribute("year") %>
        </span>
        <span class="tag">
        Предмет: <%= request.getAttribute("subjectName") %>
        </span>

    </div>

    <h2>Материалы проекта</h2>

    <!-- Контейнер для отображения файлов и папок -->
    <div id="file-container"></div>
</div>

<script>
    const json = '<%= request.getAttribute("json") %>';
    const context = '<%= request.getContextPath()%>'
    const projectStructure = JSON.parse(json);

    renderStructure(projectStructure, document.getElementById('file-container'));
    function renderStructure(data, container) {
        if (!data) return;

        if (data.files) {
            data.files.forEach(file => {
                const fileElement = document.createElement('div');
                fileElement.className = 'file';
                fileElement.textContent = file.name;
                fileElement.onclick = () => openFile(context + '/' + file.path);
                container.appendChild(fileElement);
            });
        }

        if (data.folders) {
            data.folders.forEach(folder => {
                const folderElement = document.createElement('div');
                folderElement.className = 'folder';
                folderElement.textContent = folder.path.split('/').pop();

                const folderContent = document.createElement('div');
                folderContent.className = 'hidden';

                folderElement.onclick = () => {
                    folderContent.classList.toggle('hidden');
                    if (!folderContent.hasChildNodes()) {
                        renderStructure(folder, folderContent);
                    }
                };

                container.appendChild(folderElement);
                container.appendChild(folderContent);
            });
        }
    }

    function openFile(path) {
        window.location.href = path;
    }
</script>
</body>
</html>
