package com.kerosenelabs.billtracker.model.supabase.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSignUpRequest {
    private String email;
    private String password;
}
