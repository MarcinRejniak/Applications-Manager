package springrest.infrastructure.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import springrest.api.dto.CarDto;
import springrest.api.mapper.CarMapper;
import springrest.infrastructure.database.entity.CarEntity;
import springrest.infrastructure.database.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService{
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto save(CarDto carDto) {
        carRepository.save((carMapper.map(carDto)));
        return carDto;
    }

    public CarDto update(Integer id, CarDto carDto) {
        CarEntity carEntity = carMapper.map(carDto);
        carEntity.setId(id);
        carRepository.save(carEntity);
        return carDto;
    }

    public CarDto findOne(Integer id) {
        return carRepository.findById(id).map(carMapper::map).orElseThrow(()->new EntityNotFoundException(
                "CarEntity not found, carId: [%s]".formatted(id)
        ));
    }

    public List<CarDto> findAll() {
        Iterable<CarEntity> iterable = carRepository.findAll();
        List<CarEntity> carEntityList = new ArrayList<>();
        iterable.forEach(carEntityList::add);
        return carEntityList.stream().map(carMapper::map).toList();
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        carRepository.save(new CarEntity("Ford", "Mustang", 1969));
        carRepository.save(new CarEntity("BMW", "X3", 2023));
    }
}
