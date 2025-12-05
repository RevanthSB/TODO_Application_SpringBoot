package Revanth.SB.project.Respository;


import Revanth.SB.project.Model.UserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<UserDatabase,Long> {

    Optional<UserDatabase> findByEmail(String email);

}