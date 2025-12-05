package Revanth.SB.project.Controller;


import Revanth.SB.project.Auth.JWTAuth;
import Revanth.SB.project.Model.UserDatabase;
import Revanth.SB.project.Respository.UserRespository;
import Revanth.SB.project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todo/User")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userservice;
    private final UserRespository userrespository;
    private final PasswordEncoder passwordEncoder;
    private final JWTAuth jwtauth;

    @PostMapping("/Register")
    public ResponseEntity<String> Registeruser(@RequestBody Map<String,String> User){
        String email = User.get("email");
        String password = passwordEncoder.encode(User.get("password"));
        if(userrespository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email is already existed", HttpStatus.CONFLICT);
        }

       userservice.createuser(UserDatabase.builder().email(email).password(password).build());

       return new ResponseEntity<>("Successfully created",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Loginuser(@RequestBody Map<String,String> User){
        String email = User.get("email");
        String password = User.get("password");

        var useroptional = userrespository.findByEmail(email);
        if(!useroptional.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        UserDatabase user = useroptional.get();


        if(!passwordEncoder.matches(password,user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        }

        String token =  jwtauth.generatetoken(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", token));
    }


































}
