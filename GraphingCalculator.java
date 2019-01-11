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
    double x = 1.0;
    int c = 0;
    int q = 1;
    int h = 2001;
    int w = 2001;

    if (args.length > 0) {
      try{
        x = Double.parseDouble(args[0]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have a double for your first input");
      }
    }

    if (args.length > 1) {
      try{
        c = Integer.parseInt(args[1]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have an integer for your second input");
      }
    }


    /*if (args.length > 2) {                                                                     This section was meant to
      try{                                                                                       allow users to input values into
        h = Integer.parseInt(args[2]);                                                           the function to modify the dimensions
      }catch(NumberFormatException e) {                                                          of the graph. We ran into a couple of
        System.out.println("Make sure that you have an integer for your third input");           unexpected problems and will attempt to
      }                                                                                          do this later.
    }

    if (args.length > 3) {
      try{
        w = Integer.parseInt(args[3]);
      }catch(NumberFormatException e) {
        System.out.println("Make sure that you have an integer for your fourth input");
      }
    }*/
    // Testing storeVals
<<<<<<< HEAD
    SingleGraph output = new SingleGraph(2001,2001,q,x,c);
=======
    SingleGraph output = new SingleGraph(2001,2001,1.0,x,c);
>>>>>>> 13784cbfaff1a5a6a5d2b3a512137426d2da9131
    // System.out.println(graph);

    if (args.length > 2) {
      for (int i = 2;i < args.length;i += 1) {
        char a = args[i].charAt(0);
        if (a == 't') {
          if (args[i].charAt(1) == 'u') {
            output.translateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
          }
          if (args[i].charAt(1) == 'r') {
            output.translateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
          }
        }
        if (a == 'd') {
          if (args[i].charAt(1) == 'u') {
            output.dilateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
          }
          if (args[i].charAt(1) == 'r') {
            output.dilateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
          }
        }
      }
    }

    System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // // Testing dilates
    //output.dilateUpDown(5);
    //output.dilateLeftRight(5);

    // // Testing translates
    output.translateLeftRight(100);
    output.translateUpDown(100);



    System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // Testing display
    output.display();


  }
}
