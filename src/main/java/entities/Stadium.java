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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author a.toutsios
 */


@NamedQueries ({
    @NamedQuery(name="Stadium.findAll", query = "SELECT s FROM Stadium s"),
    @NamedQuery(name = "Stadium.findFromName", query = "SELECT s FROM Stadium s WHERE s.name = :name")
})
@Entity
@Table (name="stadium")
public class Stadium implements Serializable {
    
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private int id;
    
    @Column
    public String name;
    
    @OneToMany (mappedBy = "gameStadium")
        private List<Game> games = new ArrayList<Game>();
    
    @OneToMany (mappedBy = "trainingStadium")
        private List<Training> trainings = new ArrayList<Training>();

    //CONSTRUCTOR
    public Stadium(String name) {
        this.name = name;
    }

    public Stadium() {
    }
    
    
    //GETTER-SETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
    
    @Override
    public String toString() {
        return "Όνομα: " + name ;
    }

    

}
