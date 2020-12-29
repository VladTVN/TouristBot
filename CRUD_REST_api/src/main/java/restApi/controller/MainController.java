package restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restApi.hibernate.models.City;
import restApi.services.CityService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {
    private CityService cityServiceImpl;

    @Autowired
    public MainController(CityService cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody City city){
        HttpHeaders headers = new HttpHeaders();
        if(city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        City foundCity = cityServiceImpl.findByCityName(city.getCityName());
        if (foundCity != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cityServiceImpl.saveCity(city);
        return new ResponseEntity<>(city, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<City> update(@RequestBody City newCity){
        HttpHeaders headers = new HttpHeaders();
        if (newCity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        City city = cityServiceImpl.findByCityName(newCity.getCityName());
        if (city == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        city.setCityName(newCity.getCityName());
        city.setInformation(newCity.getInformation());
        cityServiceImpl.saveCity(city);
        return new ResponseEntity<>(city, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<City> delete(@PathVariable Long id){
        Optional<City> city = cityServiceImpl.findById(id);
        if (city == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityServiceImpl.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
