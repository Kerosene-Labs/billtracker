package com.kerosenelabs.billtracker.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetIntroductorySettingsRequest {
    @NotEmpty
    @Length(min = 1, max = 30)
    private String firstName;

    @NotEmpty
    @Length(min = 1, max = 30)
    private String lastName;

    @NotEmpty
    @Length(min = 1, max = 30)
    private LocalDate birthday;
}
