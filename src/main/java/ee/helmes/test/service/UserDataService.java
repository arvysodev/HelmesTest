package ee.helmes.test.service;

import ee.helmes.test.dto.UserDataForm;
import ee.helmes.test.dto.UserSessionData;
import ee.helmes.test.entity.Sector;
import ee.helmes.test.entity.UserData;
import ee.helmes.test.mapper.UserDataMapper;
import ee.helmes.test.repository.SectorRepository;
import ee.helmes.test.repository.UserDataRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final SectorRepository sectorRepository;
    private final UserDataMapper userDataMapper;

    @Transactional
    public UserData saveOrUpdateUserData(UserDataForm dto, Long existingUserId) {
        UserData entity = userDataRepository.findById(existingUserId != null ? existingUserId : -1L)
                .orElse(new UserData());

        userDataMapper.updateEntityFromDto(dto, entity);

        entity.getSectors().clear();
        if (dto.selectedSectorIds() != null && !dto.selectedSectorIds().isEmpty()) {
            Set<Sector> selectedSectors = new HashSet<>(sectorRepository.findAllById(dto.selectedSectorIds()));
            entity.setSectors(selectedSectors);
        }

        return userDataRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public UserSessionData getUserDataForSession(Long userId) {
        UserData entity = userDataRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        String name = entity.getName();
        boolean agreedToTerms = entity.isAgreedToTerms();

        Set<Long> selectedSectorIds = entity.getSectors().stream()
                .map(Sector::getId)
                .collect(Collectors.toSet());

        return new UserSessionData(name, agreedToTerms, selectedSectorIds);
    }
}