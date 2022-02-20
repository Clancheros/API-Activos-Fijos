package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EUnitMeasureWeight;
import assets.fixed.api.repositories.UnitMeasureWeightRepository;
import assets.fixed.api.services.implementation.UnitMeasureWeightService;

public class UnitMeasureWeightTest {
    @Mock
    private UnitMeasureWeightRepository typeRepository;

    @InjectMocks
    private UnitMeasureWeightService typeService;

    private EUnitMeasureWeight eUnitMeasureWeight;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eUnitMeasureWeight = new EUnitMeasureWeight();
    }

    @Test
    void findByIdSuccess() {
        Optional<EUnitMeasureWeight> optionalUnitMeasureWeight = Optional.of(eUnitMeasureWeight);
        when(typeRepository.findById(eUnitMeasureWeight.getId())).thenReturn(optionalUnitMeasureWeight);
        assertNotNull(typeService.findById(eUnitMeasureWeight.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<EUnitMeasureWeight> optionalUnitMeasureWeight = Optional.empty();
        when(typeRepository.findById(eUnitMeasureWeight.getId())).thenReturn(optionalUnitMeasureWeight);
        assertNotNull(typeService.findById(eUnitMeasureWeight.getId()));
    }
}

