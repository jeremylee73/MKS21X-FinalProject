import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {


  public static double solve(double x1, double c1, double x2, double c2){
    double newX = x2 - x1;
    double newC = c1 - c2;
    return (newC * 1.0) / newX;
  }




  public static void main(String[] args){
    // Testing solve
    //System.out.println(solve(1, 1, 2, 2));

    if (args.length >= 1) {
      try{
        double x = Double.parseDouble(args[1]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have a double for your first input");
      }
    }

    if (args.length >= 2) {
      try{
        int x = Integer.parseInt(args[2]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have an integer for your second input");
      }
    }

    if (args.length >= 3) {
      try{
        int x = Integer.parseInt(args[3]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have an integer for your third input");
      }
    }

    if (args.length >= 4) {
      try{
        int x = Integer.parseInt(args[4]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have an integer for your fourth input");
      }
    }

    // Testing storeVals
    singlegraph output = new singlegraph(2001,2001,2.0,1);
    // System.out.println(graph);

    // Testing dilates
    output.dilateUpDown(5);
    output.dilateLeftRight(5);

    // Testing translates
    output.translateLeftRight(100);
    output.translateUpDown(500);

    // Testing display
    output.display();


  }
}
