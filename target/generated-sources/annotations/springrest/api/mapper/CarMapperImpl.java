package springrest.api.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import springrest.api.dto.CarDto;
import springrest.infrastructure.database.entity.CarEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T18:56:40+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public CarDto map(CarEntity carEntity) {
        if ( carEntity == null ) {
            return null;
        }

        String brand = null;
        String model = null;
        int year = 0;

        brand = carEntity.getBrand();
        model = carEntity.getModel();
        year = carEntity.getYear();

        CarDto carDto = new CarDto( brand, model, year );

        return carDto;
    }

    @Override
    public CarEntity map(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        CarEntity carEntity = new CarEntity();

        carEntity.setBrand( carDto.getBrand() );
        carEntity.setModel( carDto.getModel() );
        carEntity.setYear( carDto.getYear() );

        return carEntity;
    }
}
