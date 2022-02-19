package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.repositories.TypeRepository;
import assets.fixed.api.services.interfaces.ITypeService;
import assets.fixed.api.utilities.Helpers;
import assets.fixed.api.entities.EType;
import assets.fixed.api.models.Type;

@Component
public class TypeService implements ITypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Optional<Type> findById(String id) {
        try {
            Optional<EType> eType = this.typeRepository.findById(id);
            return eType.map(this::eTypeToType);
        } catch (Exception e) {
            System.out.println("No hay un tipo con ese id");
            return null;
        }
    }

    private Type eTypeToType(final EType eType){
        return Helpers.modelMapper().map(eType, Type.class);
    }
    
}
