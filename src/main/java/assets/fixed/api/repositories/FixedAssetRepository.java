package assets.fixed.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assets.fixed.api.entities.EFixedAsset;

@Repository("fixedAssetRepository")
public interface FixedAssetRepository extends JpaRepository<EFixedAsset, String>{

    public List<EFixedAsset> findByTypeId(String typeId);

    public List<EFixedAsset> findByBuyDate(String buyDate);

    public Optional<EFixedAsset> findBySerial(String serial);
    
}
