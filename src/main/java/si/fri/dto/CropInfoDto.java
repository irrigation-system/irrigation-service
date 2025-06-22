package si.fri.dto;

public record CropInfoDto(
        // Fields from CropEntity
        String name,
        Float minAllowedMoisture,
        Float coefficientDev,
        Float coefficientMid,
        Float coefficientLate,
        Long devNumOfDays,
        Long midNumOfDays,
        Long latNumOfDays
) {
}
