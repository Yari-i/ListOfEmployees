package pro.sky.ListOfEmployees.controller;


import org.springframework.web.bind.annotation.*;
import pro.sky.ListOfEmployees.model.Employee;
import pro.sky.ListOfEmployees.service.DepartmentService;


import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
    public Object getAll(@RequestParam Optional<Integer> departmentId) {
        if (departmentId.isEmpty()) {
            return departmentService.getAllEmployeesGropedByDepartment();
        } else {
            return departmentService.getAllEmployeesByDepartment(departmentId.get());
        }
    }


}

