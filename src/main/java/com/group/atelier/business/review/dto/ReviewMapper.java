package com.group.atelier.business.review.dto;

import com.group.atelier.business.review.reply.dto.ReviewReplyToResponseConverter;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ReviewReplyToResponseConverter.class)
public abstract class ReviewMapper {

    @Mapping(source = "client", target = "clientFullName", qualifiedByName = "mapClientFullName")
    @Mapping(source = "client.user.username", target = "clientUsername")
    public abstract ReviewResponse entityToResponse(Review review);

    @Named("mapClientFullName")
    protected String mapFullName(Client client){
        return client.getFirstName() + " " + client.getLastName();
    }
}
