package assets.fixed.api.services.interfaces;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.utilities.exceptions.UnprocessableEntity;

@Service("validatorService")
public interface IValidatorService {
    public void validator (FixedAsset fixedAssetFixedAsset) throws UnprocessableEntity;
}
