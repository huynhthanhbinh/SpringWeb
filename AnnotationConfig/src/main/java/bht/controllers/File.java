package bht.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class File {

    private Logger logger = Logger.getRootLogger();

    @GetMapping("/upload")
    public String upload(HttpServletRequest request) {
        // views/file/upload.jsp
        return "file/upload";
    }

    @PostMapping("/upload")
    public String upload(
            HttpServletRequest request,
            @RequestParam("fileUpload") MultipartFile file) {

        // Create new path according to Origin file name
        String path = getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()
                .replace("/classes/",
                        "/resources/uploads/")
                + file.getOriginalFilename();


        logger.info(path);

        java.io.File newFile = new java.io.File(path);

        // Save File to web-resources (Server-side)
        // fileOutputStream.close() in finally clause !
        // Using try-with-resources for auto-close stream !
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(newFile)) {

            fileOutputStream.write(file.getBytes());

        } catch (IOException e) {
            logger.info(e);

        } finally {
            request.setAttribute("file", file);
        }

        return "file/view";
    }
}
