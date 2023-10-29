package com.group.atelier.dto.mapper;

import com.group.atelier.dto.response.HomepageImageResponse;
import com.group.atelier.model.entity.HomepageImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mapper(componentModel = "spring")
public abstract class HomepageImageMapper {

    @Mapping(source = "path", target = "image", qualifiedByName = "mapImage")
    public abstract HomepageImageResponse entityToResponse(HomepageImage homepageImage);

    @Named("mapImage")
    protected byte[] mapImage(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }
}
