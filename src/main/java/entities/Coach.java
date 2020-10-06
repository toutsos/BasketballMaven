
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
    @NamedQuery(name = "Coach.findAll", query = "SELECT p FROM Coach p")})
@Entity
@Table(name = "coach")
public class Coach implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    protected String name;
    @Column(name = "salary")
    protected int salary;
    @Column(name = "start_date")
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
        return "Coach: " + name + ", salary: " + salary + ", join at: " + start;
    }
    
    
    
}//class
