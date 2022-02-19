package assets.fixed.api.controllers;

import java.util.List;
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

import assets.fixed.api.models.Person;
import assets.fixed.api.services.interfaces.IPersonService;

@CrossOrigin
@RestController
@RequestMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    
    @Autowired
    private IPersonService personService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Person> person = personService.findById(id);
        try{
            if(!person.isPresent()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(person, httpStatus);
    }

    @GetMapping(value = "find/area")
    public ResponseEntity<Object> findByArea(@PathParam("areaId") String areaId){
        HttpStatus httpStatus;
        List<Person> person = personService.findByAreaId(areaId);
        try{
            if(!person.isEmpty()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(person, httpStatus);
    }
}
