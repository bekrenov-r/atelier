package com.group.atelier.client.clientmetrics;

import com.group.atelier.client.ClientRepository;
import com.group.atelier.client.clientmetrics.dto.ClientMetricsMapper;
import com.group.atelier.client.clientmetrics.dto.ClientMetricsRequest;
import com.group.atelier.client.clientmetrics.dto.ClientMetricsResponse;
import com.group.atelier.exception.ApplicationException;
import com.group.atelier.model.entity.Client;
import com.group.atelier.model.entity.ClientMetrics;
import com.group.atelier.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.group.atelier.exception.ApplicationExceptionReason.CLIENT_METRICS_ALREADY_EXIST;
import static com.group.atelier.exception.ApplicationExceptionReason.CLIENT_METRICS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ClientMetricsService {
    private final ClientMetricsRepository clientMetricsRepository;
    private final ClientRepository clientRepository;
    private final CurrentUserUtil currentUserUtil;
    private final ClientMetricsMapper clientMetricsMapper;

    public ClientMetricsResponse getClientMetricsByCurrentUser() {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        ClientMetrics clientMetrics = clientMetricsRepository.findById(client.getId())
                .orElseThrow(() -> new ApplicationException(CLIENT_METRICS_NOT_FOUND));
        return clientMetricsMapper.entityToResponse(clientMetrics);
    }

    public void createClientMetricsForCurrentUser(ClientMetricsRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        if(clientMetricsRepository.existsById(client.getId()))
            throw new ApplicationException(CLIENT_METRICS_ALREADY_EXIST);
        ClientMetrics clientMetrics = clientMetricsMapper.requestToEntity(request);
        clientMetrics.setClient(client);
        clientMetricsRepository.save(clientMetrics);
    }

    public ClientMetricsResponse updateClientMetricsForCurrentUser(ClientMetricsRequest request) {
        Client client = clientRepository.findByUser(currentUserUtil.getCurrentUser());
        ClientMetrics clientMetrics = clientMetricsRepository.findById(client.getId())
                .orElseThrow(() -> new ApplicationException(CLIENT_METRICS_NOT_FOUND));
        ClientMetrics updatedMetrics = this.doUpdate(clientMetrics, request);
        return clientMetricsMapper.entityToResponse(clientMetricsRepository.save(updatedMetrics));
    }

    private ClientMetrics doUpdate(ClientMetrics clientMetrics, ClientMetricsRequest request){
        clientMetrics.setNeckSemiCircumference(request.neckSemiCircumference());
        clientMetrics.setNeckSemiCircumference(request.neckSemiCircumference());
        clientMetrics.setChestSemiCircumference1(request.chestSemiCircumference1());
        clientMetrics.setChestSemiCircumference2(request.chestSemiCircumference2());
        clientMetrics.setChestSemiCircumference3(request.chestSemiCircumference3());
        clientMetrics.setWaistSemiCircumference(request.waistSemiCircumference());
        clientMetrics.setShoulderWidth(request.shoulderWidth());
        clientMetrics.setChestHeight(request.chestHeight());
        clientMetrics.setChestHeight1(request.chestHeight1());
        clientMetrics.setBackArmholeHeight(request.backArmholeHeight());
        clientMetrics.setBackLengthTillWaist(request.backLengthTillWaist());
        clientMetrics.setShoulderHeightSidelong(request.shoulderHeightSidelong());
        clientMetrics.setChestWidth(request.chestWidth());
        clientMetrics.setChestCenter(request.chestCenter());
        clientMetrics.setBackWidth(request.backWidth());
        clientMetrics.setWaistLengthFront(request.waistLengthFront());
        clientMetrics.setNeckBaseToFrontWaistLineDistance(request.neckBaseToFrontWaistLineDistance());

        return clientMetrics;
    }
}
