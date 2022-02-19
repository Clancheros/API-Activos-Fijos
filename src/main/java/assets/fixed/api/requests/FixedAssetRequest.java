package assets.fixed.api.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FixedAssetRequest implements Serializable {
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("typeId")
    private String typeId;

    @JsonProperty("serial")
    private String serial;

    @JsonProperty("internalNumber")
    private Double internalNumber;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("height")
    private Double height;

    @JsonProperty("width")
    private Double width;

    @JsonProperty("length")
    private Double length;

    @JsonProperty("buyValue")
    private Double buyValue;

    @JsonProperty("buyDate")
    private String buyDate;

    @JsonProperty("unitMeasureWeightId")
    private String unitMeasureWeightId;

    @JsonProperty("unitMeasureDistenceId")
    private String unitMeasureDistanceId;

    @JsonProperty("areaId")
    private String areaId;

    @JsonProperty("personId")
    private String personId;
}
