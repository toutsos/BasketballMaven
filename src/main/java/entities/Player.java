/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Player {
    public String name;
    public int age;
    public int phone;
    public int height;
    public double weight;
    public double trainings;
    public double totalRank;
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
