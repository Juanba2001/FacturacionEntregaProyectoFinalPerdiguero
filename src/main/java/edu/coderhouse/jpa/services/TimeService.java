package edu.coderhouse.jpa.services;

import edu.coderhouse.jpa.DTO.TimeApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TimeService {
    public LocalDateTime getCurrentDateTime() {
        String url = "https://www.timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TimeApiResponse> response = restTemplate.getForEntity(url, TimeApiResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().toLocalDateTime();
        } else {
            throw new RuntimeException("No fue posible obtener fecha y hora");
        }
    }
}
