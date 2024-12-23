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
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@MultipartConfig
@WebServlet("/avatarupload")
public class AvatarUploader extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "/Users/misha/IdeaProjects/ITIS/Collaboration/src/main/webapp/resources/avatars";
    private static final String DOWNLOAD_DIRECTORY = "/resources/avatars";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = (UserService) req.getServletContext().getAttribute("userService");

        Part filePart = req.getPart("file");
        if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file uploaded");
            return;
        }

        String originalFileName = filePart.getSubmittedFileName();
        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        // Проверяем директорию для временного сохранения
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("Unable to create upload directory: " + UPLOAD_DIRECTORY);
        }

        // Путь для постоянного хранения файла
        File fileToSave = new File(uploadDir, uniqueFileName);

        // Временный путь, из которого будут стираться аватарки при редиплое
        String avatarPath = getServletContext().getRealPath(DOWNLOAD_DIRECTORY) + File.separator + uniqueFileName;
        File avatarFile = new File(avatarPath);

        try (InputStream inputStream = filePart.getInputStream()) {
            // Сохраняем файл во временной директории
            Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Копируем файл в конечную директорию
            Files.copy(fileToSave.toPath(), avatarFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Формируем URL для доступа к файлу
            String avatarUrl = req.getContextPath() + DOWNLOAD_DIRECTORY + "/" + uniqueFileName;

            // Сохраняем аватар в системе
            String username = (String) req.getSession().getAttribute("username");

            userService.addAvatar(avatarUrl, username);

            // Перенаправляем на профиль
            res.sendRedirect(req.getContextPath() + "/profile");
        } catch (IOException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving file: " + e.getMessage());
        }
    }
}
