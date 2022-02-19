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

import assets.fixed.api.models.Type;
import assets.fixed.api.services.interfaces.ITypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "type", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Type> type = typeService.findById(id);
        try{
            if(!type.isPresent()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(type, httpStatus);
    }
}
