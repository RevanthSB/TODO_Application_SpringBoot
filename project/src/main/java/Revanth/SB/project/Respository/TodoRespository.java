package Revanth.SB.project.Respository;


import Revanth.SB.project.Model.TodoDatabase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRespository extends JpaRepository<TodoDatabase,Long> {

}
