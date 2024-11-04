/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIS;

import MODEL.Direccion;
import MODEL.Juego;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author Saliim
 */



public class GuiJuego1 extends javax.swing.JFrame {

    public static final int CELL_SIZE = 20;
    private Juego juego;

    public GuiJuego1() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);
        juego = new Juego(this);
        new Thread(juego).start();
        jPanel1.setFocusable(true);
        jPanel1.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game");
        setSize(new java.awt.Dimension(400, 400));

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {
        switch (evt.getKeyCode()) {
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

    private void drawGame(Graphics g) {
        g.clearRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());

        g.setColor(Color.GREEN);
        for (Point segment : juego.getSnake().getCuerpo()) {
            g.fillRect(segment.x * CELL_SIZE, segment.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        Point comidaPos = juego.getComida().getPosition();
        g.setColor(Color.RED);
        g.fillRect(comidaPos.x * CELL_SIZE, comidaPos.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void updateGame() {
        jPanel1.repaint();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GuiJuego1().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private JPanel jPanel1;
    // End of variables declaration                   
}
 

