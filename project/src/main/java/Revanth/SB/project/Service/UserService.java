package Revanth.SB.project.Service;


import Revanth.SB.project.Model.UserDatabase;
import Revanth.SB.project.Respository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRespository userRespository;

    public UserDatabase createuser(UserDatabase user){
       return userRespository.save(user);
    }

}
