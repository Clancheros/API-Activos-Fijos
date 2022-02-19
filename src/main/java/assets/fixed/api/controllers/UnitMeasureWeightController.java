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

import assets.fixed.api.models.UnitMeasureWeight;
import assets.fixed.api.services.interfaces.IUnitMeasureWeightService;

@CrossOrigin
@RestController
@RequestMapping(value = "unitMeasureWeight", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitMeasureWeightController {
    @Autowired
    private IUnitMeasureWeightService WeightService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        HttpStatus httpStatus;
        Optional<UnitMeasureWeight> unitWeight = WeightService.findById(id);
        try {
            if(!unitWeight.isPresent()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(unitWeight, httpStatus);
    }
}
