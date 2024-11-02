/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.awt.Point;

/**
 *
 * @author Saliim
 */

   public class Juego implements Runnable {
    private Snake snake;         
    private Comida comida;       
    private Puntuacion puntuacion; 
    private GuiJuego guijuego;  // Frame del juego, hay que crearla y configurar sus metodos,observar los que se usan en esta clase para                             
    private boolean running;    //crearlos logicamente y que funcione esta clase

    public Juego(GuiJuego guijuego) {
        this.guijuego = guijuego;
        this.snake = new Snake();
        this.comida = new Comida(guijuego.getWidth() / guijuego.Panel.CELL_SIZE, guijuego.getHeight() / guijuego.CELL_SIZE);
        this.puntuacion = new Puntuacion();
        this.running = true; 
    }

    public void run() {
        while (running) {
            updateGame();

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            guijuego.repaint(); // Actualizar el panel del juego
        }
    }

    public void updateGame() {
        snake.move(); 

       
        if (snake.getCuerpo().get(0).equals(comida.getPosition())) {
            snake.grow(); 
            puntuacion.increase(); 
            comida.spawn(); 
        }

       
        checkCollisions();
    }

    public void checkCollisions() {
        Point head = snake.getCuerpo().get(0);

      
        if (head.x < 0 || head.x >= guijuego.getWidth() / guijuego.CELL_SIZE ||
            head.y < 0 || head.y >= guijuego.getHeight() / guijuego.CELL_SIZE) {
            running = false; 
        }

      
        for (Point segment : snake.getCuerpo()) {
            if (segment != head && segment.equals(head)) {
                running = false; 
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
