package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.EUnitMeasureDistance;

@Repository("unitMeasureDistanceRepository")
public interface UnitMeasureDistanceRepository extends JpaRepository<EUnitMeasureDistance, String>{
    
}
