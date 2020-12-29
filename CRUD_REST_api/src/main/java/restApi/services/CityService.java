package restApi.services;

import restApi.hibernate.models.City;

import java.util.Optional;

public interface CityService {

    Optional<City> findById(Long id);

    void saveCity(City city);

    City findByCityName(String cityName);

    void deleteCity(Long id);


}
