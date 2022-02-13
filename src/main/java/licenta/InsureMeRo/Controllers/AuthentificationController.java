package licenta.InsureMeRo.Controllers;

import licenta.InsureMeRo.Services.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
//@RequestMapping("/user")
public class AuthentificationController {

    private final AuthentificationService authentificationService;

    @Autowired
    public AuthentificationController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        return authentificationService.login(email, password);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam("email") String email){
        return authentificationService.logout(email);
    }


    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            @RequestParam("confirm") String confirmPassword){
        return authentificationService.forgotPassword(email, password, confirmPassword);
    }
}
