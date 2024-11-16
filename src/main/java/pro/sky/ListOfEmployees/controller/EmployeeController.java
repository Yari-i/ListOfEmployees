package pro.sky.ListOfEmployees.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public ResponseEntity<Employee> add(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam int salary,
                                        @RequestParam int departmentId) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(employeeService.add(firstName, lastName, salary, departmentId));
    }


    @GetMapping("/remove")
    public Employee delete(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }


    @GetMapping
    public Map<String, Employee> getAll() {
        return employeeService.getAll();
    }
}
