/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="player")
public class Player implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;
    
    @Column 
    public String name;
    
    @Column
    public int age;
    
    @Column 
    public int phone;
    
    @Column
    public int height;
    
    @Column
    public double weight;
    
    @Column (name="trainings_number")
    public double trainings;
    
    @Transient
    private double totalRank;
    
//    public double MOrank = totalRank/trainings;
    

    public Player(String name, int age, int phone, int height, double weight) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }

    public Player(String name) {
        this.name = name;
    }
    
    

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTrainings() {
        return trainings;
    }

    public void setTrainings(double trainings) {
        this.trainings = trainings;
    }

    public double getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(double x) {
        this.totalRank += x;
        
    }
    
    @Override
    public String toString() {
        return name + ", age: " + age + ", phone: " + phone + ", height: " + height + ", weight: " + weight + ", trainings: " + (int)trainings + ", MO rank: " +(totalRank/trainings);
    }

    
    
    
}//class
