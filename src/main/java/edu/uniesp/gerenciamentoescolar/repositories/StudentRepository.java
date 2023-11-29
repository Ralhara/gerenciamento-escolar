package edu.uniesp.gerenciamentoescolar.repositories;

import edu.uniesp.gerenciamentoescolar.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findById(long id);
    Iterable<Student> findByName(String name);
}
