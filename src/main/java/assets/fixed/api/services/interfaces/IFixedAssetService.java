package assets.fixed.api.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.*;

@Service("fixedAssetService")
public interface IFixedAssetService {
    public List<FixedAsset> findByTypeId(String typeId);
    public List<FixedAsset> findByBuyDate(String buyDate);
    public FixedAsset findBySerial(String serial);
    public boolean save(FixedAsset fixedAsset);
    public boolean update(FixedAsset fixedAsset, String serial);
}
