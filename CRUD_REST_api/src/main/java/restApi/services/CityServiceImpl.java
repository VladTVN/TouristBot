package restApi.services;

import restApi.hibernate.models.City;
import restApi.hibernate.repos.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {
    private final CityRepo cityRepo;

    @Autowired
    public CityServiceImpl(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public Optional<City> findById(Long id){
        return  cityRepo.findById(id);
    }


    public void saveCity(City city){
        cityRepo.save(city);
    }

    public City findByCityName(String cityName){
        return cityRepo.findByCityName(cityName);
    }


    public void deleteCity(Long id){
        cityRepo.deleteById(id);
    }

}
