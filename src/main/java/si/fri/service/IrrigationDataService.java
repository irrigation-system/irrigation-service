package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.dto.IrrigationDataDto;
import si.fri.entities.IrrigationDataEntity;
import si.fri.entities.UserDataEntity;
import si.fri.mapper.IrrigationMapper;

import java.time.LocalDate;
import java.time.YearMonth;

@ApplicationScoped
public class IrrigationDataService {

    @Inject
    WeatherService weatherService;

    @Inject
    IrrigationMapper irrigationMapper;

    public IrrigationDataDto getIrrigationData(UserDataEntity userDataEntity) {
        final IrrigationDataEntity irrigationEntity = userDataEntity.getIrrigationEntity();

        // Check if an irrigation entity exists
        if (irrigationEntity == null) {
            throw new IllegalStateException("Irrigation data not found for user");
        }

        String currentYearMonth = YearMonth.now().toString();

        if (irrigationEntity.getMonthlyRainfall() == null
                || irrigationEntity.getMonthlyRainfallMonth() == null
                || !irrigationEntity.getMonthlyRainfallMonth().equals(currentYearMonth)) {

            LocalDate now = LocalDate.now();
            YearMonth lastYearSameMonth = YearMonth.of(now.getYear() - 1, now.getMonth());
            LocalDate startDate = lastYearSameMonth.atDay(1);
            LocalDate endDate = lastYearSameMonth.atEndOfMonth();

            Float monthlyRainfall = weatherService.fetchHistoricalRainfallData(userDataEntity, startDate, endDate);

            irrigationEntity.setMonthlyRainfall(monthlyRainfall);
            irrigationEntity.setMonthlyRainfallMonth(currentYearMonth);

        }

        return irrigationMapper.toDto(irrigationEntity);
    }

}
