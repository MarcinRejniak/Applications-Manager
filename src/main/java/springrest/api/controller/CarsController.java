package springrest.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springrest.api.dto.CarDto;
import springrest.infrastructure.service.CarService;

import java.util.List;

@RestController
@RequestMapping(path = "/cars")
public class CarsController {
    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(path = "/add")
    public CarDto addCar(@RequestBody CarDto carDto) {

        return this.carService.save(carDto);
    }

    @PutMapping(path = "/update/{id}")
    public CarDto updateCar(@PathVariable Integer id, @RequestBody CarDto carDto) {

        return this.carService.update(id, carDto);
    }

    @GetMapping(path = "/one/{id}")
    public ResponseEntity<CarDto> getOne(@PathVariable Integer id) {
        CarDto carDto = this.carService.findOne(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("info", "Getting one car by Id: " + id);

        return ResponseEntity.ok().headers(httpHeaders).body(carDto);
    }

    @GetMapping(path = "/all")
    public List<CarDto> getAll() {

        return this.carService.findAll();
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.delete(id);
    }
}
