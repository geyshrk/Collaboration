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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "project", value = "/project")
public class ProjectServlet extends HttpServlet {

    private static final String BASE_PROJECT_PATH = "/Users/misha/IdeaProjects/ITIS/Collaboration/src/main/java/ru/msnigirev/oris/collaboration/servlet"; // Замените на ваш корневой путь

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

        req.getRequestDispatcher("/views/project.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
