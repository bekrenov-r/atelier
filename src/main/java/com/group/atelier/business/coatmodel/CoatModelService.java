package com.group.atelier.business.coatmodel;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.dto.mapper.CoatModelMapper;
import com.group.atelier.model.dto.response.CoatModelResponse;
import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.model.entity.Order;
import com.group.atelier.order.OrderImageService;
import com.group.atelier.order.OrderRepository;
import io.vavr.CheckedFunction1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.group.atelier.exception.ApplicationExceptionReason.COAT_MODEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CoatModelService {
    private final CoatModelRepository coatModelRepository;
    private final CoatModelMapper coatModelMapper;
    private final OrderRepository orderRepository;
    private final OrderImageService orderImageService;

    public List<CoatModelResponse> getAllCoatModels() {
        return coatModelRepository.findAll().stream()
                .map(coatModelMapper::entityToResponse)
                .toList();
    }

    public List<byte[]> getAllOrderImagesForCoatModel(Long id) {
        CoatModel coatModel = coatModelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, id));
        List<Order> orders = orderRepository.findAllByCoatModel(coatModel);
        CheckedFunction1<Order, byte[]> mapImageFunction =
                order -> orderImageService.extractImage(order.getImgPath());

        return orders.stream()
                .filter(order -> order.getImgPath() != null)
                .map(mapImageFunction.unchecked())
                .toList();
    }
}
