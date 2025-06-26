package ee.helmes.test.dto;

import java.util.List;
import java.util.Set;

public record UserDataView(
        String name,
        boolean agreedToTerms,
        Set<Long> selectedSectorIds,
        List<SectorView> allSectors
) {
}
