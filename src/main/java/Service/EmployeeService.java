package be.ehb.employee.Service;
import be.ehb.employee.EmployeeRepository.EmployeeRepository;
import be.ehb.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> searchEmployees(String search) {
        return employeeRepository.findByNameContainingIgnoreCase(search);
    }

   public String deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "Employee not found";
    }
    public Employee updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        }
        return null;
    }

    public List<Employee> searchEmployeesByName(String search) {
        return employeeRepository.findByNameContainingIgnoreCase(search);
    }
}
