/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author ASUS
 */
public class Puntuacion {
    
    private int score; 

    public Puntuacion() {
        this.score = 0; 
    }

   
    public void increase() {
        score += 10; 
    }

   
    public int getScore() {
        return score;
    }

   
    public void reset() {
        score = 0; 
    }
}
