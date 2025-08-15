package com.gyabisito.employmentsystem.entities;

import com.gyabisito.employmentsystem.enums.CompensationType;
import com.gyabisito.employmentsystem.utils.YearMonthAttributeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
@Data
@Table(name = "compensation")
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private CompensationType type;

    @NotNull
    private BigDecimal amount;

    private String description;

    @NotNull
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth period;




    // Getters, setters, equals y hashCode
}
