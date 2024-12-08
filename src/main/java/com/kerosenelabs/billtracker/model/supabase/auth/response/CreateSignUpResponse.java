package com.kerosenelabs.billtracker.model.supabase.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSignUpResponse {
    private String accessToken;
    private String refreshToken;
}
