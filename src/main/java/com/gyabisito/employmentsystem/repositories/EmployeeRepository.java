package com.gyabisito.employmentsystem.repositories;

import com.gyabisito.employmentsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByFirstNameAndMiddleNameAndLastNameAndBirthDate(String firstName, String middleName, String lastName, LocalDate birthDate);

    @Query("""
                SELECT e FROM Employee e
                WHERE (:firstName IS NULL OR LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))
                  AND (:lastName IS NULL OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
                  AND (:position IS NULL OR LOWER(e.position) LIKE LOWER(CONCAT('%', :position, '%')))
            """)
    List<Employee> search(@Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          @Param("position") String position);
}