package pl.rejniak.claim.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.rejniak.claim.domain.application.dto.AppDto;
import pl.rejniak.claim.domain.application.service.AppService;

import java.util.List;

@RestController
@RequestMapping(path = "/apps")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping(path = "/create")
    public AppDto createApp(@RequestBody AppDto appDto){
        return this.appService.create(appDto);
    }

    @PutMapping(path = "/verify/{id}")
    public AppDto verify(@PathVariable Long id){
        return this.appService.verify(id);
    }

    @PutMapping(path="/accept/{id}")
    public AppDto accept(@PathVariable Long id){
        return this.appService.accept(id);
    }

    @PutMapping(path = "/publish/{id}")
    public AppDto publish(@PathVariable Long id){
        return this.appService.publish(id);
    }

    @PutMapping(path = "/delete/{id}/{reason}")
    public AppDto deleteApp(@PathVariable Long id, @PathVariable String reason){
        return this.appService.delete(id, reason);
    }

    @PutMapping(path = "/reject/{id}/{reason}")
    public AppDto reject(@PathVariable Long id, @PathVariable String reason){
        return this.appService.reject(id, reason);
    }

    @PutMapping(path="/update/{id}/{content}")
    public AppDto changeContent(@PathVariable Long id, @PathVariable String content){
        return this.appService.changeContent(id,content);
    }

    @GetMapping(path = "/one/{id}")
    public AppDto findById(@PathVariable Long id){
        return this.appService.getOne(id);
    }

    @GetMapping(path = "/all")
    public List<AppDto> findAll(){
        return this.appService.getAll();
    }
}
