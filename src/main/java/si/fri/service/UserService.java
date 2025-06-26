package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import si.fri.dto.CreateUserDto;
import si.fri.entities.CropEntity;
import si.fri.entities.UserDataEntity;
import si.fri.mapper.CreateUserMapper;
import si.fri.repositories.CropRepository;
import si.fri.repositories.UserDataRepository;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    UserDataRepository repository;

    @Inject
    CropRepository cropRepository;

    @Inject
    CreateUserMapper mapper;


    public UserDataEntity getUserByToken(String token) {

        try {
            return repository.findByUserToken(token);
        } catch (NoResultException e) {
            throw new EntityNotFoundException("User with token '" + token + "' does not exist.");
        }

    }

    @Transactional
    public String createUser(CreateUserDto createUserDto) {

        UUID cropId;
        try {
            cropId = UUID.fromString(createUserDto.cropId());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid UUID format for cropId: " + createUserDto.cropId());
        }

        CropEntity crop = cropRepository.findById(cropId);
        if (crop == null) {
            throw new NotFoundException("Crop with ID " + cropId + " not found.");
        }

        UserDataEntity entity = mapper.toEntity(createUserDto);
        entity.setCropEntity(crop);

        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

        entity.setUserToken(token);

        repository.persist(entity);

        return token;
    }

}
