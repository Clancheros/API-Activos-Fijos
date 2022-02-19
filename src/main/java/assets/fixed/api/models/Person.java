package assets.fixed.api.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable{
    private String id;
    private String name;
    private String lastName;
    private String areaId;
}
