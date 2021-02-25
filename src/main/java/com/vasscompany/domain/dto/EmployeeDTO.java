package com.vasscompany.domain.dto;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long salarioBase;
    private Long bono;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Long salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Long getBono() {
        return bono;
    }

    public void setBono(Long bono) {
        this.bono = bono;
    }
}
