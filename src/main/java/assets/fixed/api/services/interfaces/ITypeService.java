package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.Type;

@Service("typeService")
public interface ITypeService {
    public Optional<Type> findById(String id);
}
