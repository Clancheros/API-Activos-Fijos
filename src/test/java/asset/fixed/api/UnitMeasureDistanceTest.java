package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EUnitMeasureDistance;
import assets.fixed.api.repositories.UnitMeasureDistanceRepository;
import assets.fixed.api.services.implementation.UnitMeasureDistanceService;

public class UnitMeasureDistanceTest {
    @Mock
    private UnitMeasureDistanceRepository typeRepository;

    @InjectMocks
    private UnitMeasureDistanceService typeService;

    private EUnitMeasureDistance eUnitMeasureDistance;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eUnitMeasureDistance = new EUnitMeasureDistance();
    }

    @Test
    void findByIdSuccess() {
        Optional<EUnitMeasureDistance> optionalUnitMeasureDistance = Optional.of(eUnitMeasureDistance);
        when(typeRepository.findById(eUnitMeasureDistance.getId())).thenReturn(optionalUnitMeasureDistance);
        assertNotNull(typeService.findById(eUnitMeasureDistance.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<EUnitMeasureDistance> optionalUnitMeasureDistance = Optional.empty();
        when(typeRepository.findById(eUnitMeasureDistance.getId())).thenReturn(optionalUnitMeasureDistance);
        assertNotNull(typeService.findById(eUnitMeasureDistance.getId()));
    }
}
