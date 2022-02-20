package assets.fixed.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="FixedAsset")
@Entity
public class EFixedAsset implements Serializable {
    
    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "type_id")
    private String typeId;

    @Id
    @Column
    private String serial;

    @Column(name="internal_number")
    private String internalNumber;

    @Column
    private Double weight;

    @Column
    private Double height;

    @Column
    private Double width;

    @Column
    private Double length;

    @Column( name="buy_value")
    private Double buyValue;

    @Column( name="buy_date")
    private String buyDate;

    @Column(name = "unit_measure_weight_id")
    private String unitMeasureWeightId;

    @Column(name = "unit_measure_distance_id")
    private String unitMeasureDistanceId;

    @Column(name = "area_id")
    private String areaId;

    @Column(name = "person_id")
    private String personId;
}
