import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {

  // Takes in six inputs to solve q1(x^2) + x1(x) + c1 = q2(x^2) + x2(x) + c2
  public static double[] solve(double q1, double x1, int c1, double q2, double x2, int c2){
    if (q1 != q2){ // quadratic formula
      double dq = q2 - q1;
      double dx = x2 - x1;
      double dc = (c2 * 1.0) - c1;
      double[] ans = new double[2];
      ans[0] = ((-1*dx) + Math.sqrt((dx*dx)-(4*(dc*dq)))) / (2*dq);
      ans[1] = ((-1*dx) - Math.sqrt((dx*dx)-(4*(dc*dq)))) / (2*dq);
      return ans;
    } else { // linear formula
      double[] ans = new double[1];
      double newX = x2 - x1;
      double newC = (c1 * 1.0) - c2;
      ans[0] = newC / newX;
      return ans;
    }
  }

  // Finds the roots of q1(x^2) + x1(x) + c1 = 0
  public static String findRoots(double q1, double x1, int c1){
    double[] roots = solve(q1, x1, c1, 0, 0, 0);
    if (q1 == 0 || roots[0] == roots[1]){
      return "(" + roots[0] + ",0)";
    }
    return "(" + roots[0] + ",0);(" + roots[1] + ",0)";
  }

  public static void main(String[] args){
    double q = 0; // coefficient of x^2
    double x = 0; // coefficient of x
    int c = 0; // constant
    double q2 = 0;
    double x2 = 0;
    int c2 = 0;
    int h = 2001; // height of image & graph
    int w = 2001; // width of image & graph

    int i = 0;
    if (args.length > 0){
      int sign = 1; // determines positive or negative term
      boolean end = false;
      for (i=0; i<args.length && !end; i++){
        boolean presentX = false;
        boolean deg1 = false;
        boolean deg2 = false;
        char firstChar = args[i].charAt(0);
        if (firstChar != 't' && firstChar != 'd' && firstChar != 'r'){ // checks that the term is not a transformation
          // Checking for sign
          if (args[i].equals("+")){
            sign = 1;
          } else if (args[i].equals("-")){
            sign = -1;
          } else {
            for (int j=0; j<args[i].length(); j++){
              if (args[i].charAt(j) == 'x'){
                presentX = true; // term could be x or x^2
                if (j == args[i].length()-1){
                  deg1 = true; // term is x
                } else if ((j<args[i].length()-2) && (args[i].substring(j, j+3).equals("x^2"))){
                  deg2 = true; // term is x^2
                }
              }
            }

            if (!presentX){ // has to be a constant
              c = sign * Integer.parseInt(args[i]);
            } else if (deg1){
              if (args[i].length() != 1 && !(args[i].equals("-x"))){
                x = sign * Double.parseDouble(args[i].substring(0, args[i].length()-1)); // not x or -x
              } else if (args[i].length() == 1){ // has to be 1x
                x = 1;
              } else { // has to be -1x
                x = -1;
              }
            } else if (deg2){
              if (args[i].length() != 3 && !(args[i].equals("-x^2"))){
                q = sign * Double.parseDouble(args[i].substring(0, args[i].length()-3)); // not x^2 or -x^2
              } else if (args[i].length() == 3){ // has to be 1x^2
                q = 1;
              } else { // has to be -1x^2
                q = -1;
              }
            }
          }
        }
        if (i + 1< args.length && args[i + 1].equals("new")) {
          end = true;
        }
      }
    }

    if (i + 1 < args.length) {
      int sign2 = 1;
      for (i = i + 1;i < args.length;i += 1) {
        boolean presentX2 = false;
        boolean deg12 = false;
        boolean deg22 = false;
        char firstChar2 = args[i].charAt(0);
        if (firstChar2 != 't' && firstChar2 != 'd' && firstChar2 != 'r'){ // checks that the term is not a transformation
          // Checking for sign
          if (args[i].equals("+")){
            sign2 = 1;
          } else if (args[i].equals("-")){
            sign2 = -1;
          } else {
            for (int j=0; j<args[i].length(); j++){
              if (args[i].charAt(j) == 'x'){
                presentX2 = true; // term could be x or x^2
                if (j == args[i].length()-1){
                  deg12 = true; // term is x
                } else if ((j<args[i].length()-2) && (args[i].substring(j, j+3).equals("x^2"))){
                  deg22 = true; // term is x^2
                }
              }
            }

            if (!presentX2){ // has to be a constant
              c2 = sign2 * Integer.parseInt(args[i]);
            } else if (deg12){
              if (args[i].length() != 1 && !(args[i].equals("-x"))){
                x2 = sign2 * Double.parseDouble(args[i].substring(0, args[i].length()-1)); // not x or -x
              } else if (args[i].length() == 1){ // has to be 1x
                x2 = 1;
              } else { // has to be -1x
                x2 = -1;
              }
            } else if (deg22){
              if (args[i].length() != 3 && !(args[i].equals("-x^2"))){
                q2 = sign2 * Double.parseDouble(args[i].substring(0, args[i].length()-3)); // not x^2 or -x^2
              } else if (args[i].length() == 3){ // has to be 1x^2
                q2 = 1;
              } else { // has to be -1x^2
                q2 = -1;
              }
            }
          }
        }
      }
    }

    System.out.println(q);
    System.out.println(x);
    System.out.println(c);
    System.out.println(findRoots(q, x, c));
    System.out.println("------------------");
    System.out.println(q2);
    System.out.println(x2);
    System.out.println(c2);
    System.out.println(findRoots(q2, x2, c2));
    // Testing storeVals
    SingleGraph output = new SingleGraph(2001,2001,q,x,c);

    // Looking for transformations in input
    if (args.length > 0){
      for (i=0; i<args.length; i++){
        if (args[i].length() >= 2){
          char a = args[i].charAt(0);
          char b = args[i].charAt(1);
          if (a == 't') { // Translations
            if (b == 'u') { // translate up
              output.translateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
            }
            if (b == 'r') { // translate right
              output.translateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
            }
          }
          if (a == 'd') { // Dilations
            if (b == 'u') { // dilate up
              output.dilateUpDown(Integer.parseInt(args[i].substring(2,args[i].length())));
            }
            if (b == 'r') { // dilate right
              output.dilateLeftRight(Integer.parseInt(args[i].substring(2,args[i].length())));
            }
          }
          if (a == 'r'){ // Rotations
            if (args[i].length() >= 3){
              if (args[i].substring(1,3).equals("cc")){
                output.rotate90CC();
              }
            } else if (b == 'c'){
              output.rotate90C();
            }
          }
        }
      }
    }

    // Testing dilates
    //output.dilateUpDown(5);
    //output.dilateLeftRight(5);

    // Testing translates
    //output.translateLeftRight(100);
    //output.translateUpDown(100);

    //System.out.println("Root: (" + findRoots(output.getX(), output.getC()) + ",0)");

    // Testing Rotates
    //output.rotate90C();

    // Testing display
    output.display();
  }
}
