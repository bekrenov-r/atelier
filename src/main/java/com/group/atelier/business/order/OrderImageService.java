package com.group.atelier.business.order;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Order;
import com.group.atelier.util.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.group.atelier.exception.ApplicationExceptionReason.ORDER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderImageService {
    private final ImageService imageService;
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private static final String ORDERS_IMAGES_DIR_PATH = "src/main/resources/images/orders";

    public void attachImageToOrder(Long id, MultipartFile file) throws IOException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        orderValidator.validateOrderOwnershipByEmployee(order);
        orderValidator.assertOrderIsCompleted(order);

        imageService.removeImageIfPresent(order.getImgPath());
        String imgPath = imageService.saveImage(file.getInputStream(), new File(ORDERS_IMAGES_DIR_PATH));
        order.setImgPath(imgPath);
        orderRepository.save(order);
    }

    public void removeImageFromOrder(Long id) throws IOException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, id));
        orderValidator.validateOrderOwnershipByEmployee(order);
        imageService.removeImageIfPresent(order.getImgPath());
        order.setImgPath(null);
        orderRepository.save(order);
    }

    public String getOrderImage(Long orderId) throws IOException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ORDER_NOT_FOUND, orderId));
        orderValidator.assertOrderIsCompleted(order);
        orderValidator.validateOrderOwnershipByCurrentUser(order);

        if(order.getImgPath() == null)
            return null;
        var bytes = imageService.extractImage(order.getImgPath());
        return new String(Base64.encodeBase64(bytes, false), StandardCharsets.UTF_8);
    }
}
