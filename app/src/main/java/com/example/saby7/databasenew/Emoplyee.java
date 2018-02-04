package com.example.saby7.databasenew;

/**
 * Created by saby7 on 04-07-2017.
 */

class Emoplyee {
    private String ename, esalary;
    private String edesignation;

    public Emoplyee(String ename, String esalary, String edesignation) {
        this.ename = ename;
        this.esalary = esalary;
        this.edesignation = edesignation;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsalary() {
        return esalary;
    }

    public void setEsalary(String esalary) {
        this.esalary = esalary;
    }

    public String getEdesignation() {
        return edesignation;
    }

    public void setEdesignation(String edesignation) {
        this.edesignation = edesignation;
    }
}

