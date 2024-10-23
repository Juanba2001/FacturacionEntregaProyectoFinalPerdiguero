package edu.coderhouse.jpa.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TimeApiResponse {
    @JsonProperty("dateTime")
    private  String dateTimeString;
    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.parse(dateTimeString);
    }
}
