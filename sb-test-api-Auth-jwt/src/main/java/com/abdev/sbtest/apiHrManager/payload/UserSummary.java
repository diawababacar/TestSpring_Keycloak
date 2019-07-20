package com.abdev.sbtest.apiHrManager.payload;

import com.abdev.sbtest.apiHrManager.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummary {
    private Long id;
    private String nom;
    private String prenom;
    private String username;
    private Set<Role> roles;

}
