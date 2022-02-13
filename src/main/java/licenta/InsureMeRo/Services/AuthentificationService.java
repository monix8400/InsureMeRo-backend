package licenta.InsureMeRo.Services;

import licenta.InsureMeRo.Models.User;
import licenta.InsureMeRo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    private final UserRepository userRepository;

    @Autowired

    public AuthentificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ResponseEntity<?> login(String email, String password){

        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> logout(String email){
        User user = (User) userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }

        if (!user.getLoggedIn()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        user.setLoggedIn(false);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<?> forgotPassword(String email, String newPassword, String confirmPassword){
        User user = (User) userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }

        if (!newPassword.equals(confirmPassword)) {
            return new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

        user.setPassword(newPassword);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
