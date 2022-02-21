package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.Role;

@Service("roleService")
public interface IRoleService {
    public Optional<Role> findById(String id);
}
