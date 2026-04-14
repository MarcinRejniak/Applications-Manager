package pl.rejniak.claim.domain.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rejniak.claim.domain.application.AppRepository;
import pl.rejniak.claim.domain.application.State;
import pl.rejniak.claim.domain.application.dto.AppDto;
import pl.rejniak.claim.domain.application.exception.StateNotFoundException;
import pl.rejniak.claim.domain.application.mapper.AppMapper;
import pl.rejniak.claim.domain.application.model.AppEntity;

@Service
@AllArgsConstructor
public class AppService {
    private final AppRepository appRepository;
    private final AppMapper appMapper;

    public AppDto create(AppDto appDto) {
        AppEntity appEntity = appMapper.map(appDto);
        appEntity.setState(State.CREATED);
        AppEntity savedEntity = this.appRepository.save(appEntity);

        return appMapper.map(savedEntity);
    }

    public AppDto verify(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED) {
            appEntity.setState(State.VERIFIED);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.CREATED);
        }
    }

    public AppDto accept(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.VERIFIED) {
            appEntity.setState(State.ACCEPTED);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.VERIFIED);
        }
    }

    public AppDto publish(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.ACCEPTED) {
            appEntity.setState(State.PUBLISHED);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.ACCEPTED);
        }
    }

    public AppDto delete(Long id, String reason) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED) {
            appEntity.setState(State.DELETED);
            appEntity.setReason(reason);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.CREATED);
        }
    }

    public AppDto reject(Long id, String reason) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.VERIFIED || appEntity.getState() == State.ACCEPTED) {
            appEntity.setState(State.REJECTED);
            appEntity.setReason(reason);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.VERIFIED, State.ACCEPTED);
        }
    }

    public AppDto changeContent(Long id, String newContent) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED || appEntity.getState() == State.VERIFIED) {
            appEntity.setContent(newContent);
            AppEntity savedEntity = appRepository.save(appEntity);
            return this.appMapper.map(savedEntity);
        } else {
            throw new StateNotFoundException(id, State.CREATED, State.VERIFIED);
        }
    }

    public AppDto getOne(Long id) {
        return this.appMapper.map(getEntityById(id));
    }

    public Page<AppDto> getAll(State state, Pageable pageable) {
        Page<AppEntity> entities;

        if (state != null) {
            entities = appRepository.findAllByState(state, pageable);
        } else {
            entities = appRepository.findAll(pageable);
        }

        return entities.map(appMapper::map);
    }

    private AppEntity getEntityById(Long id) {
        return this.appRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("AppEntity not found, appId: [%s]".formatted(id))
        );
    }
}
