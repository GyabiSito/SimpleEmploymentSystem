package com.gyabisito.employmentsystem.entities;

import com.gyabisito.employmentsystem.annotations.Adult;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Data
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    @Past(message = "Birth date must be in the past")
    @Adult
    private LocalDate birthDate;

    @NotBlank
    private String position;

}

