package springrest.infrastructure.database.repository;

import springrest.infrastructure.database.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Integer> {

}