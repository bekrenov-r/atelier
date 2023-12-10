package com.group.atelier.business.coatmodel;

import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.util.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CoatModelImageService {
    private final ImageService imageService;

    private static final String COAT_MODELS_IMG_DIR_PATH = "src/main/resources/images/coat_models";

    public String saveImageForCoatModel(CoatModel coatModel, MultipartFile file) throws IOException {
        File targetDir = new File(COAT_MODELS_IMG_DIR_PATH + '/' + coatModel.getCoatType().getImgDirName());
        imageService.removeOldImageIfPresent(coatModel.getImgPath());
        return imageService.saveImage(file.getInputStream(), targetDir);
    }
}
