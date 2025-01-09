package com.amit.employee_swagger.controller;

import com.amit.employee_swagger.exception.ResourceNotFoundException;
import com.amit.employee_swagger.model.Employee;
import com.amit.employee_swagger.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee() {
        System.out.println("getAllEmployee");
        return this.employeeRepository.findAll();
    }



    // Get user by id
    @GetMapping("/{id}")
    public Employee getUserById(@PathVariable long id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
    }

    // Create user
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }

    // Update user
    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
        Employee existingEmployee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDesignation(employee.getDesignation());
        return this.employeeRepository.save(existingEmployee);
    }

    // Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteUser(@PathVariable long id) {
        Employee existingUser = this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        this.employeeRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }



}
