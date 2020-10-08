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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author a.toutsios
 */
@Entity
@Table (name="stadium")
@NamedQueries ({
    @NamedQuery(name="Stadium.findAll", query = "SELECT s FROM Stadium s"),
    @NamedQuery(name = "Stadium.findFromName", query = "SELECT s FROM Stadium p WHERE s.name = :name")
})
public class Stadium implements Serializable {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    
    @Column
    private String name;

    //CONSTRUCTOR
    public Stadium(String name) {
        this.name = name;
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
    
    

}
