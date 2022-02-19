package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.ECity;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<ECity, String> {
    
}
