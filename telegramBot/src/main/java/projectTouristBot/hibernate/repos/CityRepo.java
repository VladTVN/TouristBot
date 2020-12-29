package projectTouristBot.hibernate.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projectTouristBot.hibernate.models.City;
@Repository
public interface CityRepo extends CrudRepository<City,Long> {
    City findByCityName(String cityName);
}
