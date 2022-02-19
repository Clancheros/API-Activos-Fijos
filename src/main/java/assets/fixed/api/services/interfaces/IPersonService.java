package assets.fixed.api.services.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.Person;

@Service("personService")
public interface IPersonService {
    public Optional<Person> findById(String id);
    public List<Person> findByAreaId(String areaId);
}
