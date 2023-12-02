package com.group.atelier.order;

import com.group.atelier.model.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class OrderImageService {
    private static final String ORDERS_IMAGES_DIR_PATH = "src/main/resources/images/orders";
    private static final String IMG_FORMAT = "png";

    public String saveImageForOrder(Order order, MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getResource().getInputStream());
        removeOldImageIfPresent(order.getImgPath());
        File dir = new File(ORDERS_IMAGES_DIR_PATH);
        String name = generateFileName(order);
        File output = new File(dir, name);
        ImageIO.write(image, IMG_FORMAT, output);
        return ORDERS_IMAGES_DIR_PATH + '/' + name;
    }

    private void removeOldImageIfPresent(String imgPath) throws IOException {
        if(imgPath != null)
            Files.delete(Paths.get(imgPath));
    }

    private String generateFileName(Order order){
        LocalDateTime now = LocalDateTime.now();
        return new StringBuilder()
                .append(order.getId())
                .append('_')
                .append(now.toLocalDate().getYear())
                .append(now.toLocalDate().getMonthValue())
                .append(now.toLocalDate().getDayOfMonth())
                .append('T')
                .append(now.toLocalTime().getHour())
                .append(now.toLocalTime().getMinute())
                .append(now.toLocalTime().getSecond())
                .append(".")
                .append(IMG_FORMAT)
                .toString();
    }
}
