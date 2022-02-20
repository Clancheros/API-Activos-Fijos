package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EPerson;
import assets.fixed.api.repositories.PersonRepository;
import assets.fixed.api.services.implementation.PersonService;

public class PersonTest {
    
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private EPerson ePerson;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        ePerson = new EPerson();
        ePerson.setId("0");
        ePerson.setName("Juan");
        ePerson.setLastName("Stark");
        ePerson.setAreaId("0");
    }

    @Test
    void findByIdSuccess() {
        Optional<EPerson> optionalPerson = Optional.of(ePerson);
        when(personRepository.findById(ePerson.getId())).thenReturn(optionalPerson);
        assertNotNull(personService.findById(ePerson.getId()));
    }

    @Test
    void findByIdFailure() {
        Optional<EPerson> optionalPerson = Optional.empty();
        when(personRepository.findById(ePerson.getId())).thenReturn(optionalPerson);
        assertNotNull(personService.findById(ePerson.getId()));
    }

    @Test
    void findByAreaIdSuccess() {
        List<EPerson> persons = new ArrayList<>();
        persons.add(ePerson);
        when(personRepository.findByAreaId(ePerson.getId())).thenReturn(persons);
        assertNotNull(personService.findByAreaId(ePerson.getId()));
    }

    @Test
    void findByAreaIdFailure() {
        List<EPerson> persons = new ArrayList<>();
        persons.add(ePerson);
        when(personRepository.findByAreaId(ePerson.getId())).thenReturn(persons);
        assertNotNull(personService.findByAreaId(ePerson.getId()));
    }
}
