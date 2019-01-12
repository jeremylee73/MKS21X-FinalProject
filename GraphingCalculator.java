import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {

  public static double[] solve(double q1, double x1, int c1, double q2, double x2, int c2){
    double dq = q2 - q1;
    double dx = x2 - x1;
    double dc = (c2 * 1.0) - c1;
    double[] ans = new double[2];
    ans[0] = ((-1*dx) + Math.sqrt((dx*dx)-(4*(dc*dq)))) / (2*dq);
    ans[1] = ((-1*dx) - Math.sqrt((dx*dx)-(4*(dc*dq)))) / (2*dq);
    return ans;
  }

  public static String findRoots(double q1, double x1, int c1){
    double[] roots = solve(q1, x1, c1, 0, 0, 0);
    if (roots[0] == roots[1]){
      return "(" + roots[0] + ",0)";
    }
    return "(" + roots[0] + ",0);(" + roots[1] + ",0)";
  }

  public static void main(String[] args){
    // Testing solve
    System.out.println(findRoots(1, 6, -16)); // Should return -8 and 2
    double x = 0;
    int c = 0;
    double q = 0;
    int h = 2001;
    int w = 2001;

    if (args.length > 0){
      int sign = 1;
      for (int i=0; i<args.length; i++){
          boolean presentX = false;
          boolean deg1 = false;
          boolean deg2 = false;
          if (!(args[i].contains("du") || args[i].contains("dr") || args[i].contains("tu") || args[i].contains("tr"))){
            if (args[i].equals("+")){
              sign = 1;
            } else if (args[i].equals("-")){
              sign = -1;
            } else {
              for (int j=0; j<args[i].length(); j++){
                if (args[i].charAt(j) == 'x'){
                  presentX = true;
                  if (j == args[i].length()-1){
                    deg1 = true;
                  } else if ((j<args[i].length()-2) && (args[i].substring(j, j+3).equals("x^2"))){
                    deg2 = true;
                  }
                }
              }

              if (!presentX){
                c = sign * Integer.parseInt(args[i]);
              } else if (deg1){
                x = sign * Double.parseDouble(args[i].substring(0, args[i].length()-1));
              } else if (deg2){
                q = sign * Double.parseDouble(args[i].substring(0, args[i].length()-3));
              }
            }
          }
        }
      }

      System.out.println(q);
      System.out.println(x);
      System.out.println(c);
      System.out.println(findRoots(q, x, c));

    // if (args.length > 0) {
    //   try{
    //     x = Double.parseDouble(args[0]);
    //   }catch(NumberFormatException e) {
    //     System.out.println("Make sure that you have a double for your first input");
    //   }
    // }
    //
    // if (args.length > 1) {
    //   try{
    //     c = Integer.parseInt(args[1]);
    //   }catch(NumberFormatException e) {
    //     System.out.println("Make sure that you have an integer for your second input");
    //   }
    // }


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
    SingleGraph output = new SingleGraph(2001,2001,q,x,c);
    // System.out.println(graph);

    if (args.length > 0){
      for (int i=0; i<args.length; i++){
          if (args[i].length() >= 2){
            char a = args[i].charAt(0);
            char b = args[i].charAt(1);
            if (a == 't') {
              if (b == 'u') {
                output.translateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
              }
              if (b == 'r') {
                output.translateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
              }
            }
            if (a == 'd') {
              if (b == 'u') {
                output.dilateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
              }
              if (b == 'r') {
                output.dilateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
              }
            }
          }
        }
      }

    //System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // // Testing dilates
    //output.dilateUpDown(5);
    //output.dilateLeftRight(5);

    // // Testing translates
    //output.translateLeftRight(100);
    //output.translateUpDown(100);

    //System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // Testing Rotates
    //output.rotate90C();

    // Testing display
    output.display();
  }
}
