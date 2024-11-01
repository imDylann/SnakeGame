
package MODEL;

import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Saliim
 */
public class Comida {
     private Point position;
    private final int ANCHO ; 
    private final int ALTO ;
    private Random random;

    public Comida (int ancho, int alto) {
        this.ANCHO = ancho;
        this.ALTO = alto;
        random = new Random();
        spawn(); 
    }

    public void spawn() {
        int x = random.nextInt(ANCHO);
        int y = random.nextInt(ALTO);
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
