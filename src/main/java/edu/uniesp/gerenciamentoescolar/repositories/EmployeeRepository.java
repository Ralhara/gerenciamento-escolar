package edu.uniesp.gerenciamentoescolar.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.uniesp.gerenciamentoescolar.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findById(long id);
    Iterable<Employee> findByName(String name);
}