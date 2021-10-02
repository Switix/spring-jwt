package pl.switix.springjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.switix.springjwt.entity.AppUser;
import pl.switix.springjwt.model.AuthenticationRequest;
import pl.switix.springjwt.model.AuthenticationResponse;
import pl.switix.springjwt.service.AppUserService;
import pl.switix.springjwt.util.JwtUtil;

@RestController
public class AuthController {

    UserDetailsService userDetailsService;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }





    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials", e);
        }
        AppUser appUser = (AppUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtUtil.generateToken(appUser);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

}
