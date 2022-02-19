package assets.fixed.api.utilities;

import org.modelmapper.ModelMapper;

public class Helpers {
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
