package licenta.InsureMeRo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")//(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // standard constructors, dependency injection
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    //update
}
