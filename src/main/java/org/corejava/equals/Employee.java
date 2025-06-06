package org.corejava.equals;

import java.io.Serializable;
import java.time.*;
import java.util.Objects;

public class Employee implements Serializable {

    public static int NAME_SIZE = 40;
    public static int RECORD_SIZE = 100;
    private String name;
    private double salary;
    private LocalDate hireDay;

    protected String pro_field = "protected field";     //protected field;

    public Employee(String name)
    {
        this.name = name;
    }
    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName(){
        return name;
    }
    
    public double getSalary(){
        return salary;
    }
    public LocalDate getHireDay(){
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
     
    public boolean equals(Object otherObject){

        if(this == otherObject) return true;

        if(otherObject == null) return false;

        if(getClass() != otherObject.getClass()) return false;

        Employee other = (Employee) otherObject;

        return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
    }

    public int hashCode(){
        return Objects.hash(name, salary, hireDay);
    }

    public String toString(){
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }

    public void test(Employee other){
        System.out.println("other.name:" + other.name);
    }
}