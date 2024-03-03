package accesingdatapostgres.api;

import accesingdatapostgres.dao.entity.CarEntity;
import accesingdatapostgres.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/cars")
public class CarApi {
    private final CarService carService;

    @Autowired
    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(path = "/add")
    public CarEntity addCar(@RequestBody CarEntity carEntity) {

        return this.carService.save(carEntity);
    }

    @PutMapping(path = "/update")
    public CarEntity updateCar(@RequestBody CarEntity carEntity) {
        return this.carService.save(carEntity);
    }

    @GetMapping(path = "/one/{id}")
    public Optional<CarEntity> getOne(@PathVariable Integer id) {
        return this.carService.findOne(id);
    }

    @GetMapping(path = "/all")
    public Iterable<CarEntity> getAll() {

        return this.carService.findAll();
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteCar(@PathVariable Integer id) {

        carService.delete(id);
    }
}
