package pl.switix.springjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @GetMapping(path = "/test-all")
    public ResponseEntity<String> test1() {
        return new ResponseEntity<>("test-all", HttpStatus.OK);
    }

    @GetMapping(path = "/test-user")
    public ResponseEntity<String> test2() {
        return new ResponseEntity<>("test-user", HttpStatus.OK);
    }

    @GetMapping(path = "/test-admin")
    public ResponseEntity<String> test3() {
        return new ResponseEntity<>("test-admin", HttpStatus.OK);
    }

}
