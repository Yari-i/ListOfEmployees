package pro.sky.ListOfEmployees.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("all")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam int departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }

    @GetMapping("all-grouped")
    public Map<Integer, List<Employee>> getAllEmployeesGropedByDepartment() {
        return departmentService.getAllEmployeesGropedByDepartment();
    }
}