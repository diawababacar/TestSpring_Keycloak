package com.abdev.sbtest.apiHrManager.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdentityAvailability {
    private Boolean available;
}
