package assets.fixed.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="unit_measure_distance")
@Entity
public class EUnitMeasureDistance implements Serializable{
    @Id
    @Column
    private String id;

    @Column
    private String unit;

    @Column
    private String abbreviation;
}
