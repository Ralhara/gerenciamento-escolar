package edu.uniesp.gerenciamentoescolar.controllers;

import edu.uniesp.gerenciamentoescolar.entities.Student;
import edu.uniesp.gerenciamentoescolar.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentsController {
    private StudentRepository repository;

    public StudentsController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    public String listAllPage(Model model) {
        Iterable<Student> students = this.repository.findAll();
        model.addAttribute("students", students);

        return "students-list-all";
    }

    @GetMapping("/students/new")
    public String createNewForm(Model model) {
        model.addAttribute("student", new Student());
        return "students-create-new";
    }

    @PostMapping("/students/new")
    public String createNewSubmit(@ModelAttribute Student student) {
        this.repository.save(student);

        return "redirect:/students";
    }

    @PostMapping("/students/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "redirect:/students";
    }
}


