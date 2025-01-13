package pro.sky.ListOfEmployees.controller;


import org.springframework.web.bind.annotation.*;
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

    @GetMapping("{departmentId}/employees")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable int departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }


    @GetMapping("{departmentId}/salary/sum")
    public int getEmployeeSalarySum(@PathVariable int departmentId) {
        return departmentService.getEmployeeSalarySum(departmentId);
    }

    @GetMapping("{departmentId}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("{departmentId}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable int departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("employees")
    public Map<Integer, List<Employee>> getAllEmployeesGropedByDepartment() {
        return departmentService.getAllEmployeesGropedByDepartment();
    }


}

