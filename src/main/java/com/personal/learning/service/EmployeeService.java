package com.personal.learning.service;


import com.personal.learning.model.Employee;
import com.personal.learning.model.EmployeeDto;
import com.personal.learning.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Mono<EmployeeDto> saveEmployee(EmployeeDto employee) {
        Employee employee1 = mapDtoToDb(employee);
        return employeeRepository.save(employee1)
                .flatMap(emp2 -> {
                    return Mono.just(mapDbToEntity(emp2));
                });
    }

    public Mono<EmployeeDto> getEmployee(String id){
        return employeeRepository.findByEmpId(id)
                .map(emp2 -> {
                    return mapDbToEntity(emp2);
                });
    }

    public Mono<List<EmployeeDto>> getEmployees(){
        return employeeRepository.findAll()
                .collectList()
                .map(emps -> {
                    return mapListOfDbToEntity(emps);
                });
    }
    public Mono<List<EmployeeDto>> getEmployeesByName(String name) {
        return employeeRepository.findByName(name)
                .collectList()
                .map(emps -> {
                    return mapListOfDbToEntity(emps);
                });
    }

    private List<EmployeeDto> mapListOfDbToEntity(List<Employee> emps) {
        List<EmployeeDto> employees = new ArrayList<>();
        for(Employee emp2: emps){
            employees.add(new EmployeeDto(emp2.getId(), emp2.getEmpId(), emp2.getName()));
        }
        return employees;
    }

    private EmployeeDto mapDbToEntity(Employee emp2) {
        EmployeeDto emp = new EmployeeDto();
        emp.setId(emp2.getId());
        emp.setEmpId(emp2.getEmpId());
        emp.setName(emp2.getName());
        return emp;
    }

    private Employee mapDtoToDb(EmployeeDto employee) {
        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setEmpId(employee.getEmpId());
        emp.setName(employee.getName());
        return emp;
    }


}
