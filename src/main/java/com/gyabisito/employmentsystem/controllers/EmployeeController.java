package com.gyabisito.employmentsystem.controllers;

import com.gyabisito.employmentsystem.entities.Employee;
import com.gyabisito.employmentsystem.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("/new")
    public String submitForm(@ModelAttribute("employee") @Valid Employee employee,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "employee_form";
        }

        try {
            service.add(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado agregado correctamente.");
            return "redirect:/employees/new";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "employee_form";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error inesperado.");
            return "employee_form";
        }
    }

    @GetMapping("/search")
    public String searchForm(@RequestParam(required = false) String firstName,
                             @RequestParam(required = false) String lastName,
                             @RequestParam(required = false) String position,
                             Model model) {

        List<Employee> results = service.search(
                isBlank(firstName) ? null : firstName,
                isBlank(lastName) ? null : lastName,
                isBlank(position) ? null : position
        );

        model.addAttribute("results", results);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("position", position);
        return "employee_search";
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
    @GetMapping("/edit/{uid}")
    public String showEditForm(@PathVariable Long uid, Model model) {
        Employee employee = service.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        model.addAttribute("employee", employee);
        System.out.println("Emploe" + employee);
        return "employee_edit";
    }


    @PostMapping("/edit/{uid}")
    public String updateEmployee(@PathVariable Long uid,
                                 @ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        if (bindingResult.hasErrors()) {
            return "employee_edit";
        }

        try {
            service.update(uid, employee);
            redirectAttributes.addFlashAttribute("success", "Empleado actualizado correctamente.");
            return "redirect:/employees/search";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "employee_edit";
        }
    }

}
