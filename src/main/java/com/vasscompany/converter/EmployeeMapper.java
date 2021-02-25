package com.vasscompany.converter;

import com.vasscompany.domain.Employee;
import com.vasscompany.domain.dto.EmployeeDTO;
import com.vasscompany.entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee EmployeeEntityToEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity EmployeeToEmployeeEntity(Employee employee);

    EmployeeDTO EmployeeEntityToEmployeeDTO(EmployeeEntity entity);

}
