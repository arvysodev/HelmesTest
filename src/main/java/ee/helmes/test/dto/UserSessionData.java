package ee.helmes.test.dto;

import java.util.Set;

public record UserSessionData(
        String name,
        boolean agreedToTerms,
        Set<Long> selectedSectorIds
) {
}
