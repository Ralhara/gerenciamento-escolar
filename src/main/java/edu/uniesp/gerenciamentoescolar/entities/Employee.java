package edu.uniesp.gerenciamentoescolar.entities;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person {
    public String function;
    public String contractStartDate;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }
}
