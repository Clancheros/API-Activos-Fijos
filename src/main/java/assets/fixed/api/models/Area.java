package assets.fixed.api.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Area implements Serializable{
    private String id;
    private String name;
    private String cityId;
}
