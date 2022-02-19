package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.EUnitMeasureWeight;

@Repository("unitMeasureWeightRepository")
public interface UnitMeasureWeightRepository extends JpaRepository<EUnitMeasureWeight, String> {
    
}
