package com.vasscompany.entities;

import com.vasscompany.domain.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long salary;
    private Integer percent;
    private Employee.Status status;
    private UsuarioEntity jefeEntity;

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    @Column(name = "SALARY")
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @NotNull
    @Column(name = "PERCENT")
    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public Employee.Status getStatus() {
        return status;
    }

    public void setStatus(Employee.Status status) {
        this.status = status;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SUPERIOR", foreignKey = @ForeignKey(name = "id_usuario_fk"))
    public UsuarioEntity getJefeEntity() {
        return jefeEntity;
    }

    public void setJefeEntity(UsuarioEntity usuarioEntity) {
        this.jefeEntity = usuarioEntity;
    }
}
