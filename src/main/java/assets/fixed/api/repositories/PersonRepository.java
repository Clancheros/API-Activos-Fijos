package assets.fixed.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.EPerson;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<EPerson, String>{

    public List<EPerson> findByAreaId(String areaId);
    
}
