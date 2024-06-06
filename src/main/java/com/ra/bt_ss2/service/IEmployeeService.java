package com.ra.bt_ss2.service;

import com.ra.bt_ss2.model.entity.Employee;
import org.hibernate.sql.Insert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee insertEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Long id);

    List<Employee> findEmployeeByName(String name);

    Page<Employee> getEmployeePaging(String searchName, Integer page, Integer itemPage, String orderBy, String direction);

    List<Employee> saveAll(List<Employee> list);

    List<Employee> findEmployeeBySalaryBetweenMinSalaryAndMaxSalary(Double minSalary, Double maxSalary);

    List<Employee> find10EmployeeGreatestSalary();
}
