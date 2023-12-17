package com.group.atelier.model.dto.mapper;

import com.group.atelier.model.dto.response.ReviewResponse;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Review;
import com.group.atelier.review.reply.ReviewReplyToResponseConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ReviewReplyToResponseConverter.class)
public abstract class ReviewMapper {

    @Mapping(source = "client", target = "clientFullName", qualifiedByName = "mapClientFullName")
    public abstract ReviewResponse entityToResponse(Review review);

    @Named("mapClientFullName")
    protected String mapFullName(Client client){
        return client.getFirstName() + " " + client.getLastName();
    }
}
