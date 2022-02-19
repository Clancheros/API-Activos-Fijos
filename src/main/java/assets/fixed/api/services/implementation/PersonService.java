package assets.fixed.api.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.entities.EPerson;
import assets.fixed.api.models.Person;
import assets.fixed.api.repositories.PersonRepository;
import assets.fixed.api.services.interfaces.IPersonService;
import assets.fixed.api.utilities.Helpers;

@Component
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findByAreaId(String areaId) {
        try {
            List<Person> persons = new ArrayList<>();
            List<EPerson> ePersons = this.personRepository.findByAreaId(areaId);
            for (EPerson ePerson : ePersons) {
                persons.add(ePersonToPerson(ePerson));
            }
            return persons;
        } catch (Exception e) {
            System.out.println("No existen personas asignadas a esta área");
            return null;
        }
    }

    @Override
    public Optional<Person> findById(String id) {
        try {
            Optional<EPerson> ePerson = this.personRepository.findById(id);
            return ePerson.map(this::ePersonToPerson);
        } catch (Exception e) {
            System.out.println("No hay personas con ese id");
            return null;
        }
    }

    private Person ePersonToPerson(final EPerson ePerson){
        return Helpers.modelMapper().map(ePerson, Person.class);
    }
    
}
