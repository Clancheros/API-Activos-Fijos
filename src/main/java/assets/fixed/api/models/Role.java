package assets.fixed.api.models;

import assets.fixed.api.utilities.enums.RoleNameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
    private String id;
    private RoleNameEnum roleName;
}
