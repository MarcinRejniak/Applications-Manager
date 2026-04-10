package pl.rejniak.claim.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.rejniak.claim.domain.application.dto.AppDto;
import pl.rejniak.claim.domain.application.dto.ContentChange;
import pl.rejniak.claim.domain.application.dto.RejectReason;
import pl.rejniak.claim.domain.application.service.AppService;

import java.util.List;

@RestController
@RequestMapping(path = "/apps")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping
    public List<AppDto> findAll(){
        return this.appService.getAll();
    }

    @GetMapping(path = "/{id}")
    public AppDto findById(@PathVariable Long id){
        return this.appService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppDto createApp(@RequestBody AppDto appDto){
        return this.appService.create(appDto);
    }

    @PatchMapping(path = "/{id}/verify")
    public AppDto verify(@PathVariable Long id){
        return this.appService.verify(id);
    }

    @PatchMapping(path="/{id}/accept")
    public AppDto accept(@PathVariable Long id){
        return this.appService.accept(id);
    }

    @PatchMapping(path = "/{id}/publish")
    public AppDto publish(@PathVariable Long id){
        return this.appService.publish(id);
    }

    @PatchMapping(path = "/{id}/reject")
    public AppDto reject(@PathVariable Long id, @RequestBody RejectReason rejectReason){
        return this.appService.reject(id, rejectReason.reason());
    }

    @PatchMapping(path = "/{id}/delete")
    public AppDto deleteApp(@PathVariable Long id, @RequestBody RejectReason rejectReason){
        return this.appService.delete(id, rejectReason.reason());
    }

    @PatchMapping(path="/{id}/content")
    public AppDto changeContent(@PathVariable Long id, @RequestBody ContentChange contentChange){
        return this.appService.changeContent(id, contentChange.newContent());
    }
}
