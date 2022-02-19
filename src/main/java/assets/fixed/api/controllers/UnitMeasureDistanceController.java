package assets.fixed.api.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.services.interfaces.IUnitMeasureDistanceService;

@CrossOrigin
@RestController
@RequestMapping(value = "unitMeasureDistance", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitMeasureDistanceController {
    @Autowired
    private IUnitMeasureDistanceService distanceService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        try {
            return ResponseEntity.ok(distanceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }
}
