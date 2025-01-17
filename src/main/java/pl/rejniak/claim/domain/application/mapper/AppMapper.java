package pl.rejniak.claim.domain.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.rejniak.claim.domain.application.dto.AppDto;
import pl.rejniak.claim.domain.application.model.AppEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper {
    AppDto map(AppEntity appEntity);
    AppEntity map(AppDto appDto);
}
