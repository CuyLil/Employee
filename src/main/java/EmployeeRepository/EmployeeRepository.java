package be.ehb.employee.EmployeeRepository;

import be.ehb.employee.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
