package restApi.hibernate.repos;


import restApi.hibernate.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CityRepo extends CrudRepository<City,Long> {
    Optional<City> findById(Long id);
    City findByCityName(String name);
}
