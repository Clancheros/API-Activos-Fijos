package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.UnitMeasureDistance;

@Service("unitMeasureDistanceService")
public interface IUnitMeasureDistanceService {
    public Optional<UnitMeasureDistance> findById(String id);
}
