package pro.sky.ListOfEmployees.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.ListOfEmployees.exceptions.EmployeeAlreadyAddedException;
import pro.sky.ListOfEmployees.exceptions.EmployeeNotFoundException;
import pro.sky.ListOfEmployees.exceptions.EmployeeStorageIsFullException;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int MAX_EMPLOYEES = 10;

    private final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void initEmployees() {
        add("Ivan", "Ivanov1", 100_000, 1);
        add("Ivan", "Ivanov2", 10_000, 1);
        add("Ivan", "Ivanov3", 50_000, 1);
        add("Ivan", "Ivanov4", 200_000, 2);
        add("Ivan", "Ivanov5", 15_000, 2);
        add("Ivan", "Ivanov6", 25_000, 2);
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        if (employees.size() >= MAX_EMPLOYEES){
            throw new EmployeeStorageIsFullException("Лимит сотрудников превышен");
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует.");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }


    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
//        if (employees.containsKey(employee.getFullName())) {
//            return employees.get(employee.getFullName());
//        }
        return employees
                .values()
                .stream()
                .filter(it -> it.getFullName().equals(employee.getFullName()))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден."));
    }

    @Override
    public Map<String, Employee> getAll() {
        return employees;
    }
}