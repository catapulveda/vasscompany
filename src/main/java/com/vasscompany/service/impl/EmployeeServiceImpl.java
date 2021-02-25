package com.vasscompany.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vasscompany.converter.EmployeeMapper;
import com.vasscompany.domain.Employee;
import com.vasscompany.domain.dto.EmployeeDTO;
import com.vasscompany.entities.EmployeeEntity;
import com.vasscompany.entities.UsuarioEntity;
import com.vasscompany.exception.APINotFoundException;
import com.vasscompany.repo.EmployeeRepository;
import com.vasscompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.*;

import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO findById(Long employeeId) {
        Optional<EmployeeEntity> byId = employeeRepository.findById(employeeId);
        if (!byId.isPresent()) {
            throw new APINotFoundException("No se encontró un empleado con ID "+employeeId);
        }
        EmployeeEntity entity = byId.get();
        EmployeeDTO employeeDTO = EmployeeMapper.INSTANCE.EmployeeEntityToEmployeeDTO(entity);
        Long salary = entity.getSalary();
        Double percent = entity.getPercent() / 100.0;
        Double baseSalary = salary * percent;
        employeeDTO.setSalarioBase(baseSalary.longValue());
        employeeDTO.setBono(salary - baseSalary.longValue());
        return employeeDTO;
    }

    @Override
    public Employee insert(Employee employee) {
        EmployeeEntity entity = EmployeeMapper.INSTANCE.EmployeeToEmployeeEntity(employee);
        entity.setStatus(Employee.Status.ACTIVE);

        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        String tokenValue = ((OAuth2AuthenticationDetails) details).getTokenValue();
        DecodedJWT jwt = JWT.decode(tokenValue);
        Map<String, Claim> claims = jwt.getClaims();
        entity.setJefeEntity(new UsuarioEntity(claims.get("id").asInt()));
        EmployeeEntity save = employeeRepository.save(entity);
        return EmployeeMapper.INSTANCE.EmployeeEntityToEmployee(save);
    }

    @Override
    public void deactivated(Long employeeId) {
        Optional<EmployeeEntity> byId = employeeRepository.findById(employeeId);
        if (!byId.isPresent()) {
            throw new APINotFoundException("No se encontró un empleado con ID "+employeeId);
        }
        byId.ifPresent(employeeEntity -> {
            employeeEntity.setStatus(Employee.Status.INACTIVE);
            employeeRepository.save(employeeEntity);
        });
    }

}
