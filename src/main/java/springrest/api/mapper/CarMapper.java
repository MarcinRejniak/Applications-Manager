package springrest.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import springrest.api.dto.CarDto;
import springrest.infrastructure.database.entity.CarEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    CarDto map(CarEntity carEntity);

    CarEntity map(CarDto carDto);
}
