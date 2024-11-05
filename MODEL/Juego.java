/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import GUIS.FrmHome;
import GUIS.GuiJuego1;
import java.awt.Point;
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
    FrmHome g = new FrmHome();
    private int velocidad; //Variable para controlar la velocidad

    public Juego(GuiJuego1 guijuego) {
        this.guijuego = guijuego;
        this.snake = new Snake();
        this.comida = new Comida(guijuego.getWidth() / GuiJuego1.CELL_SIZE, guijuego.getHeight() / GuiJuego1.CELL_SIZE);
        this.puntuacion = new Puntuacion();
        this.running = true;
        this.velocidad = 125; // Velocidad inicial 
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
        if (velocidad > 50) { // Asegúrate de que la velocidad no sea demasiado baja
            velocidad -= 10; // Reduce el tiempo de espera para aumentar la velocidad
        }
    }

    public void checkCollisions() {
        Point head = snake.getCuerpo().get(0);

      
        if (head.x < 0 || head.x >= guijuego.getWidth() / guijuego.CELL_SIZE ||
            head.y < 0 || head.y >= guijuego.getHeight() / guijuego.CELL_SIZE) {
            running = false; 
            JOptionPane.showMessageDialog(guijuego, "HAS CHOCADO!");
            guijuego.setVisible(false);
            g.setVisible(true);
        }

      
        for (Point segment : snake.getCuerpo()) {
            if (segment != head && segment.equals(head)) {
                running = false; 
                JOptionPane.showMessageDialog(guijuego, "TE HAS MORDIDO!");
                guijuego.setVisible(false);    
                g.setVisible(true);
                
            }
        }
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
}
