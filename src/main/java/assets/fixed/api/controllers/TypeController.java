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

import assets.fixed.api.models.Type;
import assets.fixed.api.services.interfaces.ITypeService;

@CrossOrigin
@RestController
@RequestMapping(value = "type", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {
    public final static Logger LOGGER = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private ITypeService typeService;

    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<Type> type = typeService.findById(id);
        try{
            if(!type.isPresent()){
                LOGGER.debug("No existe el tipo con id '{}'", id);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                LOGGER.debug("Data: {}", type);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(type, httpStatus);
    }
}
