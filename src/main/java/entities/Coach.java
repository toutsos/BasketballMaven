
package entities;

import java.time.LocalDate;
import java.util.Date;

public class Coach {
    
    protected String name;
    protected int salary;
    protected LocalDate start;

    public Coach() {
    }

    public Coach(String name, int salary, LocalDate start) {
        this.name = name;
        this.salary = salary;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Our coach is: " + name + ", salary: " + salary + ", join at: " + start;
    }
    
    
    
}//class
