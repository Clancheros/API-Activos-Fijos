package assets.fixed.api.controllers;

import java.util.List;
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

import assets.fixed.api.models.Area;
import assets.fixed.api.models.Person;
import assets.fixed.api.services.interfaces.IAreaService;
import assets.fixed.api.services.interfaces.IPersonService;

@CrossOrigin
@RestController
@RequestMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    public final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @Autowired
    private IAreaService areaService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Person> person = personService.findById(id);
        try{
            if(!person.isPresent()){
                LOGGER.debug("No existe la persona con id '{}'", id);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                LOGGER.debug("Data: '{}'", person);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(person, httpStatus);
    }

    @GetMapping(value = "find/area")
    public ResponseEntity<Object> findByArea(@PathParam("areaId") String areaId){
        HttpStatus httpStatus;
        List<Person> persons = personService.findByAreaId(areaId);
        try{
            Optional<Area> area = areaService.findById(areaId);
            if(!persons.isEmpty()){
                LOGGER.debug("No existe la persona asignada al area '{}'", area.get().getName());
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                for(Person person : persons){
                    person.setAreaId(area.get().getName());
                }
                LOGGER.debug("Data: {}",persons);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(persons, httpStatus);
    }
}
