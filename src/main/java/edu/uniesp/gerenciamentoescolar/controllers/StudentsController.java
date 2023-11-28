package edu.uniesp.gerenciamentoescolar.controllers;

import edu.uniesp.gerenciamentoescolar.entities.Student;
import edu.uniesp.gerenciamentoescolar.repositories.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/students/{id}/update")
    public String updateForm(Model model, @PathVariable Long id) throws Exception {
        Optional<Student> student = this.repository.findById(id);
        if (student.isEmpty()) {
            throw new Exception("Could not find Student with Id " + id);
        }

        model.addAttribute("student", student);
        return "students-update";
    }

    @PostMapping("/students/{id}/update")
    public String updateSubmit(@ModelAttribute Student student, @PathVariable Long id) throws Exception {
        Optional<Student> persistedStudentOptional = this.repository.findById(id);
        if (persistedStudentOptional.isEmpty()) {
            throw new Exception("Could not find Student with Id " + id);
        }

        Student persistedStudent = persistedStudentOptional.get();

        persistedStudent.firstName = student.firstName;
        persistedStudent.lastName = student.lastName;

        this.repository.save(persistedStudent);

        return "redirect:/students";
    }
}


