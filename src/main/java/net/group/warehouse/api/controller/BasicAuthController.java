package net.group.warehouse.api.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;


@RestController
public class BasicAuthController {

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @PostMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }
}
