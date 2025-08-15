package com.gyabisito.employmentsystem.controllers;

import com.gyabisito.employmentsystem.entities.Compensation;
import com.gyabisito.employmentsystem.entities.Employee;
import com.gyabisito.employmentsystem.enums.CompensationType;
import com.gyabisito.employmentsystem.services.CompensationService;
import com.gyabisito.employmentsystem.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employees/{uid}/compensations")
public class CompensationController {

    private final EmployeeService employeeService;
    private final CompensationService compensationService;

    public CompensationController(EmployeeService employeeService, CompensationService compensationService) {
        this.employeeService = employeeService;
        this.compensationService = compensationService;
    }

    @GetMapping("/new")
    public String showForm(@PathVariable Long uid, Model model) {
        Employee employee = employeeService.findById(uid).orElseThrow();
        Compensation compensation = new Compensation();
        compensation.setEmployee(employee);

        model.addAttribute("compensation", compensation);
        model.addAttribute("types", CompensationType.values());
        return "compensation_form";
    }
    @PostMapping("/new")
    public String submitForm(@PathVariable Long uid,
                             @ModelAttribute("compensation") Compensation compensation,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        Employee employee = employeeService.findById(uid).orElseThrow();
        compensation.setEmployee(employee);

        try {
            compensationService.add(compensation);
            redirectAttributes.addFlashAttribute("success", "Compensación agregada.");
            return "redirect:/employees/search";
        } catch (IllegalArgumentException e) {
            model.addAttribute("types", CompensationType.values());
            model.addAttribute("error", e.getMessage());
            return "compensation_form";
        }
    }
    @GetMapping("/history")
    public String showHistoryForm(@PathVariable Long uid, Model model) {
        Employee employee = employeeService.findById(uid).orElseThrow();
        model.addAttribute("employee", employee);
        model.addAttribute("start", YearMonth.now().minusMonths(3));
        model.addAttribute("end", YearMonth.now());
        return "compensation_history";
    }

    @PostMapping("/history")
    public String showHistoryResults(@PathVariable Long uid,
                                     @RequestParam YearMonth start,
                                     @RequestParam YearMonth end,
                                     Model model) {
        Employee employee = employeeService.findById(uid).orElseThrow();

        if (start.isAfter(end)) {
            model.addAttribute("error", "La fecha de inicio no puede ser posterior a la fecha de fin.");
            model.addAttribute("employee", employee);
            return "compensation_history";
        }

        Map<YearMonth, BigDecimal> history = compensationService.getTotalByMonth(employee, start, end);

        model.addAttribute("employee", employee);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("history", history);
        return "compensation_history";
    }
    @GetMapping("/monthly")
    public String viewBreakdown(@PathVariable Long uid,
                                @RequestParam("month") YearMonth month,
                                Model model) {
        Employee employee = employeeService.findById(uid).orElseThrow();
        List<Compensation> compensations = compensationService.findByMonth(employee, month);

        BigDecimal total = compensations.stream()
                .map(Compensation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("employee", employee);
        model.addAttribute("month", month);
        model.addAttribute("compensations", compensations);
        model.addAttribute("total", total);
        return "compensation_breakdown";
    }
    @GetMapping("/edit/{compId}")
    public String showEditForm(@PathVariable Long uid,
                               @PathVariable Long compId,
                               Model model) {
        Employee employee = employeeService.findById(uid).orElseThrow();
        Compensation compensation = compensationService.findById(compId)
                .orElseThrow(() -> new IllegalArgumentException("Compensación no encontrada"));

        model.addAttribute("employee", employee);
        model.addAttribute("compensation", compensation);
        return "compensation_edit";
    }

    @PostMapping("/edit/{compId}")
    public String submitEdit(@PathVariable Long uid,
                             @PathVariable Long compId,
                             @ModelAttribute("compensation") Compensation updated,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        try {
            System.out.println("Updated" +  updated);
            compensationService.update(compId, updated);
            redirectAttributes.addFlashAttribute("success", "Compensación actualizada.");
            return "redirect:/employees/" + uid + "/compensations/monthly?month=" + updated.getPeriod();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("employee", employeeService.findById(uid).orElseThrow());
            return "compensation_edit";
        }
    }


}
