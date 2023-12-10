package com.group.atelier.util;

import com.group.atelier.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static com.group.atelier.exception.ApplicationExceptionReason.FILE_IS_NOT_IMAGE;

@Service
public class ImageService {
    @Value("${spring.custom.img.format}")
    private String imgFormat;

    public String saveImage(InputStream inputStream, File targetDir){
        try {
            BufferedImage img = ImageIO.read(inputStream);
            String name = generateFileName();
            File output = new File(targetDir, name);
            ImageIO.write(img, imgFormat, output);
            return targetDir.getPath() + File.separator + name;
        } catch(IOException ex){
            throw new ApplicationException(FILE_IS_NOT_IMAGE);
        }
    }

    public byte[] extractImage(String imgPath) throws IOException {
        return imgPath != null
                ? Files.readAllBytes(Paths.get(imgPath))
                : null;
    }

    public void removeImageIfPresent(String imgPath) throws IOException {
        if(imgPath != null)
            Files.delete(Paths.get(imgPath));
    }

    private String generateFileName(){
        LocalDateTime now = LocalDateTime.now();
        return new StringBuilder()
                .append(now.toLocalDate())
                .append('_')
                .append(now.toLocalTime().getHour())
                .append(now.toLocalTime().getMinute())
                .append(now.toLocalTime().getSecond())
                .append('_')
                .append(now.toLocalTime().getNano())
                .append(".")
                .append(imgFormat)
                .toString();
    }
}
