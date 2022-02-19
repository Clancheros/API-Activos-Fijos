package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.City;

@Service("cityService")
public interface ICityService {
    public Optional<City> findById(String id);
}
