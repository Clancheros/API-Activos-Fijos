package assets.fixed.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
@Entity
public class EPerson implements Serializable{
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "area_id")
    private String areaId;
}
