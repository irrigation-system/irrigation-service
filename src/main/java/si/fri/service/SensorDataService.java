package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import si.fri.dto.SensorDataDto;
import si.fri.entities.SensorDataEntity;
import si.fri.entities.UserDataEntity;
import si.fri.mapper.SensorDataMapper;
import si.fri.repositories.SensorDataRepository;

@ApplicationScoped
public class SensorDataService {

    @Inject
    SensorDataRepository sensorDataRepository;

    @Inject
    UserService userService;

    @Inject
    SensorDataMapper mapper;

    public SensorDataDto getLatest(String userToken) {

        UserDataEntity user = userService.getUserByToken(userToken);

        SensorDataEntity lastKnown = sensorDataRepository.findLatest(user);
        if (lastKnown == null) {
            throw new NotFoundException("No weather data available for location: " + user.getLocation());
        }
        return mapper.toDto(lastKnown);

    }
}
