package org.example.hw5spring.entity;


import java.time.LocalDateTime;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "notes")
public class Note  implements Comparable<Note>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    @NotBlank(message="Заметка не может быть пустой")
    @Size(max=255, message="Превышен лимит в 255 символов")
    private String text;

    @Column(name = "last_change")
    private LocalDateTime lastChange;

    @Column(name = "important")
    private Boolean important;

    @Override
    public int compareTo(Note otherNote) {
        if (getLastChange() == null || otherNote.getLastChange() == null){
            return -1;
        }
        return otherNote.getLastChange().compareTo(getLastChange());
    }
}