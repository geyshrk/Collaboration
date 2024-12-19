package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "registerpage", value = "/registerpage")
public class RegisterPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("static/register.html")){
            is.transferTo(res.getOutputStream());
            res.setContentType("text/html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
