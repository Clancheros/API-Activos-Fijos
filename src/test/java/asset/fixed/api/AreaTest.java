package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EArea;
import assets.fixed.api.repositories.AreaRepository;
import assets.fixed.api.services.implementation.AreaService;

public class AreaTest {
    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaService areaService;

    private EArea eArea;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eArea = new EArea();
        eArea.setId("0");
        eArea.setName("Computador");
    }

    @Test
    void findByIdSuccess() {
        Optional<EArea> optionalArea = Optional.of(eArea);
        when(areaRepository.findById(eArea.getId())).thenReturn(optionalArea);
        assertNotNull(areaService.findById(eArea.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<EArea> optionalArea = Optional.empty();
        when(areaRepository.findById(eArea.getId())).thenReturn(optionalArea);
        assertNotNull(areaService.findById(eArea.getId()));
    }

    @Test
    void findByCitySuccess() {
        when(areaRepository.findByCityId(eArea.getId())).thenReturn(eArea);
        assertNotNull(areaService.findByCity(eArea.getId()));
    }

    @Test
    void findByCityFailure() {
        when(areaRepository.findByCityId(eArea.getId())).thenReturn(eArea);
        assertNotNull(areaService.findByCity(eArea.getId()));
    }
}
