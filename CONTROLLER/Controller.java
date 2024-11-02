/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import MODEL.Juego;
import GUI.GuiJuego;

public class Controller {
    private GuiJuego guiJuego;
    private Juego juego;
    private Timer timer;

    public Controller(GuiJuego guiJuego, Juego juego) {
        this.guiJuego = guiJuego;
        this.juego = juego;

        // Configurar el temporizador para actualizar el juego a intervalos regulares
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarJuego();
            }
        });

        // Agregar escucha de teclado para controlar la dirección de la serpiente
        guiJuego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                manejarTeclado(e);
            }
        });

        // Iniciar el juego
        iniciarJuego();
    }

    public void iniciarJuego() {
        juego.run();  // Llama a la lógica de inicio en la clase Juego
        guiJuego.setVisible(true);
        timer.start(); // Iniciar el temporizador para actualizar el juego
    }

    private void manejarTeclado(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                juego.getSnake().setDireccion(Direccion.UP);
                break;
            case KeyEvent.VK_DOWN:
                juego.getSnake().setDireccion(Direccion.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                juego.getSnake().setDireccion(Direccion.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                juego.getSnake().setDireccion(Direccion.RIGHT);
                break;
        }
    }

    private void actualizarJuego() {
        juego.updateGame(); // Actualiza el estado del juego

        // Verifica si el juego ha terminado
        if (!juego.isRunning()) {
            timer.stop();
            guiJuego.mostrarMensaje("Juego Terminado");
        } else {
            guiJuego.repaint(); // Redibuja el panel del juego
        }
    }

    public void detenerJuego() {
        timer.stop();
        juego.stop();
        guiJuego.mostrarMensaje("Juego Detenido");
    }
}
