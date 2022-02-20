package assets.fixed.api.utilities;

import org.modelmapper.ModelMapper;

public class Helpers {
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static boolean validateUnitMeasureDistanceId(String unit){
        if( unit != null){
            return true;
        }
        return false;
    }

    public static boolean validateUnitMeasureWeightId(String unit){
        if( unit != null){
            return true;
        }
        return false;
    }

    public static boolean validateTypeId(String typeId){
        if(typeId != null){
            return true;
        }
        return false;
    }

    public static boolean validatePersonId(String personId){
        if(personId != null){
            return true;
        }
        return false;
    }

    public static boolean validateAreaId(String areaId){
        if(areaId != null){
            return true;
        }
        return false;
    }
}
