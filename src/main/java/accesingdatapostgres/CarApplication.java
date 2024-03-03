package accesingdatapostgres;

import accesingdatapostgres.dao.dto.CarDto;
import accesingdatapostgres.dao.entity.CarEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<CarEntity, CarDto>() {
            @Override
            protected void configure() {
                map().setBrand(source.getBrand());
                map().setModel(source.getModel());
                map().setYear(source.getYear());
            }
        });
        return modelMapper;
    }
}
