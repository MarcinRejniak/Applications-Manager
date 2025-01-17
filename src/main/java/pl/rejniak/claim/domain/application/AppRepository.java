package pl.rejniak.claim.domain.application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rejniak.claim.domain.application.model.AppEntity;

@Repository
public interface AppRepository extends CrudRepository<AppEntity, Long> {
}