package pl.rejniak.claim;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.rejniak.claim.domain.application.AppRepository;
import pl.rejniak.claim.domain.application.State;
import pl.rejniak.claim.domain.application.dto.AppDto;
import pl.rejniak.claim.domain.application.exception.StateNotFoundException;
import pl.rejniak.claim.domain.application.mapper.AppMapper;
import pl.rejniak.claim.domain.application.model.AppEntity;
import pl.rejniak.claim.domain.application.service.AppService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AppServiceTest {

    @Mock
    AppRepository appRepository;

    @Mock
    AppMapper appMapper;

    @InjectMocks
    AppService appService;

    @Test
    void should_set_created_state_when_creating_application() {

//        given
        AppEntity entityBeforeSave = new AppEntity();

        Long appId = 1L;
        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.CREATED);

        AppDto inputDto = new AppDto(null, "name", "content", null, null);
        AppDto expectedDto = new AppDto(appId, "name", "content", "CREATED", null);

        given(appMapper.map(inputDto)).willReturn(entityBeforeSave);
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.create(inputDto);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.CREATED);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_set_verified_state_when_application_is_created() {

//        given
        Long appId = 1L;
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.CREATED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.VERIFIED);

        AppDto expectedDto = new AppDto(appId, "name", "content", "VERIFIED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.verify(appId);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.VERIFIED);

        verify(appRepository).save(entityBeforeSave);
    }
    
    @Test
    void should_throw_exception_when_verifying_app_is_not_created() {
        
//        given
        Long appId = 1L;
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.REJECTED);
        
        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));
        
//        when
//        then
        assertThatThrownBy(() -> appService.verify(appId))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s not found, appId: %d", State.CREATED, appId);
    }

    @Test
    void should_set_accepted_state_when_application_is_verified() {

//        given
        Long appId = 1L;
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.VERIFIED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.ACCEPTED);

        AppDto expectedDto = new AppDto(appId, "name", "content", "ACCEPTED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.accept(appId);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.ACCEPTED);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_throw_exception_when_accepting_app_is_not_verified() {

//        given
        Long appId = 1L;
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.REJECTED);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));

//        when
//        then
        assertThatThrownBy(() -> appService.accept(appId))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s not found, appId: %d", State.VERIFIED, appId);
    }

    @Test
    void should_set_published_state_when_application_is_accepted() {

//        given
        Long appId = 1L;
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.ACCEPTED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.PUBLISHED);

        AppDto expectedDto = new AppDto(appId, "name", "content", "PUBLISHED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.publish(appId);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.PUBLISHED);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_throw_exception_when_publishing_app_is_not_accepted() {

//        given
        Long appId = 1L;
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.REJECTED);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));

//        when
//        then
        assertThatThrownBy(() -> appService.publish(appId))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s not found, appId: %d", State.ACCEPTED, appId);
    }

    @Test
    void should_set_deleted_state_and_reason_when_application_is_created() {

//        given
        Long appId = 1L;
        String reason = "reason";
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.CREATED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.DELETED);
        entityAfterSave.setReason(reason);

        AppDto expectedDto = new AppDto(appId, "name", "content", "DELETED", reason);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.delete(appId, reason);

//        then
        assertThat(result).isEqualTo(expectedDto);

        assertThat(entityBeforeSave.getState()).isEqualTo(State.DELETED);
        assertThat(entityBeforeSave.getReason()).isEqualTo(reason);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_throw_exception_when_deleting_app_is_not_created() {

//        given
        Long appId = 1L;
        String reason = "reason";
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.ACCEPTED);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));

//        when
//        then
        assertThatThrownBy(() -> appService.delete(appId, reason))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s not found, appId: %d", State.CREATED, appId);
    }

    @Test
    void should_set_rejected_state_when_application_is_verified() {

//        given
        Long appId = 1L;
        String reason = "reason";
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.VERIFIED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.REJECTED);
        entityAfterSave.setReason(reason);

        AppDto expectedDto = new AppDto(appId, "name", "content", "REJECTED", reason);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.reject(appId, reason);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.REJECTED);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_set_rejected_state_when_application_is_accepted() {

//        given
        Long appId = 1L;
        String reason = "reason";
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.ACCEPTED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.REJECTED);
        entityAfterSave.setReason(reason);

        AppDto expectedDto = new AppDto(appId, "name", "content", "REJECTED", reason);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.reject(appId, reason);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getState()).isEqualTo(State.REJECTED);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_throw_exception_when_rejecting_app_is_not_accepted_and_verified() {

//        given
        Long appId = 1L;
        String reason = "reason";
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.CREATED);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));

//        when
//        then
        assertThatThrownBy(() -> appService.reject(appId, reason))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s, %s not found, appId: %d", State.VERIFIED, State.ACCEPTED, appId);
    }

    @Test
    void should_change_content_when_application_is_created() {

//        given
        Long appId = 1L;
        String newContent = "newContent";
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.CREATED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.CREATED);
        entityAfterSave.setContent(newContent);

        AppDto expectedDto = new AppDto(appId, "name", newContent, "CREATED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.changeContent(appId, newContent);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getContent()).isEqualTo(newContent);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_change_content_when_application_is_verified() {

//        given
        Long appId = 1L;
        String newContent = "newContent";
        AppEntity entityBeforeSave = new AppEntity();
        entityBeforeSave.setId(appId);
        entityBeforeSave.setState(State.VERIFIED);

        AppEntity entityAfterSave = new AppEntity();
        entityAfterSave.setId(appId);
        entityAfterSave.setState(State.VERIFIED);
        entityAfterSave.setContent(newContent);

        AppDto expectedDto = new AppDto(appId, "name", newContent, "VERIFIED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(entityBeforeSave));
        given(appRepository.save(entityBeforeSave)).willReturn(entityAfterSave);
        given(appMapper.map(entityAfterSave)).willReturn(expectedDto);

//        when
        AppDto result = appService.changeContent(appId, newContent);

//        then
        assertThat(result).isEqualTo(expectedDto);
        assertThat(entityBeforeSave.getContent()).isEqualTo(newContent);

        verify(appRepository).save(entityBeforeSave);
    }

    @Test
    void should_throw_exception_when_changing_content_app_is_not_created_and_verified() {

//        given
        Long appId = 1L;
        String newContent = "newContent";
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);
        appEntity.setState(State.REJECTED);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));

//        when
//        then
        assertThatThrownBy(() -> appService.changeContent(appId, newContent))
                .isInstanceOf(StateNotFoundException.class)
                .hasMessage("AppEntity %s, %s not found, appId: %d", State.CREATED, State.VERIFIED, appId);
    }

    @Test
    void should_return_app_dto_when_appEntity_is_found() {

//        given
        Long appId = 1L;
        AppEntity appEntity = new AppEntity();
        appEntity.setId(appId);

        AppDto expectedDto = new AppDto(appId, "name", "content", "CREATED", null);

        given(appRepository.findById(appId)).willReturn(Optional.of(appEntity));
        given(appMapper.map(appEntity)).willReturn(expectedDto);

//        when
        AppDto result = appService.getOne(appId);

//        then
        assertThat(result).isEqualTo(expectedDto);

        verify(appRepository).findById(appId);
    }

    @Test
    void should_throw_exception_when_appEntity_is_not_found() {

//        given
        Long appId = 1L;

        given(appRepository.findById(appId)).willReturn(Optional.empty());

//        when
//        then
        assertThatThrownBy(() -> appService.getOne(appId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("AppEntity not found, appId: [%s]", appId);
    }

    @Test
    void should_return_page_of_app_dtos_filtered_by_state() {

//        given
        State filterState = State.CREATED;
        Pageable pageable = PageRequest.of(0, 10);
        AppEntity appEntity = new AppEntity();
        appEntity.setState(filterState);

        Page<AppEntity> entityPage = new PageImpl<>(List.of(appEntity));
        AppDto expectedDto = new AppDto(1L, "name", "content", "CREATED", null);

        given(appRepository.findAllByState(filterState, pageable)).willReturn(entityPage);
        given(appMapper.map(appEntity)).willReturn(expectedDto);

//        when
        Page<AppDto> result = appService.getAll(filterState, pageable);

//        then
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).state()).isEqualTo(filterState.name());

        verify(appRepository).findAllByState(filterState, pageable);
    }

    @Test
    void should_return_page_of_app_dtos_when_state_is_null() {

//        given
        Pageable pageable = PageRequest.of(0, 10);
        AppEntity appEntity = new AppEntity();

        Page<AppEntity> entityPage = new PageImpl<>(List.of(appEntity));
        AppDto expectedDto = new AppDto(1L, "name", "content", null, null);

        given(appRepository.findAll(pageable)).willReturn(entityPage);
        given(appMapper.map(appEntity)).willReturn(expectedDto);

//        when
        Page<AppDto> result = appService.getAll(null, pageable);

//        then
        assertThat(result.getContent()).hasSize(1);

        verify(appRepository).findAll(pageable);
    }
}
