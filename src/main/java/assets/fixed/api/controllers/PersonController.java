package assets.fixed.api.controllers;

import java.util.List;

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
        try {
            return ResponseEntity.ok(personService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "find/area")
    public List<Person> findByArea(@PathParam("areaId") String areaId){
        return personService.findByAreaId(areaId);
    }
}
