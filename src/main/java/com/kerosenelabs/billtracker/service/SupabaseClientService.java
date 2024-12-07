package com.kerosenelabs.billtracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SupabaseClientService {
    private String serviceRoleKey;

    public SupabaseClientService(@Value("${supabase.service_role.key}") String serviceRoleKey) {
        this.serviceRoleKey = serviceRoleKey;
    }
}
