package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.Area;

@Service("areaService")
public interface IAreaService {
    public Optional<Area> findById(String id);
    public Area findByCity(String city);
}
