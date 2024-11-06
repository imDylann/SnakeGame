/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import GUIS.FrmHome;
import GUIS.GuiJuego1;
import static GUIS.GuiJuego1.CELL_SIZE;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Saliim
 */

public class Juego implements Runnable {
    private Snake snake;
    private Comida comida;
    private Puntuacion puntuacion;
    private GuiJuego1 guijuego;
    private boolean running;
    private ArrayList<Obstaculos> obstaculos;
    FrmHome g = new FrmHome();
    private int velocidad; //Variable para controlar la velocidad

    public Juego(GuiJuego1 guijuego) {
        this.guijuego = guijuego;
        this.snake = new Snake();
        this.comida = new Comida(guijuego.getWidth() / GuiJuego1.CELL_SIZE, guijuego.getHeight() / GuiJuego1.CELL_SIZE);
        this.puntuacion = new Puntuacion();
        this.running = true;
        this.velocidad = 125; // Velocidad inicial 
        this.obstaculos = new ArrayList<>();
        generarObstaculos(10);  // Generar obstáculos
    }

    public void run() {
        while (running) {
            updateGame();

            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            guijuego.repaint();
        }
    }

    public void updateGame() {
        snake.move();

       
        if (snake.getCuerpo().get(0).equals(comida.getPosition())) {
            snake.grow();
            puntuacion.increase();
            comida.spawn();
            aumentarVelocidad(); 
        }

       
        checkCollisions();
    }

    private void aumentarVelocidad() {
        if (velocidad > 50) { 
            velocidad -= 10; 
        }
    }
        public ArrayList <Obstaculos> generarObstaculos(int cantidad) {
         Random rand = new Random();
    for (int i = 0; i < cantidad; i++) {
        int x = rand.nextInt(guijuego.getWidth() / CELL_SIZE) * CELL_SIZE;
        int y = rand.nextInt(guijuego.getHeight() / CELL_SIZE) * CELL_SIZE;
        Point posicion = new Point(x, y);
        
        // Adjust to match available constructor
        obstaculos.add(new Obstaculos(30,30,posicion));
  }
    return obstaculos;
    }

   public void checkCollisions() {
    Point head = snake.getCuerpo().get(0);

    // Check wall collisions
    if (head.x < 0 || head.x >= guijuego.getWidth() / GuiJuego1.CELL_SIZE ||
        head.y < 0 || head.y >= guijuego.getHeight() / GuiJuego1.CELL_SIZE) {
        running = false;
        JOptionPane.showMessageDialog(guijuego, "HAS CHOCADO CON LA PARED!");
        endGame();
        return;
    }

    // Check self-collision
    for (int i = 1; i < snake.getCuerpo().size(); i++) {
        if (snake.getCuerpo().get(i).equals(head)) {
            running = false;
            JOptionPane.showMessageDialog(guijuego, "TE HAS MORDIDO!");
            endGame();
            return;
        }
    }

    // Check collision with obstacles
    for (Obstaculos obstaculo : obstaculos) {
        if (head.equals(obstaculo.getPosition())) {
            running = false;
            JOptionPane.showMessageDialog(guijuego, "¡CHOCASTE CON UN OBSTÁCULO!");
            endGame();
            return;
        }
    }
}

private void endGame() {
    guijuego.setVisible(false);
    g.setVisible(true); // Shows the main menu or home screen
}

    public void stop() {
        running = false;
    }

    public Snake getSnake() {
        return snake;
    }

    public Comida getComida() {
        return comida;
    }

    public boolean isRunning() {
        return running;
    }


    public Puntuacion getPuntuacion() {
        return puntuacion;
    }
      public ArrayList<Obstaculos> getObstaculos() {
        return obstaculos;
    }
}
