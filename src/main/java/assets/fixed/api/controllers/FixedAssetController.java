package assets.fixed.api.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assets.fixed.api.models.Area;
import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.models.Person;
import assets.fixed.api.models.Type;
import assets.fixed.api.models.UnitMeasureDistance;
import assets.fixed.api.models.UnitMeasureWeight;
import assets.fixed.api.services.interfaces.IAreaService;
import assets.fixed.api.services.interfaces.IFixedAssetService;
import assets.fixed.api.services.interfaces.IPersonService;
import assets.fixed.api.services.interfaces.ITypeService;
import assets.fixed.api.services.interfaces.IUnitMeasureDistanceService;
import assets.fixed.api.services.interfaces.IUnitMeasureWeightService;
import assets.fixed.api.services.interfaces.IValidatorService;
import assets.fixed.api.utilities.Converter;
import assets.fixed.api.utilities.Helpers;
import assets.fixed.api.utilities.exceptions.UnprocessableEntity;
import lombok.experimental.Helper;

@CrossOrigin
@RestController
@RequestMapping(value = "asset", produces = MediaType.APPLICATION_JSON_VALUE)
public class FixedAssetController {

    public final static Logger LOGGER = LoggerFactory.getLogger(FixedAssetController.class);

    @Autowired
    private IFixedAssetService fixedAssetService;

    @Autowired
    private ITypeService iTypeService;

    @Autowired
    private IValidatorService validatorService;

    @Autowired
    private IUnitMeasureDistanceService unitMeasureDistanceService;

    @Autowired
    private IUnitMeasureWeightService unitMeasureWeightService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private IPersonService personService;

    @GetMapping(value = "find/type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByTypeId(@PathParam("typeId") String typeId){
        HttpStatus httpStatus;
        List<FixedAsset> fixedAsset = fixedAssetService.findByTypeId(typeId);
        try{
            Optional<Type> type = iTypeService.findById(typeId);
            if(fixedAsset.isEmpty()){
                LOGGER.debug("No existen bienes fijos del tipo '{}'", type.get().getName());
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                for(FixedAsset asset : fixedAsset){
                    if(Helpers.validateUnitMeasureDistanceId(asset.getUnitMeasureDistanceId())){
                        Optional<UnitMeasureDistance> unitMeasureDistance = unitMeasureDistanceService.findById(asset.getUnitMeasureDistanceId());
                        asset.setUnitMeasureDistanceId(unitMeasureDistance.get().getAbbreviation());
                        if(Helpers.validateUnitMeasureWeightId(asset.getUnitMeasureWeightId())){
                            Optional<UnitMeasureWeight> unitMeasureWeight = unitMeasureWeightService.findById(asset.getUnitMeasureWeightId());
                            asset.setUnitMeasureWeightId(unitMeasureWeight.get().getAbbreviation());
                        } else {
                            return new ResponseEntity<>("Falta la unidad de peso", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Falta la unidad de distancia", HttpStatus.BAD_REQUEST);
                    }
                    Optional<Person> person = personService.findById(asset.getPersonId());
                    asset.setPersonId(person.get().getName());
                    Optional<Area> area = areaService.findById(asset.getAreaId());
                    asset.setAreaId(area.get().getName());
                    asset.setTypeId(type.get().getName());
                }
                LOGGER.debug("Data: {}",fixedAsset);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(fixedAsset, httpStatus);
    }

    @GetMapping(value = "find/buyDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByBuyDate(@PathParam("buyDate") String buyDate){
        HttpStatus httpStatus;
        List<FixedAsset> fixedAsset = fixedAssetService.findByBuyDate(Converter.dateToMillis(buyDate));
        try{
            if(fixedAsset.isEmpty()){
                LOGGER.debug("No existen bienes fijos comprados en '{}'", buyDate);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                for(FixedAsset asset : fixedAsset){
                    if(Helpers.validateUnitMeasureDistanceId(asset.getUnitMeasureDistanceId())){
                        Optional<UnitMeasureDistance> unitMeasureDistance = unitMeasureDistanceService.findById(asset.getUnitMeasureDistanceId());
                        asset.setUnitMeasureDistanceId(unitMeasureDistance.get().getAbbreviation());
                        if(Helpers.validateUnitMeasureWeightId(asset.getUnitMeasureWeightId())){
                            Optional<UnitMeasureWeight> unitMeasureWeight = unitMeasureWeightService.findById(asset.getUnitMeasureWeightId());
                            asset.setUnitMeasureWeightId(unitMeasureWeight.get().getAbbreviation());
                            if(Helpers.validateTypeId(asset.getTypeId())){
                                Optional<Type> type = iTypeService.findById(asset.getTypeId());
                                asset.setTypeId(type.get().getName());
                            } else {
                                return new ResponseEntity<>("Falta el tipo de bien", HttpStatus.BAD_REQUEST);
                            }
                        } else {
                            return new ResponseEntity<>("Falta la unidad de peso", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Falta la unidad de distancia", HttpStatus.BAD_REQUEST);
                    }
                    Optional<Area> area = areaService.findById(asset.getAreaId());
                    asset.setAreaId(area.get().getName());
                    Optional<Person> person = personService.findById(asset.getPersonId());
                    asset.setPersonId(person.get().getName());
                }
                LOGGER.debug("Data: {}",fixedAsset);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
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
                LOGGER.debug("No existe bien fijo con el serial numero: '{}'", serial);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                if(Helpers.validateUnitMeasureDistanceId(fixedAsset.getUnitMeasureDistanceId())){
                    Optional<UnitMeasureDistance> unitMeasureDistance = unitMeasureDistanceService.findById(fixedAsset.getUnitMeasureDistanceId());
                    fixedAsset.setUnitMeasureDistanceId(unitMeasureDistance.get().getAbbreviation());
                    if(Helpers.validateUnitMeasureWeightId(fixedAsset.getUnitMeasureWeightId())){
                        Optional<UnitMeasureWeight> unitMeasureWeight = unitMeasureWeightService.findById(fixedAsset.getUnitMeasureWeightId());
                        fixedAsset.setUnitMeasureWeightId(unitMeasureWeight.get().getAbbreviation());
                        if(Helpers.validateTypeId(fixedAsset.getTypeId())){
                            Optional<Type> type = iTypeService.findById(fixedAsset.getTypeId());
                            fixedAsset.setTypeId(type.get().getName());
                        } else {
                            return new ResponseEntity<>("Falta el tipo de bien", HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("Falta la unidad de peso", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("Falta la unidad de distancia", HttpStatus.BAD_REQUEST);
                }
                if(!fixedAsset.getAreaId().isEmpty()){
                    Optional<Area> area = areaService.findById(fixedAsset.getAreaId());
                    fixedAsset.setAreaId(area.get().getName());
                }
                if(!fixedAsset.getPersonId().isEmpty()){
                    Optional<Person> person = personService.findById(fixedAsset.getPersonId());
                    fixedAsset.setPersonId(person.get().getName());
                }
                LOGGER.debug("Data: {}", serial);
                httpStatus = HttpStatus.OK;
            }
        } catch(Exception error){
            LOGGER.debug("Error: {}", error.getMessage());
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
            validatorService.validator(fixedAsset);
            fixedAssetService.save(fixedAsset);
            message = "Registro almacenado exitosamente";
            LOGGER.debug("Registro almacenado exitosamente {}", fixedAsset);
            httpStatus = HttpStatus.OK;
        } catch(UnprocessableEntity error){
            message = error.getMessage();
            LOGGER.debug("Error: {}", error.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        } 
        catch (Exception error) {
            LOGGER.debug("Error: {}", error.getMessage());
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
                LOGGER.debug("Registro actualizado exitosamente {}", fixedAsset);
                httpStatus = HttpStatus.OK;
            } else {
                message = "Error al actualizar el registro";
                LOGGER.debug("Registro no almacenado {}", fixedAsset);
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception error) {
            LOGGER.debug("Error: {}", error.getMessage());
            message = "Error en el servicio";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Object>(message, httpStatus);
    }
}