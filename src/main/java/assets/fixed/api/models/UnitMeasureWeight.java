package assets.fixed.api.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitMeasureWeight implements Serializable {
    private String id;
    private String unit;
    private String abbreviation;
}
