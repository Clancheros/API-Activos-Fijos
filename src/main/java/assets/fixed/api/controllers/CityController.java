package assets.fixed.api.controllers;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public final static Logger LOGGER = LoggerFactory.getLogger(CityController.class);
    
    @Autowired
    private ICityService cityService;

    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<City> city = cityService.findById(id);
        try{
            if(!city.isPresent()){
                LOGGER.debug("No existe la ciudad con id '{}'", id);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                LOGGER.debug("Data: '{}'", city);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(city, httpStatus);
    }
}
