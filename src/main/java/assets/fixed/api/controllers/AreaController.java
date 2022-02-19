package assets.fixed.api.controllers;

import java.util.Optional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.models.Area;
import assets.fixed.api.services.interfaces.IAreaService;

@CrossOrigin
@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Area> area = areaService.findById(id);
        try{
            if(!area.isPresent()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(area, httpStatus);
    }

    @GetMapping(value = "find/city")
    public ResponseEntity<Object> findByCity(@PathParam("cityId") String cityId){
        HttpStatus httpStatus;
        Area area = areaService.findByCity(cityId);
        try{
            if(area == null){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        }
        catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(area, httpStatus);
    }
    
}
