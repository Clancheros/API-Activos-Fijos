package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.entities.EArea;
import assets.fixed.api.models.Area;
import assets.fixed.api.repositories.AreaRepository;
import assets.fixed.api.services.interfaces.IAreaService;
import assets.fixed.api.utilities.Helpers;

@Component
public class AreaService implements IAreaService{

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public Optional<Area> findById(String id) {
        try {
            Optional<EArea> eArea = this.areaRepository.findById(id);
            return eArea.map(this::eAreaToArea);
        } catch (Exception e) {
            System.out.println("No hay area para el id buscado");
            return null;
        }
    }

    @Override
    public Area findByCity(String city) {
        try {
            EArea eArea = this.areaRepository.findByCityId(city);
            return eAreaToArea(eArea);
        } catch (Exception e) {
            System.out.println("No hay area asignada a esa ciudad");
            return null;
        }
        
    }

    private Area eAreaToArea(final EArea eArea){
        return Helpers.modelMapper().map(eArea, Area.class);
    }
    
}
