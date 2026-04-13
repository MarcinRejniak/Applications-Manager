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
        this.appRepository.save(appEntity);

        return appMapper.map(appEntity);
    }

    public AppDto verify(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED) {
            appEntity.setState(State.VERIFIED);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new StateNotFoundException(State.CREATED, id);
        }
    }

    public AppDto accept(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.VERIFIED) {
            appEntity.setState(State.ACCEPTED);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new StateNotFoundException(State.VERIFIED, id);
        }
    }

    public AppDto publish(Long id) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.ACCEPTED) {
            appEntity.setState(State.PUBLISHED);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new StateNotFoundException(State.ACCEPTED, id);
        }
    }

    public AppDto delete(Long id, String reason) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED) {
            appEntity.setState(State.DELETED);
            appEntity.setReason(reason);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new StateNotFoundException(State.CREATED, id);
        }
    }

    public AppDto reject(Long id, String reason) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.VERIFIED || appEntity.getState() == State.ACCEPTED) {
            appEntity.setState(State.REJECTED);
            appEntity.setReason(reason);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new StateNotFoundException(State.VERIFIED, id);
        }
    }

    public AppDto changeContent(Long id, String newContent) {
        AppEntity appEntity = getEntityById(id);

        if (appEntity.getState() == State.CREATED || appEntity.getState() == State.VERIFIED) {
            appEntity.setContent(newContent);
            appRepository.save(appEntity);
            return this.appMapper.map(appEntity);
        } else {
            throw new EntityNotFoundException("AppEntity 'CREATED' or 'VERIFIED' not found, appId: [%s]".formatted(id));
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
