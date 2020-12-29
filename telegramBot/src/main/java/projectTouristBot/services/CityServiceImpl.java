package projectTouristBot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectTouristBot.hibernate.models.City;
import projectTouristBot.hibernate.repos.CityRepo;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;

    public City findByCityName(String cityName){
        City city = cityRepo.findByCityName(cityName);
        if (city == null){
            city = new City();
            city.setCityName("Извините");
            city.setInformation("Я ничего не знаю об этом городе =(\nОбещаю исправиться =)");
        }
        return city;
    }
}
