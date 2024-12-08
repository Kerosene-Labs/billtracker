package com.kerosenelabs.billtracker.model.supabase.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSignUpResponse {
    private String accessToken;
    private String refreshToken;
}
