package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.repositories.UnitMeasureDistanceRepository;
import assets.fixed.api.services.interfaces.IUnitMeasureDistanceService;
import assets.fixed.api.Utilities.Helpers;
import assets.fixed.api.entities.EUnitMeasureDistance;
import assets.fixed.api.models.UnitMeasureDistance;

@Component
public class UnitMeasureDistanceService implements IUnitMeasureDistanceService {

    @Autowired
    private UnitMeasureDistanceRepository distanceRepository;

    public Optional<UnitMeasureDistance> findById(String id) {
        try {
            Optional<EUnitMeasureDistance> eUnitDistance = this.distanceRepository.findById(id);
            return eUnitDistance.map(this::eUnitMeasureDistanceToUnitMeasureDistance);
        } catch (Exception e) {
            System.out.println("No existe unidad de medida de distancia con ese id");
            return null;
        }
        
    }
    
    private UnitMeasureDistance eUnitMeasureDistanceToUnitMeasureDistance(final EUnitMeasureDistance eUnitMeasureDistance){
        return Helpers.modelMapper().map(eUnitMeasureDistance, UnitMeasureDistance.class);
    }
    
}
