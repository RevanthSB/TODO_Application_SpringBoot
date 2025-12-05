package Revanth.SB.project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Data
public class TodoDatabase {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @NotBlank
    String title;
    @NotNull
    @NotBlank
    String description;
    boolean iscompleted;
    @Pattern(regexp = "\\d+")
    String mobileno;
    @Email
    String email;

}
