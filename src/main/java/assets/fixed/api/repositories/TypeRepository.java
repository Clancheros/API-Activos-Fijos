package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.EType;

@Repository("typeRepository")
public interface TypeRepository extends JpaRepository<EType, String> {

}
