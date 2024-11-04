/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> cuerpo;
    private Direccion direccion;
    private boolean hasEaten; 

    public Snake() {
        cuerpo = new ArrayList<>();
        direccion = Direccion.RIGHT;

        for (int i = 0; i < 3; i++) {
            cuerpo.add(new Point(3 - i - 1, 0));
        }
        hasEaten = false; 
    }

    public void move() {
        Point head = cuerpo.get(0); 
        Point newHead = new Point(head);

        switch (direccion) {
            case UP: newHead.translate(0, -1); break;
            case DOWN: newHead.translate(0, 1); break;
            case LEFT: newHead.translate(-1, 0); break;
            case RIGHT: newHead.translate(1, 0); break;
        }

        if (hasEaten) {
            cuerpo.add(0, newHead); 
            hasEaten = false; 
        } else {
            cuerpo.add(0, newHead); 
            cuerpo.remove(cuerpo.size() - 1);
        }

        moverCuerpoRecursivamente(cuerpo.size() - 1);
    }

    private void moverCuerpoRecursivamente(int index) {
        if (index <= 1) return;
        cuerpo.get(index).setLocation(cuerpo.get(index - 1));
        moverCuerpoRecursivamente(index - 1); 
    }

    public void grow() {
        hasEaten = true; 
    }

    public void setDireccion(Direccion newDireccion) {
        if (newDireccion == Direccion.UP && direccion != Direccion.DOWN) {
            direccion = newDireccion;
        } else if (newDireccion == Direccion.DOWN && direccion != Direccion.UP) {
            direccion = newDireccion;
        } else if (newDireccion == Direccion.LEFT && direccion != Direccion.RIGHT) {
            direccion = newDireccion;
        } else if (newDireccion == Direccion.RIGHT && direccion != Direccion.LEFT) {
            direccion = newDireccion;
        }
    }

    public List<Point> getCuerpo() {
        return cuerpo;
    }
}


