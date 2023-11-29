package edu.uniesp.gerenciamentoescolar.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.uniesp.gerenciamentoescolar.entities.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findById(long id);
    Iterable<Teacher> findByName(String name);
}