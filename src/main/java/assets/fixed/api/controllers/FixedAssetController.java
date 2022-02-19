package assets.fixed.api.controllers;

import java.util.Optional;

import java.util.List;
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

import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.services.interfaces.IFixedAssetService;
import assets.fixed.api.services.interfaces.IValidatorService;
import assets.fixed.api.utilities.Converter;

@CrossOrigin
@RestController
@RequestMapping(value = "asset", produces = MediaType.APPLICATION_JSON_VALUE)
public class FixedAssetController {

    @Autowired
    private IFixedAssetService fixedAssetService;

    @Autowired
    private IValidatorService validatorService;

    @GetMapping(value = "find/type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTypeId(@PathParam("typeId") String typeId){
        HttpStatus httpStatus;
        List<FixedAsset> fixedAsset = fixedAssetService.findByTypeId(typeId);
        try{
            if(!fixedAsset.isEmpty()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fixedAsset, httpStatus);
    }

    @GetMapping(value = "find/buyDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByBuyDate(@PathParam("buyDate") String buyDate){
        HttpStatus httpStatus;
        List<FixedAsset> fixedAsset = fixedAssetService.findByBuyDate(Converter.dateToMillis(buyDate));
        try{
            if(!fixedAsset.isEmpty()){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fixedAsset, httpStatus);
    }

    @GetMapping(value = "find/serial", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Object> findBySerial(@PathParam("serial") String serial){
        HttpStatus httpStatus;
        FixedAsset fixedAsset = fixedAssetService.findBySerial(serial);
        try{
            if( fixedAsset == null){
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fixedAsset, httpStatus);
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody FixedAsset fixedAsset){
        HttpStatus httpStatus;
        String message;
        String timeInMillis = Converter.dateToMillis(fixedAsset.getBuyDate());
        fixedAsset.setBuyDate(timeInMillis);
        try {
            if(validatorService.validator(fixedAsset)){
                fixedAssetService.save(fixedAsset);
                message = "Registro guardado exitosamente";
                httpStatus = HttpStatus.OK;
            } else {
                message = "Error al guardar el registro";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            message = "Error en el servicio";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Object>(message, httpStatus);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody FixedAsset fixedAsset, @PathParam("serial") String serial) {
        HttpStatus httpStatus;
        String message;
        String timeInMillis = Converter.dateToMillis(fixedAsset.getBuyDate());
        fixedAsset.setBuyDate(timeInMillis);
        try {
            if(this.fixedAssetService.update(fixedAsset, serial)){
                message = "Registro actualizado exitosamente";
                httpStatus = HttpStatus.OK;
            } else {
                message = "Error al actualizar el registro";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            message = "Error en el servicio";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Object>(message, httpStatus);
    }
}