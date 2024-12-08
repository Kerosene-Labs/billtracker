package com.kerosenelabs.billtracker.model.supabase;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchema {
    private UUID id;
    private Instant confirmedAt;
}
