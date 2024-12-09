package com.kerosenelabs.billtracker.model.supabase;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupabaseCookies {
    private Cookie accessToken;
    private Cookie refreshToken;
}
