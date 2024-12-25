package com.kerosenelabs.billtracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntroductorySettings {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
}
