package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.msnigirev.oris.collaboration.service.ProjectService;

import java.io.IOException;

public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService projectService = (ProjectService) req.getServletContext().getAttribute("projectService");
        projectService.addAdmin();
        req.getRequestDispatcher("views/project")ж
    }
}
