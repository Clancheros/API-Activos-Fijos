package assets.fixed.api.services.interfaces;

import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.utilities.exceptions.UnprocessableEntity;

public interface IValidatorService {
    public void validator (FixedAsset fixedAssetFixedAsset) throws UnprocessableEntity;
}
