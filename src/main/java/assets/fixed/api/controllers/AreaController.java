package assets.fixed.api.controllers;

import java.util.Optional;
import javax.websocket.server.PathParam;

import org.hibernate.type.descriptor.java.CalendarTimeTypeDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.models.Area;
import assets.fixed.api.models.City;
import assets.fixed.api.services.interfaces.IAreaService;
import assets.fixed.api.services.interfaces.ICityService;

@CrossOrigin
@RestController
@RequestMapping("area")
public class AreaController {
    public final static Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private IAreaService areaService;

    @Autowired
    private ICityService cityService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Area> area = areaService.findById(id);
        try{
            if(!area.isPresent()){
                LOGGER.debug("No existen area con id '{}'", id);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                LOGGER.debug("Data: '{}'", area);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(area, httpStatus);
    }

    @GetMapping(value = "find/city")
    public ResponseEntity<Object> findByCity(@PathParam("cityId") String cityId){
        HttpStatus httpStatus;
        Area area = areaService.findByCity(cityId);
        try{
            Optional<City> city = cityService.findById(cityId);
            if(area == null){
                LOGGER.debug("No existen area asignada a la ciudad '{}'", city.get().getName());
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                area.setCityId(city.get().getName());
                LOGGER.debug("Data: '{}'", area);
                httpStatus = HttpStatus.OK;
            }
        }
        catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(area, httpStatus);
    }
    
}
