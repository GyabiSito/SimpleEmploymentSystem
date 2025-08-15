package com.gyabisito.employmentsystem.services;

import com.gyabisito.employmentsystem.entities.Compensation;
import com.gyabisito.employmentsystem.entities.Employee;
import com.gyabisito.employmentsystem.enums.CompensationType;
import com.gyabisito.employmentsystem.repositories.CompensationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static com.gyabisito.employmentsystem.enums.CompensationType.*;

@Service
public class CompensationService {

    private final CompensationRepository repo;

    public CompensationService(CompensationRepository repo) {
        this.repo = repo;
    }

    public void add(Compensation compensation) {
        switch (compensation.getType()) {
            case SALARY:
                if (repo.existsByEmployeeAndTypeAndPeriod(compensation.getEmployee(), CompensationType.SALARY, compensation.getPeriod())) {
                    throw new IllegalArgumentException("Ya existe un salario para ese mes.");
                }
                break;
            case BONUS, COMMISSION, ALLOWANCE, ADJUSTMENT:
                if (compensation.getAmount().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("El monto no puede ser cero.");
                }
                break;
        }

        // validar descripción
        if (EnumSet.of(BONUS, COMMISSION, ALLOWANCE, ADJUSTMENT).contains(compensation.getType()) &&
                (compensation.getDescription() == null || compensation.getDescription().isBlank())) {
            throw new IllegalArgumentException("La descripción es obligatoria para este tipo de compensación.");
        }

        repo.save(compensation);
    }


    public Map<YearMonth, BigDecimal> getTotalByMonth(Employee employee, YearMonth start, YearMonth end) {
        List<Compensation> compensations = repo.findByEmployeeAndPeriodBetween(employee, start, end);

        return compensations.stream()
                .collect(Collectors.groupingBy(
                        Compensation::getPeriod,
                        TreeMap::new, // mantiene orden cronológico
                        Collectors.reducing(BigDecimal.ZERO, Compensation::getAmount, BigDecimal::add)
                ));
    }

    public List<Compensation> findByMonth(Employee employee, YearMonth month) {
        return repo.findByEmployeeAndPeriod(employee, month);
    }

    public void update(Long id, Compensation updated) {
        Compensation existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Compensación no encontrada"));

        if (updated.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("El monto no puede ser cero.");
        }

        if (EnumSet.of(BONUS, COMMISSION, ALLOWANCE, ADJUSTMENT).contains(existing.getType())
                && (updated.getDescription() == null || updated.getDescription().isBlank())) {
            throw new IllegalArgumentException("La descripción es obligatoria para este tipo de compensación.");
        }

        existing.setAmount(updated.getAmount());
        existing.setDescription(updated.getDescription());

        repo.save(existing);
    }


    public Optional<Compensation> findById(Long compId) {
        return repo.findById(compId);
    }
}
