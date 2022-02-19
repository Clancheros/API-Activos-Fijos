package assets.fixed.api.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.services.interfaces.IAreaService;

@CrossOrigin
@RestController
@RequestMapping("area")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping(value = "find")
    public ResponseEntity<Object> findById(@PathParam("id") String id){
        try{
            return ResponseEntity.ok(areaService.findById(id));
        }
        catch(Exception e){
            System.out.println(e.getStackTrace());
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "find/city")
    public ResponseEntity<Object> findByCity(@PathParam("cityId") String cityId){
        try{
            return ResponseEntity.ok(areaService.findByCity(cityId));
        }
        catch(Exception e){
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }
    
}
