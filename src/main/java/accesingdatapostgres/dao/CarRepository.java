package accesingdatapostgres.dao;

import accesingdatapostgres.dao.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Integer> {

}