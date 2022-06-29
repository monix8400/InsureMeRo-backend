package licenta.InsureMeRo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String health() {
        return "Hello & Welcome to CloudKatha !!!";
    }
}
