package com.company.studer.common.helper;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {

        String uploadRootPath = Paths.get(uploadDir).toString();

        File uploadRootDir = new File(uploadRootPath);
        // Create directory if it not exists.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            BufferedImage bi = ImageIO.read(inputStream);
            File outputFile = new File(uploadRootDir + "/" + fileName);
            ImageIO.write(bi, "jpg", outputFile);
        } catch (IOException e) {
            throw new IOException("Could not save image file: " + fileName, e);
        }
    }
}
