package pro.sky.ListOfEmployees.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.EmployeeService;

import java.util.*;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> employees = new HashMap<>() {{
        put("Ivan1 Ivanov1", new Employee("Ivan1", "Ivanov1", 10_000, 1));
        put("Ivan2 Ivanov2", new Employee("Ivan2", "Ivanov2", 25_000, 1));
        put("Ivan3 Ivanov3", new Employee("Ivan3", "Ivanov3", 50_000, 1));
    }};

    @Test
    void shouldCorrectlyGetEmployeeSalarySum() {
        int departmentId = 1;
        int expectedSum = 85_000;


        Mockito.when(employeeService.getAll()).thenReturn(employees);

        int actualSum = departmentService.getEmployeeSalarySum(departmentId);

        Assertions.assertEquals(expectedSum, actualSum);
    }

    @Test
    void shouldCorrectlyGetEmployeeWithMaxSalary() {
        int departmentId = 1;
        Employee expectedEmployee = employees.get("Ivan3 Ivanov3");

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void shouldCorrectlyGetEmployeeWithMinSalary() {

        int departmentId = 1;
        Employee expectedEmployee = employees.get("Ivan1 Ivanov1");

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void shouldCorrectlyGetAllEmployeesByDepartment() {

        int departmentId = 1;

        Collection<Employee> expectedEmployees = new ArrayList<>(employees.values());

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        List<Employee> actualEmployees = departmentService.getAllEmployeesByDepartment(departmentId);

        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void shouldCorrectlyGetAllEmployeesGropedByDepartment() {

        Map<Integer, List<Employee>> expectedEmployees = new HashMap<>() {{
            put(1, new ArrayList<>(employees.values()));
        }};

        Mockito.when(employeeService.getAll()).thenReturn(employees);

        Map<Integer, List<Employee>> actualEmployees = departmentService.getAllEmployeesGropedByDepartment();

        Assertions.assertEquals(expectedEmployees, actualEmployees);
    }
}