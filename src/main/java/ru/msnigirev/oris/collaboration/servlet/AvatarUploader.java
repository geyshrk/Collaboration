package ru.msnigirev.oris.collaboration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.msnigirev.oris.collaboration.service.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@MultipartConfig
@WebServlet("/avatarupload")
public class AvatarUploader extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "avatars";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");
        // Получаем файл из запроса
        Part filePart = req.getPart("file"); // "file" - это имя поля в форме
        String fileName = filePart.getSubmittedFileName(); // Получаем имя файла
        // Определяем путь для сохранения файла
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Создаем директорию, если она не существует
        }

        // Сохраняем файл
        File file = new File(uploadDir, fileName);
        try (InputStream inputStream = filePart.getInputStream()) {
            Files.copy(inputStream, file.toPath());
            userService.addAvatar( req.getServletContext().getContextPath()
                    + File.separator + UPLOAD_DIRECTORY + File.separator +
                    file.getName(), (String) req.getSession().getAttribute("username"));
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
