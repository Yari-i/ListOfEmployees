package pro.sky.ListOfEmployees.service;

import pro.sky.ListOfEmployees.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    int getEmployeeSalarySum(int departmentId);

    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    List<Employee> getAllEmployeesByDepartment(int departmentId);

    Map<Integer, List<Employee>> getAllEmployeesGropedByDepartment();

}
