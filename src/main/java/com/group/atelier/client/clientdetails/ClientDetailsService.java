package com.group.atelier.client.clientdetails;

import com.group.atelier.model.dto.mapper.ClientDetailsMapper;
import com.group.atelier.model.dto.request.ClientDetailsRequest;
import com.group.atelier.model.dto.response.ClientDetailsResponse;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.ClientDetails;
import com.group.atelier.client.clientdetails.ClientDetailsRepository;
import com.group.atelier.client.ClientRepository;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.group.atelier.exception.ApplicationExceptionReason.*;

@Service
@RequiredArgsConstructor
public class ClientDetailsService {
    private final ClientDetailsRepository clientDetailsRepository;
    private final CurrentUserUtil currentUserUtil;
    private final ClientRepository clientRepository;
    private final ClientDetailsMapper clientDetailsMapper;

    public void createClientDetailsForCurrentUser(ClientDetailsRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(clientDetailsRepository.existsById(client.getId()))
            throw new ApplicationException(CLIENT_DETAILS_ALREADY_EXIST);
        ClientDetails clientDetails = clientDetailsMapper.requestToEntity(request);
        clientDetails.setClient(client);
        clientDetailsRepository.save(clientDetails);
    }

    public ClientDetailsResponse getClientDetailsByCurrentUser() {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        ClientDetails clientDetails = clientDetailsRepository.findById(client.getId())
                .orElseThrow(() -> new ApplicationException(CLIENT_DETAILS_NOT_FOUND));
        return clientDetailsMapper.entityToResponse(clientDetails);
    }

    public ClientDetailsResponse updateClientDetailsForCurrentUser(ClientDetailsRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        ClientDetails clientDetails = clientDetailsRepository.findById(client.getId())
                .orElseThrow(() -> new ApplicationException(CLIENT_DETAILS_NOT_FOUND));
        ClientDetails updatedDetails = this.doUpdate(clientDetails, request);
        return clientDetailsMapper.entityToResponse(clientDetailsRepository.save(updatedDetails));
    }

    private ClientDetails doUpdate(ClientDetails clientDetails, ClientDetailsRequest request) {
        clientDetails.setBirthDate(request.birthDate());
        clientDetails.setPhoneNumber(request.phoneNumber());
        clientDetails.getAddress().setCity(request.address().city());
        clientDetails.getAddress().setStreet(request.address().street());
        clientDetails.getAddress().setBuildingNumber(request.address().buildingNumber());
        clientDetails.getAddress().setApartmentNumber(request.address().apartmentNumber());
        clientDetails.getAddress().setZipCode(request.address().zipCode());

        return clientDetails;
    }
}
