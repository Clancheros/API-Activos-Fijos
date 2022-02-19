package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import assets.fixed.api.entities.EArea;

@Repository("areaRepository")
public interface AreaRepository extends JpaRepository<EArea, String>{
    
    @Transactional(readOnly = true)
    public EArea findByCityId(String cityId);
}
