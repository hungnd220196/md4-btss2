package com.ra.bt_ss2.controller;

import com.ra.bt_ss2.model.dto.request.EmployeeRequest;
import com.ra.bt_ss2.model.entity.Employee;
import com.ra.bt_ss2.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long empId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.insertEmployee(employee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.findEmployeeByName(name), HttpStatus.OK);
    }

    @GetMapping("/searchAndPaging")
    public ResponseEntity<List<Employee>> getEmployeesByPage(@RequestBody EmployeeRequest employeeRequest) {
        List<Employee> content = employeeService.getEmployeePaging(employeeRequest.getName(), employeeRequest.getPage() - 1, employeeRequest.getItemPage(), employeeRequest.getSortBy(), employeeRequest.getDirection()).getContent();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeInRange/{min}/{max}")
    public ResponseEntity<List<Employee>> getEmployeeInRange(@PathVariable Double min, @PathVariable Double max) {
        List<Employee> employeeList = employeeService.findEmployeeBySalaryBetweenMinSalaryAndMaxSalary(min, max);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);

    }

    @GetMapping("/get10EmployeeGreatestSalary")
    public ResponseEntity<List<Employee>> get10EmployeeGreatestSalary() {
        List<Employee> list = employeeService.find10EmployeeGreatestSalary();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
