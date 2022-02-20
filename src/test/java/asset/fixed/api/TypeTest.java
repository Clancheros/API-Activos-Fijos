package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EType;
import assets.fixed.api.repositories.TypeRepository;
import assets.fixed.api.services.implementation.TypeService;

class TypeTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    private EType eType;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eType = new EType();
        eType.setId("0");
        eType.setName("Computador");
    }

    @Test
    void findByIdSuccess() {
        Optional<EType> optionalType = Optional.of(eType);
        when(typeRepository.findById(eType.getId())).thenReturn(optionalType);
        assertNotNull(typeService.findById(eType.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<EType> optionalType = Optional.empty();
        when(typeRepository.findById(eType.getId())).thenReturn(optionalType);
        assertNotNull(typeService.findById(eType.getId()));
    }
}
