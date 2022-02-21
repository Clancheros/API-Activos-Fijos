package assets.fixed.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import assets.fixed.api.entities.EUser;

public interface UserRepository extends JpaRepository<EUser, String>{
    
}
