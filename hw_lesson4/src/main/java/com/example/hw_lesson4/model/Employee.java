package com.example.hw_lesson4.model;




import jakarta.persistence.*;
import lombok.Data;




@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Employee(int i, String john, String doe, String mail) {

    }

    public Employee() {

    }
}