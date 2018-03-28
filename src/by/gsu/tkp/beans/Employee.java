package by.gsu.tkp.beans;

import by.gsu.tkp.exceptions.EmployeeException;

import java.io.Serializable;

public class Employee implements Serializable{
    private static final int rate = 50;
    private String name;
    private String position;
    private int salary;
    private int surcharge;

    public Employee(String name, String position, int salary, int surchargeRate) {
        if(salary<=0 || surchargeRate<=0){
            throw new EmployeeException("invalid salary or surchargeRate");
        }
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.surcharge = surchargeRate*rate/100;
    }

    public static int getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(int surchargeRate) {
        this.surcharge = surchargeRate*rate/100;
    }

    @Override
    public String toString() {
        return name+";"+position+";"+salary+";"+surcharge;
    }
}
