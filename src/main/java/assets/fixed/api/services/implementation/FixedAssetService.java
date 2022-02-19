package assets.fixed.api.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import assets.fixed.api.Utilities.Helpers;
import assets.fixed.api.entities.EFixedAsset;
import assets.fixed.api.models.FixedAsset;
import assets.fixed.api.repositories.FixedAssetRepository;
import assets.fixed.api.services.interfaces.IFixedAssetService;

@Component
public class FixedAssetService implements IFixedAssetService {


    @Autowired
    private FixedAssetRepository assetRepository;

    @Override
    public List<FixedAsset> findByTypeId(String typeId) {
        try {
            List<EFixedAsset> eFixedAssets = assetRepository.findByTypeId(typeId);
            List<FixedAsset> fixedAssets = new ArrayList<>();
            for (EFixedAsset eAsset : eFixedAssets) {
            fixedAssets.add(eFixedAssetToFixedAsset(eAsset));
        }
        return fixedAssets;
        } catch (Exception e) {
            System.out.println("No hay bienes fijos con ese tipo");
            return null;
        }
    }

    @Override
    public List<FixedAsset> findByBuyDate(String buyDate) {
        try {
            List<EFixedAsset> eFixedAssets = assetRepository.findByBuyDate(buyDate);
            List<FixedAsset> fixedAssets = new ArrayList<>();
            for (EFixedAsset eAsset : eFixedAssets) {
            fixedAssets.add(eFixedAssetToFixedAsset(eAsset));
        }
        return fixedAssets;
        } catch (Exception e) {
            System.out.println("No hay bienes fijos comprados en esa fecha");
            return null;
        }
    }

    @Override
    public FixedAsset findBySerial(String serial) {
        try {
            EFixedAsset eFixedAsset = assetRepository.findBySerial(serial).get();
            return eFixedAssetToFixedAsset(eFixedAsset);
        } catch (Exception e) {
            System.out.println("No hay bien fijo con ese serial");
            return null;
        }
    }

    @Override
    public void save(@RequestBody FixedAsset fixedAsset) {
        EFixedAsset eFixedAsset = Helpers.modelMapper().map(fixedAsset, EFixedAsset.class);
        try {
            assetRepository.save(eFixedAsset);
        }
        catch(Exception e){
            System.out.println("Error al guardar");
        }
    }

    @Override
    public void update(FixedAsset fixedAsset, String serial) {
        try {
            Optional<EFixedAsset> eFixedAsset = this.assetRepository.findBySerial(serial);
            EFixedAsset asset = eFixedAsset.get();
            asset.setBuyDate(fixedAsset.getBuyDate());
            asset.setBuyValue(fixedAsset.getBuyValue());
            asset.setDescription(fixedAsset.getDescription());
            asset.setHeight(fixedAsset.getHeight());
            asset.setInternalNumber(fixedAsset.getInternalNumber());
            asset.setLength(fixedAsset.getLength());
            asset.setName(fixedAsset.getName());
            asset.setPersonId(fixedAsset.getPersonId());
            asset.setSerial(fixedAsset.getSerial());
            asset.setTypeId(fixedAsset.getTypeId());
            asset.setUnitMeasureDistanceId(fixedAsset.getUnitMeasureDistanceId());
            asset.setUnitMeasureWeightId(fixedAsset.getUnitMeasureWeightId());
            asset.setWeight(fixedAsset.getWeight());
            asset.setWidth(fixedAsset.getWidth());
            this.assetRepository.save(asset);
        } catch (Exception e) {
            System.out.println("Error al actualizar");
        }
    }
    
    private FixedAsset eFixedAssetToFixedAsset(final EFixedAsset eFixedAsset){
        return Helpers.modelMapper().map(eFixedAsset, FixedAsset.class);
    }
}
