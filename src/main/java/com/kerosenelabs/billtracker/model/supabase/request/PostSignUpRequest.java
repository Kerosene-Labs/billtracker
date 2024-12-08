package com.kerosenelabs.billtracker.model.supabase.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSignUpRequest {
    private String email;
    private String password;
}
