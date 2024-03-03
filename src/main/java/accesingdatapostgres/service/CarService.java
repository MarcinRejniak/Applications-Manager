package accesingdatapostgres.service;

import accesingdatapostgres.dao.entity.CarEntity;
import accesingdatapostgres.dao.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarEntity save(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    public Optional<CarEntity> findOne(Integer id) {
        return carRepository.findById(id);
    }

    public Iterable<CarEntity> findAll() {
        return carRepository.findAll();
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new CarEntity("Ford", "Mustang", 1969));
        save(new CarEntity("BMW", "X3", 2023));
    }
}
