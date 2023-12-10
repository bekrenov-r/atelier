package com.group.atelier.business.coatmodel;

import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.dto.mapper.CoatModelMapper;
import com.group.atelier.model.dto.request.CoatModelRequest;
import com.group.atelier.model.dto.response.CoatModelResponse;
import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.model.entity.Order;
import com.group.atelier.order.OrderRepository;
import com.group.atelier.util.ImageService;
import io.vavr.CheckedFunction1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.group.atelier.exception.ApplicationExceptionReason.CANNOT_DELETE_COAT_MODEL;
import static com.group.atelier.exception.ApplicationExceptionReason.COAT_MODEL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CoatModelService {
    private final CoatModelRepository coatModelRepository;
    private final CoatModelMapper coatModelMapper;
    private final OrderRepository orderRepository;
    private final CoatModelImageService coatModelImageService;
    private final ImageService imageService;

    public List<CoatModelResponse> getAllCoatModels() {
        return coatModelRepository.findAll().stream()
                .filter(coatModel -> coatModel.getImgPath() != null)
                .map(coatModelMapper::entityToResponse)
                .toList();
    }

    public Map<Long, byte[]> getAllOrderImagesForCoatModel(Long id) {
        CoatModel coatModel = coatModelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, id));
        List<Order> orders = orderRepository.findAllByCoatModel(coatModel);
        CheckedFunction1<Order, byte[]> mapImageFunction =
                order -> imageService.extractImage(order.getImgPath());

        return orders.stream()
                .filter(order -> order.getImgPath() != null)
                .collect(Collectors.toMap(Order::getId, mapImageFunction.unchecked()));
    }

    public CoatModelResponse createCoatModel(CoatModelRequest request) {
        CoatModel coatModel = CoatModel.builder()
                .name(request.name())
                .coatType(request.coatType())
                .price(request.price())
                .videoUrl(request.videoUrl())
                .build();
        return coatModelMapper.entityToResponse(coatModelRepository.save(coatModel));
    }

    public void attachImageToCoatModel(Long id, MultipartFile file) throws IOException {
        CoatModel coatModel = coatModelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, id));
        String imgPath = coatModelImageService.saveImageForCoatModel(coatModel, file);
        System.out.println(imgPath);
        coatModel.setImgPath(imgPath);
        coatModelRepository.save(coatModel);
    }

    public CoatModelResponse updateCoatModel(Long id, CoatModelRequest request) {
        CoatModel coatModel = coatModelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, id));

        coatModel.setCoatType(request.coatType());
        coatModel.setName(request.name());
        coatModel.setVideoUrl(request.videoUrl());
        coatModel.setPrice(request.price());

        return coatModelMapper.entityToResponse(coatModelRepository.save(coatModel));
    }

    public void deleteCoatModel(Long id) throws IOException {
        CoatModel coatModel = coatModelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(COAT_MODEL_NOT_FOUND, id));
        List<Order> associatedOrders = orderRepository.findAllByCoatModel(coatModel);
        if(!associatedOrders.isEmpty())
            throw new ApplicationException(CANNOT_DELETE_COAT_MODEL, id, associatedOrders.size());

        imageService.removeImageIfPresent(coatModel.getImgPath());
        coatModelRepository.delete(coatModel);
    }
}
