package com.kerosenelabs.billtracker.model.supabase.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTokenRequest {
    private String email;
    private String password;
}
