package assets.fixed.api.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FixedAsset implements Serializable {
    private String name;
    private String description;
    private String typeId;
    private String serial;
    private Double internalNumber;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private Double buyValue;
    private String buyDate;
    private String unitMeasureWeightId;
    private String unitMeasureDistanceId;
    private String areaId;
    private String personId;
}
