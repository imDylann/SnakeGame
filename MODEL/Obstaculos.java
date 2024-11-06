/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import GUIS.GuiJuego1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author jdarg
 */
public class Obstaculos {
    private int x, y;
    private Color color = Color.DARK_GRAY;  // Color distintivo del obstáculo
    public static final int CELL_SIZE = GuiJuego1.CELL_SIZE; // Accede al tamaño de celda

    // Constructor para inicializar el obstáculo en una posición dada
    public Obstaculos(int x, int y) {
        this.x = x;
        this.y = y;
    }

   
    public Obstaculos(int maxX, int maxY, Point snakeStart) {
        Random rand = new Random();
        do {
            this.x = rand.nextInt(maxX);
            this.y = rand.nextInt(maxY);
        } while (this.x == snakeStart.x && this.y == snakeStart.y);  
    }

   
    public void drawObstacle(Graphics g) {
        g.setColor(color);
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public boolean checkCollision(Point snakeHead) {
        return snakeHead.x == x && snakeHead.y == y;
    }
    
    public Point getPosition() {
        return new Point(x, y);
    }
    
}
