package com.vasscompany.controller;

import com.vasscompany.domain.Employee;
import com.vasscompany.domain.dto.EmployeeDTO;
import com.vasscompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(path = "/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('VIEW_EMPLOYEE')")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long employeeId)  {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SAVE_EMPLOYEE')")
    public ResponseEntity<Employee> insert(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.insert(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> deactivate(@PathVariable Long employeeId) {
        employeeService.deactivated(employeeId);
        return ResponseEntity.ok("Empleado desactivado");
    }

}
