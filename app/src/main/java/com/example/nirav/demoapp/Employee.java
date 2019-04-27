package com.example.nirav.demoapp;

/**
 * Created by nirav on 25-Apr-19.
 */

public class Employee {
    int id;
    String name, dept, joiningDate;
    double salary;

    public Employee(int id, String name, String dept, String joiningDate, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public double getSalary() {
        return salary;
    }
}