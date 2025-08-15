package com.gyabisito.employmentsystem.services;

import com.gyabisito.employmentsystem.entities.Employee;
import com.gyabisito.employmentsystem.repositories.EmployeeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee add(Employee employee) {
        if (repo.existsByFirstNameAndMiddleNameAndLastNameAndBirthDate(
                employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getBirthDate())) {
            throw new IllegalArgumentException("Employee already exists");
        }

        return repo.save(employee);
    }

    public List<Employee> search(String firstName, String lastName, String position) {
        return repo.search(
                isBlank(firstName) ? null : firstName,
                isBlank(lastName) ? null : lastName,
                isBlank(position) ? null : position
        );
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public Optional<Employee> findById(Long uid) {
        return repo.findById(uid);
    }

    public void update(Long uid, Employee updated) {
        Employee existing = repo.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        // Validar duplicados si se edita a uno igual a otro
        boolean isDuplicate = repo.existsByFirstNameAndMiddleNameAndLastNameAndBirthDate(
                updated.getFirstName(), updated.getMiddleName(), updated.getLastName(), updated.getBirthDate()
        ) && !existing.equals(updated);

        if (isDuplicate) {
            throw new IllegalArgumentException("Ya existe un empleado con esos datos.");
        }

        // Copiar datos válidos
        existing.setFirstName(updated.getFirstName());
        existing.setMiddleName(updated.getMiddleName());
        existing.setLastName(updated.getLastName());
        existing.setBirthDate(updated.getBirthDate());
        existing.setPosition(updated.getPosition());

        repo.save(existing);
    }

}
