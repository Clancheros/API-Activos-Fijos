package asset.fixed.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import assets.fixed.api.entities.EFixedAsset;
import assets.fixed.api.repositories.FixedAssetRepository;
import assets.fixed.api.services.implementation.FixedAssetService;

public class FixedAssetTest {
    @Mock
    private FixedAssetRepository fixedAssetRepository;

    @InjectMocks
    private FixedAssetService fixedAssetService;

    private EFixedAsset eFixedAsset;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        eFixedAsset = new EFixedAsset();
        eFixedAsset.setAreaId("0");
        eFixedAsset.setBuyDate("12/12/2019");
        eFixedAsset.setBuyValue(123.0);
        eFixedAsset.setDescription("description");
        eFixedAsset.setHeight(13.0);
        eFixedAsset.setInternalNumber("7530");
        eFixedAsset.setLength(15.2);
        eFixedAsset.setName("name");
        eFixedAsset.setPersonId("0");
        eFixedAsset.setSerial("12340");
        eFixedAsset.setTypeId("0");
        eFixedAsset.setUnitMeasureDistanceId("0");
        eFixedAsset.setUnitMeasureWeightId("0");
        eFixedAsset.setWeight(12.3);
        eFixedAsset.setWidth(15.6);
    }

    @Test
    void findBySerialSuccess() {
        Optional<EFixedAsset> optionalFixedAsset = Optional.of(eFixedAsset);
        when(fixedAssetRepository.findBySerial(eFixedAsset.getSerial())).thenReturn(optionalFixedAsset);
        assertNotNull(fixedAssetService.findBySerial(eFixedAsset.getSerial()));
    }

    @Test
    void findByBuyDateSuccess() {
        List<EFixedAsset> fixedAssets = new ArrayList<>();
        fixedAssets.add(eFixedAsset);
        when(fixedAssetRepository.findByBuyDate(eFixedAsset.getBuyDate())).thenReturn(fixedAssets);
        assertNotNull(fixedAssetService.findByBuyDate(eFixedAsset.getBuyDate()));
    }

    @Test
    void findByBuyDateFailure() {
        List<EFixedAsset> fixedAssets = new ArrayList<>();
        when(fixedAssetRepository.findByBuyDate(eFixedAsset.getBuyDate())).thenReturn(fixedAssets);
        assertNotNull(fixedAssetService.findByBuyDate(eFixedAsset.getBuyDate()));
    }

    @Test
    void findByIdSuccess() {
        List<EFixedAsset> fixedAssets = new ArrayList<>();
        fixedAssets.add(eFixedAsset);
        when(fixedAssetRepository.findByTypeId(eFixedAsset.getSerial())).thenReturn(fixedAssets);
        assertNotNull(fixedAssetService.findByTypeId(eFixedAsset.getSerial()));
    }

    @Test
    void findByIdFailure() {
        List<EFixedAsset> fixedAssets = new ArrayList<>();
        when(fixedAssetRepository.findByTypeId(eFixedAsset.getSerial())).thenReturn(fixedAssets);
        assertNotNull(fixedAssetService.findByTypeId(eFixedAsset.getSerial()));
    }
}
