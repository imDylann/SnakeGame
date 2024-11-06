/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIS;

import MODEL.Direccion;
import MODEL.Juego;
import MODEL.Obstaculos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Saliim
 */



public class GuiJuego1 extends javax.swing.JFrame {
public static final int CELL_SIZE = 20;
    private Juego juego;
    private JPanel jPanel1;
    private ArrayList<Obstaculos> obstaculos; // List to store obstacles
   // private BackgroundFrame backgroundframe;

    public GuiJuego1() {
        initComponents();
      //   backgroundframe = new BackgroundFrame(); // Crear el fondo
       // backgroundframe.setVisible(true); // Mostrar el fondo
       // setContentPane(backgroundframe.getContentPane()); 
        
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        juego = new Juego(this);
        obstaculos = new ArrayList<>();
        
        generarObstaculos(20);
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
        setTitle("Snake Game");
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

    // Method to generate random obstacles
    private void generarObstaculos(int cantidad) {
         Random rand = new Random();
    for (int i = 0; i < cantidad; i++) {
        int x = rand.nextInt(getWidth() / CELL_SIZE) * CELL_SIZE;
        int y = rand.nextInt(getHeight() / CELL_SIZE) * CELL_SIZE;
        Point posicion = new Point(x, y);
        
        // Adjust to match available constructor
        obstaculos.add(new Obstaculos(30,30,posicion));
  }
    }

    private void drawGame(Graphics g) {
        g.clearRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());

        // Draw the snake
        g.setColor(Color.GREEN);
        for (Point segment : juego.getSnake().getCuerpo()) {
            g.fillRect(segment.x * CELL_SIZE, segment.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        // Draw the food
        Point comidaPos = juego.getComida().getPosition();
        g.setColor(Color.RED);
        g.fillRect(comidaPos.x * CELL_SIZE, comidaPos.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw the obstacles
        g.setColor(Color.BLUE);
        for (Obstaculos obstaculo : obstaculos) {
            obstaculo.drawObstacle(g);
        }

        // Draw the score
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Score: " + juego.getPuntuacion().getScore(), 10, 20);
    }
    
    public void updateGame() {
        jPanel1.repaint();
    }

    public ArrayList<Obstaculos> getObstaculos() {
        return obstaculos;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new GuiJuego1().setVisible(true));
    }
    }
 
