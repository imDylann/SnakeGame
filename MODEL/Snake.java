/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Saliim
 */


import java.util.List;

public class Snake {
   private List<Point> cuerpo;
    private Direccion direccion;

    public Snake() {
        cuerpo = new ArrayList<>();
        direccion = Direccion.RIGHT;

        for (int i = 0; i < 3; i++) {
            cuerpo.add(new Point(3 - i - 1, 0));
        }
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

        cuerpo.add(0, newHead);

        moverCuerpoRecursivamente(cuerpo.size() - 1);
    }

    private void moverCuerpoRecursivamente(int index) {
        if (index <= 1) return;
        cuerpo.get(index).setLocation(cuerpo.get(index - 1));
        moverCuerpoRecursivamente(index - 1); 
    }

    public void grow() {
        Point tail = cuerpo.get(cuerpo.size() - 1);
        cuerpo.add(new Point(tail));
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

enum Direccion {
    UP, DOWN, LEFT, RIGHT
}