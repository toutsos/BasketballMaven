/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author a.toutsios
 */
public class Stadium {
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

}
