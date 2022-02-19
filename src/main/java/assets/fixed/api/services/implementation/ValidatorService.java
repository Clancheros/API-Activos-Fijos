package assets.fixed.api.services.implementation;

import org.springframework.stereotype.Component;

import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.services.interfaces.IValidatorService;
import assets.fixed.api.utilities.exceptions.UnprocessableEntity;

@Component
public class ValidatorService implements IValidatorService{

    @Override
    public boolean validator(FixedAsset fixedAsset) throws UnprocessableEntity {
        if(fixedAsset.getBuyDate() == null || fixedAsset.getBuyDate().isEmpty()){
            message("La fecha de compra es necesaria");
        }
        if(fixedAsset.getDescription() == null || fixedAsset.getDescription().isEmpty()){
            message("La descripción es necesaria");
        }
        if(fixedAsset.getName() == null || fixedAsset.getName().isEmpty()){
            message("El nombre es necesario");
        }
        if(fixedAsset.getSerial() == null || fixedAsset.getSerial().isEmpty()){
            message("El serial es necesario");
        }
        if(fixedAsset.getTypeId() == null || fixedAsset.getTypeId().isEmpty()){
            message("El id del tipo es necesario");
        }
        if(fixedAsset.getUnitMeasureDistanceId() == null || fixedAsset.getUnitMeasureDistanceId().isEmpty()){
            message("El id de la unidad de distancia es necesario");
        }
        if(fixedAsset.getUnitMeasureWeightId() == null || fixedAsset.getUnitMeasureWeightId().isEmpty()){
            message("El id de l unidad de peso es necesario");
        }
        if(fixedAsset.getBuyValue() == null || fixedAsset.getBuyValue().isNaN()){
            message("El valor de compra es necesario");
        }
        if(fixedAsset.getHeight() == null || fixedAsset.getHeight().isNaN()){
            message("El alto es necesario");
        }
        if(fixedAsset.getInternalNumber() == null || fixedAsset.getInternalNumber().isNaN()){
            message("El número interno es necesario");
        }
        if(fixedAsset.getLength() == null || fixedAsset.getLength().isNaN()){
            message("El largo es necesario");
        }
        if(fixedAsset.getWeight() == null || fixedAsset.getWeight().isNaN()){
            message("El peso es necesario");
        }
        if(fixedAsset.getWidth() == null || fixedAsset.getWidth().isNaN()){
            message("El ancho es necesario");
        }
        return true;
    }

    private void message(String message) throws UnprocessableEntity {
        throw new UnprocessableEntity(message);
    }
    
}
