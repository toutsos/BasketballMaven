/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findFromName", query = "SELECT p FROM Player p WHERE p.name = :name")
})
@Entity
@Table (name="player")
public class Player implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    public String name;
    public int age;
    public int phone;
    public int height;
    public double weight;
    @Column (name="trainings_number")
    public double trainings;
    @Transient
    private double totalRank;
    @OneToMany(mappedBy = "idplayer")
        private List<Training> playerTrainings = new ArrayList<Training>();
    
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Training> getPlayerTrainings() {
        return playerTrainings;
    }

    public void setPlayerTrainings(List<Training> playerTrainings) {
        this.playerTrainings = playerTrainings;
    }

    
    
    
}//class
