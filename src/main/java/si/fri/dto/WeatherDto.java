package si.fri.dto;

import java.time.OffsetDateTime;

public record WeatherDto(OffsetDateTime timestamp, Float rainfallmm, Float etRef, Float humidity, Float temperature) {

}
