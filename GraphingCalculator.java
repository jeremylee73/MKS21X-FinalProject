import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {

  public static double solve(double x1, int c1, double x2, int c2){
    double newX = x2 - x1;
    double newC = c1 - c2;
    return (newC * 1.0) / newX;
  }

  public static double findRoots(double x1, int c1){
    return solve(x1, c1, 0, 0);
  }

  public static void main(String[] args){
    // Testing solve
    System.out.println(solve(1, 1, 2, 2));

    // Testing storeVals
    SingleGraph output = new SingleGraph(2001,2001,2.0,100);
    // System.out.println(graph);

    System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // // Testing dilates
    output.dilateUpDown(5);
    output.dilateLeftRight(5);

    // // Testing translates
    output.translateLeftRight(100);
    output.translateUpDown(500);

    // Testing rotate90C
    output.rotate90C();
    output.rotate90C();
    output.rotate90CC();

    System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // Testing display
    output.display();


  }
}
