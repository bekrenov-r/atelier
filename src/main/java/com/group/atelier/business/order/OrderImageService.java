package com.group.atelier.business.order;

import com.group.atelier.model.entity.Order;
import com.group.atelier.util.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OrderImageService {
    private final ImageService imageService;
    private static final String ORDERS_IMAGES_DIR_PATH = "src/main/resources/images/orders";

    public String saveImageForOrder(Order order, MultipartFile file) throws IOException {
        File targetDir = new File(ORDERS_IMAGES_DIR_PATH);
        imageService.removeImageIfPresent(order.getImgPath());
        return imageService.saveImage(file.getInputStream(), targetDir);
    }
}
