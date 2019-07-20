package com.abdev.sbtest.apiHrManager.controller;

import com.abdev.sbtest.apiHrManager.exception.AppException;
import com.abdev.sbtest.apiHrManager.models.Role;
import com.abdev.sbtest.apiHrManager.models.RoleName;
import com.abdev.sbtest.apiHrManager.models.User;
import com.abdev.sbtest.apiHrManager.payload.*;
import com.abdev.sbtest.apiHrManager.repository.RoleRepository;
import com.abdev.sbtest.apiHrManager.repository.UserRepository;
import com.abdev.sbtest.apiHrManager.security.CurrentUser;
import com.abdev.sbtest.apiHrManager.security.JwtTokenProvider;
import com.abdev.sbtest.apiHrManager.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    @ApiOperation(value = "Authentification Utilisateur")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PatchMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Nom d'utilisateur déjà pris!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "L'adresse email est déjà utilisé!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Création d'un HR Manageur
        User user = new User();
        user.setLast_name(signUpRequest.getLast_name());
        user.setFirst_name(signUpRequest.getFirst_name());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_HR_MANAGER)
                .orElseThrow(() -> new AppException("Ce role d'utilisateur n'est pas défini"));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "HR Manageur créer avec Succés"));
    }


    @GetMapping("/user/me")
    @ApiOperation(value = "Information sur l'utilisateur Connecté")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary();
        userSummary.setId(currentUser.getId());
        userSummary.setNom(currentUser.getLast_name());
        userSummary.setPrenom(currentUser.getFirst_name());
        userSummary.setUsername(currentUser.getUsername());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    @ApiOperation(value = "Verification du nom d'utilisateur pour voir s'il existe déjà ou pas")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    @ApiOperation(value = "Verification du l'email d'utilisateur pour voir s'il existe déjà ou pas")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }



}
