package com.simpleapplication.empinfirmation.repositories;

import org.springframework.data.repository.CrudRepository;

import com.simpleapplication.empinfirmation.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee ,Integer> {

}
