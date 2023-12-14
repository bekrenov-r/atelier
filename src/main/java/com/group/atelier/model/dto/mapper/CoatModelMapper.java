package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.response.CoatModelResponse;
import com.group.atelier.model.entity.CoatModel;
import com.group.atelier.util.ImageService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public abstract class CoatModelMapper {
    @Autowired
    protected ImageService imageService;

    @Mapping(source = "imgPath", target = "image", qualifiedByName = "mapImage")
    public abstract CoatModelResponse entityToResponse(CoatModel coatModel);

    @Named("mapImage")
    protected byte[] mapImage(String path) throws IOException {
        return imageService.extractImage(path);
    }
}
