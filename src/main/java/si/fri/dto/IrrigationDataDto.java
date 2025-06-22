package si.fri.dto;

import java.time.LocalDateTime;


public record IrrigationDataDto(
        LocalDateTime irrigationStart,
        String monthlyRainfallMonth,
        Float monthlyRainfall,
        Float cultivationArea
) {
}