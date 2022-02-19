package assets.fixed.api.controllers;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.models.City;
import assets.fixed.api.services.interfaces.ICityService;

@CrossOrigin
@RestController
@RequestMapping("city")
public class CityController {
    
    @Autowired
    private ICityService cityService;

    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<City> area = cityService.findById(id);
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
}
