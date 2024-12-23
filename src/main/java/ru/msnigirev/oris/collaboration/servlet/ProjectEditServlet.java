package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "projectEdit", value = "/projectEdit")
public class ProjectEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
//        ProjectService projectService = (ProjectService) req.getServletContext().getAttribute("projectService");
//        ProjectDto project = projectService.getDtoById(Integer.parseInt(req.getParameter("id")));
//
//        String creatorName = userService.getUsernameById(project.getCreatorId());
//
//        req.setAttribute("projectName", project.getName());
//        req.setAttribute("avatarUrl", "");
//        req.setAttribute("creatorName", creatorName);
//        req.setAttribute("projectDescription", project.getDescription());
//
//        req.setAttribute("teacherName", project.getTeacher());
//        req.setAttribute("subjectName", project.getSubject());
//        req.setAttribute("instituteName", project.getInstitute());
//        req.setAttribute("year", project.getYear());
//

        req.getRequestDispatcher("/views/projectEdit.jsp").forward(req, res);
        /*
          <h2><%= request.getAttribute("name") %></h2>
`                <img src="<%= request.getAttribute("avatar") %>" alt="Аватар" class="avatar"> <!-- Замените на путь к вашей аватарке -->
          <h3><%= request.getAttribute("username") %></h3>

          <h2>Самые популярные проекты:</h2>
          <ul class="popular-projects">
            <li>Популярный проект 1</li>
            <li>Популярный проект 2</li>
          </ul>
            <li>Популярный проект 3</li>

          <p><%= request.getAttribute("description") %></p>
          <h2>Описание профиля:</h2>
         */
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}
