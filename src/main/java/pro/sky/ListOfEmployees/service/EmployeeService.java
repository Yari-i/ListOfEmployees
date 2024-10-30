package pro.sky.ListOfEmployees.service;


import pro.sky.ListOfEmployees.model.Employee;

import java.util.Map;

public interface EmployeeService {


    Employee add(String firstName, String lastName, int salary, int departmentId);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Map<String, Employee> getAll();

}
