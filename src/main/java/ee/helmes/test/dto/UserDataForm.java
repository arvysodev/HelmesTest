package ee.helmes.test.dto;

import java.util.Set;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserDataForm(
        @NotBlank(message = "Name is required")
        String name,

        @AssertTrue(message = "You must agree to the terms")
        boolean agreedToTerms,

        @NotEmpty(message = "Please select at least one sector")
        Set<Long> selectedSectorIds
) {
}
