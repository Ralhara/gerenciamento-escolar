package edu.uniesp.gerenciamentoescolar.controllers;

import edu.uniesp.gerenciamentoescolar.entities.Teacher;
import edu.uniesp.gerenciamentoescolar.repositories.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TeacherController {
    private TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teachers")
    public String listAllPage(Model model) {
        Iterable<Teacher> teachers = this.repository.findAll();
        model.addAttribute("teachers", teachers);

        return "teachers-list-all";
    }

    @GetMapping("/teachers/new")
    public String createNewForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers-create-new";
    }

    @PostMapping("/teachers/new")
    public String createNewSubmit(@ModelAttribute Teacher teacher) {
        this.repository.save(teacher);

        return "redirect:/teachers";
    }

    @PostMapping("/teachers/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        this.repository.deleteById(id);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}/update")
    public String updateForm(Model model, @PathVariable Long id) throws Exception {
        Optional<Teacher> teacher = this.repository.findById(id);
        if (teacher.isEmpty()) {
            throw new Exception("Could not find Teacher with Id " + id);
        }

        model.addAttribute("teacher", teacher);
        return "teachers-update";
    }

    @PostMapping("/teachers/{id}/update")
    public String updateSubmit(@ModelAttribute Teacher teacher, @PathVariable Long id) throws Exception {
        Optional<Teacher> persistedTeacherOptional = this.repository.findById(id);
        if (persistedTeacherOptional.isEmpty()) {
            throw new Exception("Could not find Teacher with Id " + id);
        }

        Teacher persistedTeacher = persistedTeacherOptional.get();

        persistedTeacher.name = teacher.name;
        persistedTeacher.registrationNumber = teacher.registrationNumber;

        this.repository.save(persistedTeacher);

        return "redirect:/teachers";
    }

    @GetMapping("/teachers/find-by-name")
    public String findByNameForm(Model model, @RequestParam String name) {
        Iterable<Teacher> teachers = this.repository.findByName(name);
        model.addAttribute("teachers", teachers);

        return "teachers-list-all";
    }
}