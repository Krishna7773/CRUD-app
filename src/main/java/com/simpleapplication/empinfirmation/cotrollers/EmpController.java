package com.simpleapplication.empinfirmation.cotrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleapplication.empinfirmation.entities.Employee;
import com.simpleapplication.empinfirmation.repositories.EmployeeRepository;

@RestController
@RequestMapping("/User")
public class EmpController {

    @Autowired
    EmployeeRepository employeeRepository;

    List<Employee> list = new ArrayList<>();

    @PostMapping("/employee")
    public ResponseEntity<Employee> creatEmployee(@RequestBody Employee emp) {
        employeeRepository.save(emp);
        return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    public List<Employee> getAll() {
        employeeRepository.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getById(@PathVariable int id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> empUpdate(@PathVariable int id, @RequestBody Employee employee) {
              employee.seteId(id);
               employeeRepository.save(employee);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteAll() {
        employeeRepository.deleteAll();
        return new ResponseEntity<String>("all data deleted", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable int id)
    {
        employeeRepository.deleteById(id);
        return new ResponseEntity<String>("user deleted",HttpStatus.NO_CONTENT);
    }
}
