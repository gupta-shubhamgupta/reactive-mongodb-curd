package com.personal.learning.controller;


import com.personal.learning.model.Employee;
import com.personal.learning.model.EmployeeDto;
import com.personal.learning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RestEmployeeController {


    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public Mono<ResponseEntity<String>> createEmployee(@RequestBody EmployeeDto employee) {
        return employeeService.saveEmployee(employee)
                .map(emp -> {
                    return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
                });

    }

    @GetMapping("/employee/{id}")
    public Mono<ResponseEntity<EmployeeDto>> getEmployee(@PathVariable String id) {
        System.out.println("id: " + id);
        return employeeService.getEmployee(id)
                .map(emp -> {
                    return new ResponseEntity<>(emp, HttpStatus.OK);
                });
    }

    @GetMapping("/employees")
    public Mono<ResponseEntity<List<EmployeeDto>>> getEmployees() {
        return employeeService.getEmployees()
                .map(emps ->{
                    return new ResponseEntity<>(emps, HttpStatus.OK);
                });
    }

    @GetMapping("/employees/{name}")
    public Mono<ResponseEntity<List<EmployeeDto>>> getEmployees(@PathVariable String name) {
        return employeeService.getEmployeesByName(name)
                .map(emps ->{
                    return new ResponseEntity<>(emps, HttpStatus.OK);
                });
    }
}
