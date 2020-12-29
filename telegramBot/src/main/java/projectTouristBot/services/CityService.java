package projectTouristBot.services;

import projectTouristBot.hibernate.models.City;

public interface CityService {
    City findByCityName(String cityName);
}
