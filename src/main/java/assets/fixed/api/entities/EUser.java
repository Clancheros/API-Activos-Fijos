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
@Table(name="reg_user")
@Entity
public class EUser implements Serializable{
    @Id
    @Column
    private String username;

    @Column
    private String password;
    
    @Column
    private String rol;
}
