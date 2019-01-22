import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Scanner;

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
      if (ans[0] == -0){
        ans[0] = 0;
      }
      if (ans[1] == -0){
        ans[1] = 0;
      }
      return ans;
    } else { // linear formula
      double[] ans = new double[1];
      double newX = x2 - x1;
      double newC = (c1 * 1.0) - c2;
      ans[0] = newC / newX;
      if (ans[0] == -0){
        ans[0] = 0;
      }
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

    Scanner reader = new Scanner(System.in);  // Reading from System.in
    System.out.println("Enter mode: ");
    System.out.println("1. One Graph");
    System.out.println("2. Two Graphs");
    String mode = reader.nextLine(); // Scans the next token of the input as an int.
    while (!mode.equals("1") && !mode.equals("2")){
      System.out.println("Please enter 1 or 2.");
      mode = reader.nextLine();
    }

    System.out.println("Enter your first expression: ");
    String equation1 = reader.nextLine();
    System.out.println("Transformations?");
    String transformations1 = reader.nextLine();

    if (mode.equals("2")){
      System.out.println("Enter your second expression: ");
      String equation2 = reader.nextLine();
      String[] terms2 = new String[equation2.length()];
      terms2 = equation2.split(" ");
      System.out.println("Transformations?");
      String transformations2 = reader.nextLine();

      // Finding coefficients of terms of second equation
      int sign2 = 1;
      for (int i = 0;i < terms2.length;i += 1) {
        boolean presentX2 = false;
        boolean deg12 = false;
        boolean deg22 = false;
        char firstChar2 = terms2[i].charAt(0);
        if (firstChar2 != 't' && firstChar2 != 'd' && firstChar2 != 'r'){ // checks that the term is not a transformation
          // Checking for sign
          if (terms2[i].equals("+")){
            sign2 = 1;
          } else if (terms2[i].equals("-")){
            sign2 = -1;
          } else {
            for (int j=0; j<terms2[i].length(); j++){
              if (terms2[i].charAt(j) == 'x'){
                presentX2 = true; // term could be x or x^2
                if (j == terms2[i].length()-1){
                  deg12 = true; // term is x
                } else if ((j<terms2[i].length()-2) && (terms2[i].substring(j, j+3).equals("x^2"))){
                  deg22 = true; // term is x^2
                }
              }
            }

            if (!presentX2){ // has to be a constant
              try{
                c2 = sign2 * Integer.parseInt(terms2[i]);
              }
              catch (NumberFormatException e){
                System.out.println("Bad constant");
                System.exit(0);
              }
            } else if (deg12){
                if (terms2[i].length() != 1 && !(terms2[i].equals("-x"))){
                  try {
                    x2 = sign2 * Double.parseDouble(terms2[i].substring(0, terms2[i].length()-1)); // not x or -x
                  }
                  catch (NumberFormatException e) {
                    System.out.println("Coefficients must be numerical.");
                    System.exit(0);
                  }
                } else if (terms2[i].length() == 1){ // has to be 1x
                    x2 = 1;
                } else { // has to be -1x
                  x2 = -1;
                }
          } else if (deg22){
              if (terms2[i].length() != 3 && !(terms2[i].equals("-x^2"))){
                try {
                  q2 = sign2 * Double.parseDouble(terms2[i].substring(0, terms2[i].length()-3)); // not x^2 or -x^2
                }
                catch (NumberFormatException e) {
                  System.out.println("Coefficients must be numerical.");
                  System.exit(0);
                }
              } else if (terms2[i].length() == 3){ // has to be 1x^2
                q2 = 1;
              } else { // has to be -1x^2
                q2 = -1;
              }
            }
          }
        }
      }

      SingleGraph output2 = new SingleGraph(2001,2001,q2,x2,c2);
      // Looking for transformations in input1
      String[] transform2 = new String[transformations2.length()];
      transform2 = transformations2.split(" ");

      for (int i=0; i<transform2.length; i++){
        if (transform2[i].length() >= 2){
          char a = transform2[i].charAt(0);
          char b = transform2[i].charAt(1);
          while ((a != 't' && a != 'd' && a != 'r') || (b != 'u' && b != 'r' && b != 'c')) {
              System.out.println("Invalid transformation input. Enter again:");
              transformations2 = reader.nextLine();
              transform2 = new String[transformations2.length()];
              transform2 = transformations2.split(" ");
              a = transform2[i].charAt(0);
              b = transform2[i].charAt(1);
          }
          if (a == 't') { // Translations
            if (b == 'u') { // translate up
              output2.translateUpDown(Integer.parseInt(transform2[i].substring(2,transform2[i].length())));
            }
            if (b == 'r') { // translate right
              output2.translateLeftRight(Integer.parseInt(transform2[i].substring(2,transform2[i].length())));
            }
          }
          if (a == 'd') { // Dilations
            if (b == 'u') { // dilate up
              output2.dilateUpDown(Double.parseDouble(transform2[i].substring(2,transform2[i].length())));
            }
            if (b == 'r') { // dilate right
              output2.dilateLeftRight(Double.parseDouble(transform2[i].substring(2,transform2[i].length())));
            }
          }
        }
      }
      for (int i=0; i<transform2.length; i++){
        if (transform2[i].length() >= 2){
          char a = transform2[i].charAt(0);
          char b = transform2[i].charAt(1);
          if (a == 'r'){ // Rotations
            if (transform2[i].length() >= 3){
              if (transform2[i].substring(1,3).equals("cc")){
                output2.rotate90CC();
              }
            } else if (b == 'c'){
              output2.rotate90C();
            }
          }
        }
      }

      clear();
      output2.display();
    }



    String[] terms1 = new String[equation1.length()];
    terms1 = equation1.split(" ");

    int i = 0;
    if (terms1.length > 0){
      int sign = 1; // determines positive or negative term
      boolean end = false;
      for (i=0; i<terms1.length && !end; i++){
        boolean presentX = false;
        boolean deg1 = false;
        boolean deg2 = false;
        char firstChar = terms1[i].charAt(0);
        if (firstChar != 't' && firstChar != 'd' && firstChar != 'r'){ // checks that the term is not a transformation
          // Checking for sign
          if (terms1[i].equals("+")){
            sign = 1;
          } else if (terms1[i].equals("-")){
            sign = -1;
          } else {
            for (int j=0; j<terms1[i].length(); j++){
              if (terms1[i].charAt(j) == 'x'){
                presentX = true; // term could be x or x^2
                if (j == terms1[i].length()-1){
                  deg1 = true; // term is x
                } else if ((j<terms1[i].length()-2) && (terms1[i].substring(j, j+3).equals("x^2"))){
                  deg2 = true; // term is x^2
                }
              }
            }

            if (!presentX){ // has to be a constant
              try {
                c = sign * Integer.parseInt(terms1[i]);
              }
              catch (NumberFormatException e){
                System.out.println("Bad constant");
                System.exit(0);
              }
            } else if (deg1){
                if (terms1[i].length() != 1 && !(terms1[i].equals("-x"))){
                  try {
                    x = sign * Double.parseDouble(terms1[i].substring(0, terms1[i].length()-1)); // not x or -x
                  }
                  catch (NumberFormatException e) {
                    System.out.println("Coefficients must be numerical.");
                    System.exit(0);
                  }
                } else if (terms1[i].length() == 1){ // has to be 1x
                    x = 1;
                } else { // has to be -1x
                  x = -1;
                }
          } else if (deg2){
              if (terms1[i].length() != 3 && !(terms1[i].equals("-x^2"))){
                try {
                  q = sign * Double.parseDouble(terms1[i].substring(0, terms1[i].length()-3)); // not x^2 or -x^2
                }
                catch (NumberFormatException e) {
                  System.out.println("Coefficients must be numerical.");
                  System.exit(0);
                }
              } else if (terms1[i].length() == 3){ // has to be 1x^2
                q = 1;
              } else { // has to be -1x^2
                q = -1;
              }
            }
          }
        }
      }
    }

    SingleGraph output = new SingleGraph(2001,2001,q,x,c);

    // Looking for transformations in input1
    String[] transform1 = new String[transformations1.length()];
    transform1 = transformations1.split(" ");

    for (i=0; i<transform1.length; i++){
      if (transform1[i].length() >= 2){
        char a = transform1[i].charAt(0);
        char b = transform1[i].charAt(1);
        while ((a != 't' && a != 'd' && a != 'r') || (b != 'u' && b != 'r' && b != 'c')) {
            System.out.println("Invalid transformation input. Enter again:");
            transformations1 = reader.nextLine();
            transform1 = new String[transformations1.length()];
            transform1 = transformations1.split(" ");
            a = transform1[i].charAt(0);
            b = transform1[i].charAt(1);
        }
        if (a == 't') { // Translations
          if (b == 'u') { // translate up
            output.translateUpDown(Integer.parseInt(transform1[i].substring(2,transform1[i].length())));
          }
          if (b == 'r') { // translate right
            output.translateLeftRight(Integer.parseInt(transform1[i].substring(2,transform1[i].length())));
          }
        }
        if (a == 'd') { // Dilations
          if (b == 'u') { // dilate up
            output.dilateUpDown(Double.parseDouble(transform1[i].substring(2,transform1[i].length())));
          }
          if (b == 'r') { // dilate right
            output.dilateLeftRight(Double.parseDouble(transform1[i].substring(2,transform1[i].length())));
          }
        }
      }
    }
    for (i=0; i<transform1.length; i++){
      if (transform1[i].length() >= 2){
        char a = transform1[i].charAt(0);
        char b = transform1[i].charAt(1);
        if (a == 'r'){ // Rotations
          if (transform1[i].length() >= 3){
            if (transform1[i].substring(1,3).equals("cc")){
              output.rotate90CC();
            }
          } else if (b == 'c'){
            output.rotate90C();
          }
        }
      }
    }

    reader.close();

    System.out.println("a: " + q);
    System.out.println("b: " + x);
    System.out.println("c: " + c);
    System.out.println("Root(s): " + findRoots(q, x, c));
    if (mode.equals("2")){
      System.out.println("------------------");
      System.out.println("a: " + q2);
      System.out.println("b: " + x2);
      System.out.println("c: " + c2);
      System.out.println("Root(s): " + findRoots(q2, x2, c2));

      double[] solvedValues = solve(q, x, c, q2, x2, c2);
      for (int valI=0; valI<solvedValues.length; valI++){
        double xval = solvedValues[valI];
        double yval = (q*xval*xval) + (x * xval) + c;
        System.out.println("Intercept: ("+xval+", "+yval+")");
      }
    }

    if (mode.equals("1")){
      clear();
    }
    output.display();
  }

  // Clears the graph.jpg
  public static void clear() {
    try {
      int[][] PixelArray;
      BufferedImage bufferimage=ImageIO.read(new File("graph.jpg"));
      int height2=bufferimage.getHeight();
      int width2=bufferimage.getWidth();
      PixelArray=new int[width2][height2];

      for(int i=1;i<width2;i++){
        for(int j=0;j<height2;j++){
           PixelArray[j][width2-i-1]=16777215; // rgb value of white
           if (i == width2 / 2 || j == height2 / 2){
             PixelArray[j][width2-i-1]=0; // rgb value of black
           }
         }
      }
      BufferedImage bufferImage=new BufferedImage(2001, 2001,BufferedImage.TYPE_INT_RGB);
      for(int y=0;y<height2;y++){
          for(int x=0;x<width2;x++){
              bufferImage.setRGB(x, y, PixelArray[x][y]);
          }
      }

      File outputfile = new File("graph.jpg");
      ImageIO.write(bufferImage, "jpg", outputfile);
    }
    catch(Exception ee){
      ee.printStackTrace();
    }
  }

}
