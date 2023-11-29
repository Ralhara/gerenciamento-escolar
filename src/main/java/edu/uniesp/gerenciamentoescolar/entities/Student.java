package edu.uniesp.gerenciamentoescolar.entities;

import jakarta.persistence.Entity;

@Entity
public class Student extends Person {
    public String classNumber;
    public String enrolmentDate;

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getEnrolmentDate() {
        return enrolmentDate;
    }

    public void setEnrolmentDate(String enrolmentDate) {
        this.enrolmentDate = enrolmentDate;
    }
}
