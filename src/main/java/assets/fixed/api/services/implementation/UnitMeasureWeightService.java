package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.repositories.UnitMeasureWeightRepository;
import assets.fixed.api.services.interfaces.IUnitMeasureWeightService;
import assets.fixed.api.utilities.Helpers;
import assets.fixed.api.entities.EUnitMeasureWeight;
import assets.fixed.api.models.UnitMeasureWeight;

@Component
public class UnitMeasureWeightService implements IUnitMeasureWeightService {

    @Autowired
    private UnitMeasureWeightRepository measureWeightRepository;
    @Override
    public Optional<UnitMeasureWeight> findById(String id) {
        try {
            Optional<EUnitMeasureWeight> eUnitWeight = this.measureWeightRepository.findById(id);
            return eUnitWeight.map(this::eUnitMeasureWeightToUnitMeasureWeight);
        } catch (Exception e) {
            System.out.println("No existe unidad de medida de peso con ese id");
            return null;
        }
        
    }
    
    private UnitMeasureWeight eUnitMeasureWeightToUnitMeasureWeight(final EUnitMeasureWeight eUnitMeasureWeight){
        return Helpers.modelMapper().map(eUnitMeasureWeight, UnitMeasureWeight.class);
    }

}
