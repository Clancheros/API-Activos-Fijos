package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.repositories.CityRepository;
import assets.fixed.api.services.interfaces.ICityService;
import assets.fixed.api.Utilities.Helpers;
import assets.fixed.api.entities.ECity;
import assets.fixed.api.models.City;

@Component
public class CityService implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> findById(String id) {
        try{
            Optional<ECity> eCity = this.cityRepository.findById(id);
            return eCity.map(this::eCityToCity);
        } catch(Exception e) {
            System.out.println("No hay ciudad con ese id");
            return null;
        }
        
    }

    private City eCityToCity(final ECity eCity){
        return Helpers.modelMapper().map(eCity, City.class);
    }
    
}
