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

import assets.fixed.api.models.UnitMeasureWeight;
import assets.fixed.api.services.interfaces.IUnitMeasureWeightService;

@CrossOrigin
@RestController
@RequestMapping(value = "unitMeasureWeight", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitMeasureWeightController {
    public final static Logger LOGGER = LoggerFactory.getLogger(UnitMeasureWeightController.class);
    
    @Autowired
    private IUnitMeasureWeightService WeightService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<UnitMeasureWeight> unitWeight = WeightService.findById(id);
        try {
            if(!unitWeight.isPresent()){
                LOGGER.debug("No existe la unidad de medida con id {}", id);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                LOGGER.debug("Data: {}", unitWeight);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(unitWeight, httpStatus);
    }
}
