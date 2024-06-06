package com.ra.bt_ss2.service.impl;

import com.ra.bt_ss2.model.entity.Employee;
import com.ra.bt_ss2.repository.EmployeeRepository;
import com.ra.bt_ss2.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImp implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeRepository.findById(employee.getId()).orElseThrow(() -> new NoSuchElementException("Employee not found"));
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public Page<Employee> getEmployeePaging(String searchName, Integer page, Integer itemPage, String orderBy, String direction) {
        Pageable pageable = null;
        if (orderBy != null && direction != null) {
            //Co sap xep
            Sort sort = null;
            switch (direction) {
                case "ASC":
                    sort = Sort.by(orderBy).ascending();
                    break;
                case "DESC":
                    sort = Sort.by(orderBy).descending();
                    break;
            }
            pageable = PageRequest.of(page, itemPage, sort);

        } else {
            pageable = PageRequest.of(page, itemPage);
        }

        //khong tim kiem
        if (searchName != null && !searchName.isEmpty()) {
            return employeeRepository.findEmployeeByNamePaging(searchName, pageable);

        } else {
            return employeeRepository.findAll(pageable);
        }

    }

    @Override
    public List<Employee> saveAll(List<Employee> list) {
        return employeeRepository.saveAll(list);
    }

    @Override
    public List<Employee> findEmployeeBySalaryBetweenMinSalaryAndMaxSalary(Double minSalary, Double maxSalary) {
        return employeeRepository.findEmployeeBySalaryBetweenMinSalaryAndMaxSalary(minSalary, maxSalary);
    }

    @Override
    public List<Employee> find10EmployeeGreatestSalary() {
        return employeeRepository.find10EmployeeGreatestSalary();
    }

}
