package ee.helmes.test.mapper;

import ee.helmes.test.dto.UserDataForm;
import ee.helmes.test.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sectors", ignore = true)
    void updateEntityFromDto(UserDataForm dto, @MappingTarget UserData entity);
}