package ee.helmes.test.dto;

import java.util.Set;

public record SectorView(
        Long id,
        String name,
        int depth
) {
}
