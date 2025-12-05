package Revanth.SB.project.Controller;


import Revanth.SB.project.Model.TodoDatabase;
import Revanth.SB.project.Service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/createtodo")
    public ResponseEntity<TodoDatabase> createtodo(@RequestBody TodoDatabase todo){
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }


    @ApiResponse(responseCode = "200" , description = "Todo retreieved successfully")
    @ApiResponse(responseCode = "400" , description = "Todo not found")
    @GetMapping("/gettodo")
    public ResponseEntity<TodoDatabase> gettodobyId(@RequestParam Long id){
        try {
            return new ResponseEntity<>(todoService.gettodobyId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Execption incurred haha  ",e);
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Page")
    public ResponseEntity<Page<TodoDatabase>> gettodobypage(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(todoService.getalltodopages(page, size),HttpStatus.OK);
    }

    @GetMapping("/getalltodo")
    public ResponseEntity<List<TodoDatabase>> getalltodo(){
        try {
            return new ResponseEntity<>(todoService.getalltodo(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatetodo")
    public ResponseEntity<TodoDatabase> updatetodo(@RequestBody TodoDatabase todo) {
        try {
            return new ResponseEntity<>(todoService.updatetodo(todo), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/Deletetodo")
    public void deletetodo (@RequestParam Long id){
        todoService.DeletetodobyId(id);
    }

    @DeleteMapping("/Deletealltodo")
    public void deletealltooo(){
        todoService.Deletealltodo();
    }

}
