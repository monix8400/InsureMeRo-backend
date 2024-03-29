package licenta.InsureMeRo.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import licenta.InsureMeRo.models.User;
import licenta.InsureMeRo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully registered!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getRefreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByEmail(username).get();
                String accessToken = JWT.create().withSubject(user.getEmail()).withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).withIssuer(request.getRequestURL().toString()).withClaim("roles", new ArrayList<>().add(user.getRole()))//user.getRole().stream().map(GrantedAuthority::getAuthority).toList()
                        .sign(algorithm);
//                response.setHeader("accessToken", accessToken);
//                response.setHeader("refreshToken", refreshToken);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("Error ", e.getMessage());
                response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }

    @GetMapping(value = "/user")
    public Optional<User> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        userService.getUserByEmail(email);
        return userService.getUserByEmail(email);
    }

    @PostMapping("/updateFirstname")
    public void updateUserFirstname(@RequestBody String firstname, Authentication auth) {
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();
        userService.updateFirstname(firstname, user.getId());
    }

    @PostMapping("/updateLastname")
    public void updateUserLastname(@RequestBody String lastname, Authentication auth) {
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();
        userService.updateLastname(lastname, user.getId());
    }

    @PostMapping("/updateEmail")
    public void updateUserEmail(@RequestBody String email, Authentication auth) {
        User user = userService.getUserByEmail((String) auth.getPrincipal()).get();
        userService.updateEmail(email, user.getId());
    }
}
