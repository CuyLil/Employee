package be.ehb.employee;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

import EmployeeRepository.EmployeeRepository;
import Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeApplicationTests {
	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;
	private Employee employee;

	@BeforeEach
	public void setUp() {
		employee = new Employee();
		employee.setId(1L);
		employee.setName("John Doe");
		employee.setPhoneNumber("1234567890");
		employee.setEmail("john@doe.com");
	}

	@Test
	public void testEGetAllEmployee() {
		when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));
		List<Employee> employees = employeeService.getAllEmployees();
		assertThat(employees).hasSize(1).contains(employee);
		verify(employeeRepository, times(1)).findAll();
	}

	@Test
	public void testGetEmployeeById() {
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
		assertThat(foundEmployee).isPresent().contains(employee);
		verify(employeeRepository, times(1)).findById(1L);
	}

	@Test
	public void testAddEmployee() {
		when(employeeRepository.save(employee)).thenReturn(employee);
		Employee savedEmployee = employeeService.addEmployee(employee);
		assertThat(savedEmployee).isNotNull();
		verify(employeeRepository, times(1)).save(employee);
	}

	@Test
	public void testDeleteEmployee() {
		doNothing().when(employeeRepository).deleteById(1L);
		employeeService.deleteEmployee(1L);
		verify(employeeRepository, times(1)).deleteById(1L);
	}
	
	@Test
	public void testUpdateEmployee() {
		when(employeeRepository.save(employee)).thenReturn(employee);
		employee.setName("Jane Doe");
		Employee updatedEmployee = employeeService.updateEmployee(1L, employee);
		assertThat(updatedEmployee.getName()).isEqualTo("Jane Doe");
		verify(employeeRepository, times(1)).save(employee);
	}

}
