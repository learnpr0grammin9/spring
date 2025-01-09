package com.amit.employee_swagger.repository;

import com.amit.employee_swagger.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
