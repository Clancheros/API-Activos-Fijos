package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.UnitMeasureWeight;

@Service("unitMeasureWeightService")
public interface IUnitMeasureWeightService {
    public Optional<UnitMeasureWeight> findById(String id);
}
