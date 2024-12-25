package com.kerosenelabs.billtracker.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Length(min = 1, max = 30)
    private String firstName;

    @NotEmpty
    @NotNull
    @Length(min = 1, max = 30)
    private String lastName;

    @NotEmpty
    @NotNull
    @Length(min = 1, max = 30)
    private LocalDate birthday;
}
