package asset.fixed.api;

import assets.fixed.api.entities.ECity;
import assets.fixed.api.repositories.CityRepository;
import assets.fixed.api.services.implementation.CityService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CityTest {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService CityService;

    private ECity eCity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eCity = new ECity();
        eCity.setId("0");
        eCity.setName("Computador");
    }

    @Test
    void findByIdSuccess() {
        Optional<ECity> optionalCity = Optional.of(eCity);
        when(cityRepository.findById(eCity.getId())).thenReturn(optionalCity);
        assertNotNull(CityService.findById(eCity.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<ECity> optionalCity = Optional.empty();
        when(cityRepository.findById(eCity.getId())).thenReturn(optionalCity);
        assertNotNull(CityService.findById(eCity.getId()));
    }
}
