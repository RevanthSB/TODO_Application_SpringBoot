package Revanth.SB.project.Service;



import Revanth.SB.project.Model.TodoDatabase;
import Revanth.SB.project.Respository.TodoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRespository todoRespository;

    public TodoDatabase createTodo(TodoDatabase todo){
        return todoRespository.save(todo);
    }

    public TodoDatabase gettodobyId(Long id){
        return todoRespository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Page<TodoDatabase> getalltodopages(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return todoRespository.findAll(pageable);
    }

    public List<TodoDatabase> getalltodo(){
        return todoRespository.findAll();
    }

    public TodoDatabase updatetodo(TodoDatabase todo){
        return todoRespository.save(todo);
    }

    public void DeletetodobyId(Long id){
         todoRespository.deleteById(id);
    }

    public void Deletealltodo(){
        todoRespository.deleteAll();
    }

}
