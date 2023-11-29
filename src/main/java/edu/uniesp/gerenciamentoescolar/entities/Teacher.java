package edu.uniesp.gerenciamentoescolar.entities;

import jakarta.persistence.Entity;

@Entity
public class Teacher extends Person {
    public String subject;
    public String contractStartDate;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }
}
