package be.ehb.employee.Controller;

import be.ehb.employee.Service.EmployeeService;
import be.ehb.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
//implementatie van employeeservice
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(required = false) String search  ) {
        if(search != null) {
            return employeeService.searchEmployees(search);
        } else {
            return employeeService.getAllEmployees();
        }
    }
    //Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }
    //Get employee by name
    @GetMapping("/name")
    public List<Employee> getEmployeeByName(@RequestParam String name) {
        return  employeeService.getEmployeeByName(name);
    }
    //add a new employee
    @PostMapping("add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
    //delete ab employee by ID
    @DeleteMapping("/delete")
    public ResponseEntity <Void> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    //update an employee
    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }
    //search employee by a keyword in their name
    //@GetMapping("/search")
   // public List<Employee> searchEmployees(@RequestParam String search) {
      //  return employeeService.searchEmployeesByName(search);
   // }
}
