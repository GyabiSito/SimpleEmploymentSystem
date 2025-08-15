package com.gyabisito.employmentsystem.repositories;

import com.gyabisito.employmentsystem.entities.Compensation;
import com.gyabisito.employmentsystem.entities.Employee;
import com.gyabisito.employmentsystem.enums.CompensationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
    boolean existsByEmployeeAndTypeAndPeriod(Employee employee, CompensationType type, YearMonth period);
    List<Compensation> findByEmployeeAndPeriodBetween(Employee employee, YearMonth start, YearMonth end);
    List<Compensation> findByEmployeeAndPeriod(Employee employee, YearMonth month);


}
