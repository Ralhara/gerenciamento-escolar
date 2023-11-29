package edu.uniesp.gerenciamentoescolar.controllers;

import edu.uniesp.gerenciamentoescolar.entities.Employee;
import edu.uniesp.gerenciamentoescolar.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EmployeeController {
    private EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public String listAllPage(Model model) {
        Iterable<Employee> employees = this.repository.findAll();
        model.addAttribute("employees", employees);

        return "employees-list-all";
    }

    @GetMapping("/employees/new")
    public String createNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees-create-new";
    }

    @PostMapping("/employees/new")
    public String createNewSubmit(@ModelAttribute Employee employee) {
        this.repository.save(employee);

        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/update")
    public String updateForm(Model model, @PathVariable Long id) throws Exception {
        Optional<Employee> employee = this.repository.findById(id);
        if (employee.isEmpty()) {
            throw new Exception("Could not find Employee with Id " + id);
        }

        model.addAttribute("employee", employee);
        return "employees-update";
    }

    @PostMapping("/employees/{id}/update")
    public String updateSubmit(@ModelAttribute Employee employee, @PathVariable Long id) throws Exception {
        Optional<Employee> persistedEmployeeOptional = this.repository.findById(id);
        if (persistedEmployeeOptional.isEmpty()) {
            throw new Exception("Could not find Employee with Id " + id);
        }

        Employee persistedEmployee = persistedEmployeeOptional.get();

        persistedEmployee.name = employee.name;
        persistedEmployee.registrationNumber = employee.registrationNumber;

        this.repository.save(persistedEmployee);

        return "redirect:/employees";
    }

    @GetMapping("/employees/find-by-name")
    public String findByNameForm(Model model, @RequestParam String name) {
        Iterable<Employee> employees = this.repository.findByName(name);
        model.addAttribute("employees", employees);

        return "employees-list-all";
    }
}
