package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.msnigirev.oris.collaboration.dto.ProjectDto;
import ru.msnigirev.oris.collaboration.service.ProjectService;
import ru.msnigirev.oris.collaboration.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "createProject", value = "/createProject")
public class ProjectCreation extends HttpServlet {
    private static final String folders = "/Users/misha/IdeaProjects/ITIS/Collaboration/.server/projects/folders/";
    private static final String avatars = "/Users/misha/IdeaProjects/ITIS/Collaboration/src/main/webapp/projects/avatars/";
    private static final String avatarsServer = "/projects/avatars/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String projectName = getFormField(req, "projectName");
        String projectDescription = getFormField(req, "description");
        String teacherName = getFormField(req, "teacherName");
        String subjectName = getFormField(req, "subjectName");
        String instituteName = getFormField(req, "instituteName");
        int year = Integer.parseInt(getFormField(req, "year"));

        UserService userService = (UserService) req.getServletContext().getAttribute("userService");

        // Получаем ID создателя проекта (например, из сессии пользователя)
        int creatorId = userService.getUser((String) req.getSession().getAttribute("username")).getId();

        String avatarUrl = null;
        if (req.getPart("avatar") != null) {
            avatarUrl = saveAvatar(req.getPart("avatar"), req.getContextPath());
        }

        // Создаем объект ProjectDto
        ProjectDto projectDto = ProjectDto.builder()
                .name(projectName)
                .description(projectDescription)
                .creatorId(creatorId)
                .teacher(teacherName)
                .subject(subjectName)
                .institute(instituteName)
                .year(year)
                .folder(folders + UUID.randomUUID() + "_" + projectName)
                .avatar(avatarUrl)
                .build();

        // Сохраняем проект (например, в базе данных)
        ProjectService projectService = (ProjectService) req.getServletContext().getAttribute("projectService");
        projectService.addNewProject(projectDto);

        // Перенаправляем на страницу проекта или на страницу со списком проектов
        res.sendRedirect(req.getContextPath() + "/projects");
    }

    // Метод для сохранения аватара в файловую систему и возврата его URL
    private String saveAvatar(Part avatarPart, String contextPath) throws IOException {
        File uploadDirFile = new File(avatars);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();  // Создаем директорию, если ее нет
        }

        // Генерируем уникальное имя для аватара (например, используя UUID)
        String avatarFileName = UUID.randomUUID() + "_" + avatarPart.getSubmittedFileName();
        File avatarFile = new File(uploadDirFile, avatarFileName);
        File avatarFileServer = new File(uploadDirFile, contextPath);
        // Сохраняем аватар в файловую систему
        avatarPart.write(avatarFile.getAbsolutePath());
        Files.copy(avatarFile.toPath(), avatarFileServer.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Возвращаем URL файла
        return avatarsServer + avatarFileName;
    }
    private String getFormField(HttpServletRequest req, String fieldName) throws IOException, ServletException {
        Part part = req.getPart(fieldName);
        if (part != null) {
            return new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        }
        return null;
    }
}