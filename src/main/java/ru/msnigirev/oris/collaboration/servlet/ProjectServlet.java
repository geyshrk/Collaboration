package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.msnigirev.oris.collaboration.dto.ProjectDto;
import ru.msnigirev.oris.collaboration.service.ProjectService;
import ru.msnigirev.oris.collaboration.service.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "project", value = "/project")
public class ProjectServlet extends HttpServlet {

    private static final String BASE_PROJECT_PATH = "/Users/misha/IdeaProjects/ITIS/Collaboration/src/main/java/ru/msnigirev/oris/collaboration/servlet"; // Замените на ваш корневой путь

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getParameter("file") != null) {
            // Логика для скачивания файла
            handleFileDownload(req, res);
            return;
        } 
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        ProjectService projectService = (ProjectService) req.getServletContext().getAttribute("projectService");

        // Получаем проект по ID
        ProjectDto project = projectService.getDtoById(Integer.parseInt(req.getParameter("id")));
        String creatorName = userService.getUsernameById(project.getCreatorId());

        req.setAttribute("projectName", project.getName());
        req.setAttribute("avatarUrl", project.getAvatar());
        req.setAttribute("creatorName", creatorName);
        req.setAttribute("description", project.getDescription());
        req.setAttribute("teacherName", project.getTeacher());
        req.setAttribute("subjectName", project.getSubject());
        req.setAttribute("instituteName", project.getInstitute());
        req.setAttribute("year", project.getYear());

        // Получение содержимого папки проекта
        String projectFolderPath = project.getFolder();
        File rootFolder = new File(projectFolderPath);
        if (!rootFolder.exists() || !rootFolder.isDirectory()) {
            req.setAttribute("fileTree", null); // Если папка не существует
        } else {
            Map<String, Object> fileTree = getFolderContents(rootFolder);
            req.setAttribute("fileTree", fileTree);
        }

        req.getRequestDispatcher("/views/project.jsp").forward(req, res);
    }

    private Map<String, Object> getFolderContents(File folder) {
        Map<String, Object> contents = new HashMap<>();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Рекурсивно добавляем содержимое папки
                    contents.put(file.getName(), getFolderContents(file));
                } else {
                    // Добавляем файл как строку
                    contents.put(file.getName(), file.getAbsolutePath());
                }
            }
        }
        return contents;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
    private void handleFileDownload(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String filePath = req.getParameter("file");
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден");
            return;
        }

        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        res.setContentLength((int) file.length());

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = res.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
