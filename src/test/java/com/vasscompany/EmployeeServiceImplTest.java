package com.vasscompany;

import com.vasscompany.domain.Employee;
import com.vasscompany.domain.dto.EmployeeDTO;
import com.vasscompany.entities.EmployeeEntity;
import com.vasscompany.exception.APINotFoundException;
import com.vasscompany.repo.EmployeeRepository;
import com.vasscompany.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_Pass_findById() {
        Long employeeId = 1L;
        EmployeeEntity entity = Mockito.mock(EmployeeEntity.class);
        Optional<EmployeeEntity> optional = Optional.of(entity);
        Mockito.when(repository.findById(employeeId)).thenReturn(optional);
        EmployeeDTO byId = service.findById(employeeId);
        Assertions.assertNotNull(byId);
    }

    @Test
    void should_Pass_findByIdAndThrowException() {
        Assertions.assertThrows(APINotFoundException.class, () -> {
            Long employeeId = 1L;
            Optional<EmployeeEntity> optional = Optional.empty();
            Mockito.when(repository.findById(employeeId)).thenReturn(optional);
            EmployeeDTO byId = service.findById(employeeId);
        });
    }

    @Test
    void insert() {
        Employee employee = Mockito.mock(Employee.class);
        EmployeeEntity entity = Mockito.mock(EmployeeEntity.class);
        Mockito.when(repository.save(Mockito.any())).thenReturn(entity);
        Employee insert = service.insert(employee);
        Assertions.assertNotNull(insert);
    }
}
