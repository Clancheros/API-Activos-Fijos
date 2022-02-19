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
@Table(name="Area")
@Entity
public class EArea implements Serializable{

    @Id
    @Column
    private String id;

    @Column
    private String name;
    
    @Column(name = "city_id")
    private String cityId;
}
