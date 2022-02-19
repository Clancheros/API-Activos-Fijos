package assets.fixed.api.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.Utilities.Converter;
import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.services.interfaces.IFixedAssetService;

@CrossOrigin
@RestController
@RequestMapping(value = "asset", produces = MediaType.APPLICATION_JSON_VALUE)
public class FixedAssetController {

    @Autowired
    private IFixedAssetService fixedAssetService;

    @GetMapping(value = "find/type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTypeId(@PathParam("typeId") String typeId){
        try{
            return ResponseEntity.ok(fixedAssetService.findByTypeId(typeId));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "find/buyDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByBuyDate(@PathParam("buyDate") String buyDate){
        try{
            return ResponseEntity.ok(fixedAssetService.findByBuyDate(Converter.dateToMillis(buyDate)));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "find/serial", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Object> findBySerial(@PathParam("serial") String serial){
        try{
            return ResponseEntity.ok(fixedAssetService.findBySerial(serial));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody FixedAsset fixedAsset){
        String timeInMillis = Converter.dateToMillis(fixedAsset.getBuyDate());
        fixedAsset.setBuyDate(timeInMillis);
        try {
            fixedAssetService.save(fixedAsset);
            return ResponseEntity.ok(fixedAsset);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody FixedAsset fixedAsset, @PathParam("serial") String serial) {
        try {
            this.fixedAssetService.update(fixedAsset, serial);
            return ResponseEntity.ok(fixedAsset);
        } catch (Exception e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        
    }
}