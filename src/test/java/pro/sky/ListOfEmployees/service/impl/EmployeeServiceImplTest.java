package pro.sky.ListOfEmployees.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.ListOfEmployees.exceptions.EmployeeAlreadyAddedException;
import pro.sky.ListOfEmployees.exceptions.EmployeeNotFoundException;
import pro.sky.ListOfEmployees.exceptions.EmployeeStorageIsFullException;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.ListOfEmployees.service.impl.EmployeeServiceImpl.MAX_EMPLOYEES;

class EmployeeServiceImplTest {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    public void clear() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void shouldCorrectlyAdd() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        Employee actualEmployee = employeeService.add(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getSalary(), expectedEmployee.getDepartmentId());

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void shouldCorrectlyAddedEmployeeWhenEmployeeStorageIsFullException() {

        for (int i = 0; i < MAX_EMPLOYEES; i++) {
            employeeService.add("Ivan" + i, "Ivanov", 10_000, 1);
        }

        assertThrows(
                EmployeeStorageIsFullException.class,
                () -> {
                    employeeService.add("Ivan", "Ivanov", 10_000, 1);
                }
        );
    }

    @Test
    void shouldCorrectlyAddedWhenEmployeeAlreadyAddedException() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        employeeService.add("Ivan", "Ivanov", 10_000, 1);

        assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> {
                    employeeService.add("Ivan", "Ivanov", 10_000, 1);
                }
        );
    }

    @Test
    void shouldCorrectlyRemove() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        employeeService.add("Ivan", "Ivanov", 10_000, 1);

        Employee actualEmployee = employeeService.remove("Ivan", "Ivanov", 10_000, 1);

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void shouldCorrectlyRemoveWhenEmployeeNotFoundException() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        employeeService.add("Ivan1", "Ivanov1", 10_000, 1);

        assertThrows(
                EmployeeNotFoundException.class,
                () -> {
                    employeeService.remove("Ivan", "Ivanov", 10_000, 1);
                }
        );
    }

    @Test
    void shouldCorrectlyFind() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        employeeService.add("Ivan", "Ivanov", 10_000, 1);

        Employee actualEmployee = employeeService.find("Ivan", "Ivanov", 10_000, 1);

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void shouldCorrectlyFindWhenEmployeeNotFoundException() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov", 10_000, 1);

        employeeService.add("Ivan1", "Ivanov1", 10_000, 1);

        assertThrows(
                EmployeeNotFoundException.class,
                () -> {
                    employeeService.find("Ivan", "Ivanov", 10_000, 1);
                }
        );
    }

    @Test
    void shouldCorrectlyGetAll() {

        Employee expectedEmployee1 = new Employee("Ivan1", "Ivanov1", 10_000, 1);
        Employee expectedEmployee2 = new Employee("Ivan2", "Ivanov2", 10_000, 1);

        employeeService.add("Ivan1", "Ivanov1", 10_000, 1);
        employeeService.add("Ivan2", "Ivanov2", 10_000, 1);

        Map<String, Employee> expectedEmployees = new HashMap<>() {{
            put("Ivan1 Ivanov1", expectedEmployee1);
            put("Ivan2 Ivanov2", expectedEmployee2);
        }};

        Map<String, Employee> actualCollections = employeeService.getAll();

        assertEquals(expectedEmployees, actualCollections);
    }
}