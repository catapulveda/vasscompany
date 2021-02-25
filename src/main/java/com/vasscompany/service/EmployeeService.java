package com.vasscompany.service;

import com.vasscompany.domain.Employee;
import com.vasscompany.domain.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO findById(Long employeeId);

    Employee insert(Employee employee);

    void deactivated(Long employeeId);
}
