
/** ****************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ***************************************************************************** */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

  private final int MAX_ITER = 570;
  private final double ZOOM = 200;
  private BufferedImage I;
  private double zx, zy, cX, cY, tmp;

  public Mandelbrot() {
    super("Mandelbrot Set");
    setBounds(100, 100, 800, 600);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < getHeight(); y++) {
      for (int x = 0; x < getWidth(); x++) {
        zx = zy = 0;
        cX = (x - 400) / ZOOM;
        cY = (y - 300) / ZOOM;
        int iter = MAX_ITER;
        while (zx * zx + zy * zy < 4 && iter > 0) {     
          //z=z^2+c
          //tmp = zx * zx - zy * zy + cX;
          //zy = 2.0 * zx * zy + cY;
          
          //z=z^3+c
          tmp = zx * zx * zx - 3 * zx * zy * zy + cX;
          zy = 3.0 * zx * zx * zy - Math.pow(zy, 3) + cY;
          
          //z=z^4+c
          //tmp = Math.pow((zx * zx + zy * zy), 2)*Math.cos(4*Math.atan2(zy, zx)) + cX;
          //zy = Math.pow((zx*zx+zy*zy),2)*Math.sin(4*Math.atan2(zy, zx)) + cY;
          
          //z=z^5+c
          //tmp = Math.pow(zx, 5)-10*Math.pow(zx, 3) * Math.pow(zy, 2)+5*zx*Math.pow(zy,4)+cX;
          //zy = 5 * Math.pow(zx, 4)*zy-10*Math.pow(zx, 2)*Math.pow(zy, 3)+Math.pow(zy, 5)+cY;
          
          zx = tmp;
          iter--;
        }
        I.setRGB(x, y, iter | (iter << 8));
      }
    }
  }

  @Override
  public void paint(Graphics g) {
    g.drawImage(I, 0, 0, this);
  }

  public static void main(String[] args) {
    new Mandelbrot().setVisible(true);
  }
}
