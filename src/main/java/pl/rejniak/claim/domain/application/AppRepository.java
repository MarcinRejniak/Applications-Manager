package pl.rejniak.claim.domain.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rejniak.claim.domain.application.model.AppEntity;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Long> {

    Page<AppEntity> findAllByState(State state, Pageable pageable);

    Page<AppEntity> findAll(Pageable pageable);
}