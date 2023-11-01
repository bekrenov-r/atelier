package com.group.atelier.dto.mapper;

import com.group.atelier.dto.response.CoatModelResponse;
import com.group.atelier.model.entity.CoatModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mapper(componentModel = "spring")
public abstract class CoatModelMapper {

    @Mapping(source = "imagePath", target = "image", qualifiedByName = "mapImage")
    public abstract CoatModelResponse entityToResponse(CoatModel coatModel);

    @Named("mapImage")
    protected byte[] mapImage(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }
}
