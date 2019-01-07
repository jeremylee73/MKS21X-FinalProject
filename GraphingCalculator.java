import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {
  private static boolean[][] graph1;
  private static double x1;
  private static int c1;
  private static boolean[][] graph2;

  public GraphingCalculator(){
    graph1 = new boolean[2001][2001];
    x1 = 2.0;
    c1 = 100;
    storeVals(x1, c1, graph1);
  }

  public static void storeVals(double x, int c, boolean[][] graph){
    for (int i=0; i<=2000; i++){
      for (int j=0; j<=2000; j++){
        if ((i-1000) == (x*(j-1000)) + c){
          graph[i][j] = true;
        } else {
          graph[i][j] = false;
        }
      }
    }
  }

  public static double solve(double x1, double c1, double x2, double c2){
    double newX = x2 - x1;
    double newC = c1 - c2;
    return (newC * 1.0) / newX;
  }

  public String toString(){
    String ans = "";
    for (int i=0; i<graph1.length; i++){
      ans += "{";
      for (int j=0; j<graph1[i].length; j++){
        if (graph1[i][j]){
          ans += (i-10) + "," + (j-10) + "}";
        }
      }
      if (i != graph1.length - 1){
        ans += ", ";
      } else {
        ans += "}";
      }
    }
    return ans;
  }

  // Creates and displays image of graph1
  // Credit to https://stackoverflow.com/questions/11897297/constructing-image-from-2d-array-in-java
  public static void display(){
    try {
      int[][] PixelArray;
      BufferedImage bufferimage=ImageIO.read(new File("graph.jpg"));
      int height=bufferimage.getHeight();
      int width=bufferimage.getWidth();
      PixelArray=new int[width][height];

      // Enters coordinates from graph and x and y axis.
      for(int i=0;i<width;i++){
          for(int j=0;j<height;j++){
              if (graph1[i][j]){
                PixelArray[j][height-i-1]=0; // rgb value of black
              } else{
                PixelArray[j][height-i-1]=16777215; // rgb value of white
              }
              if (i == width / 2 || j == height / 2){
                PixelArray[j][height-i-1]=0; // rgb value of black
              }
          }
      }
      BufferedImage bufferImage=new BufferedImage(height, width,BufferedImage.TYPE_INT_RGB);

      for(int y=0;y<height;y++){
          for(int x=0;x<width;x++){
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

  public static void translateUpDown(int a, boolean[][] data) {
    c1 = (int) Math.round(c1 + a);
    storeVals(x1, c1, graph1);
  }

  public static void translateLeftRight(int a, boolean[][] data) {
    c1 = (int) Math.round(c1 - (x1 * a));
    storeVals(x1, c1, graph1);
  }

  public static void dilateUpDown(int a, boolean[][] PixelArray) {
    x1 = x1 * a;
    c1 = c1 * a;
    storeVals(x1, c1, graph1);
  }

  public static void dilateLeftRight(int a, boolean[][] PixelArray) {
    x1 = x1 / a;
    c1 = c1 / a;
    storeVals(x1, c1, graph1);
  }

  public static void main(String[] args){
    // Testing solve
    System.out.println(solve(1, 1, 2, 2));

    // Testing storeVals
    GraphingCalculator graph = new GraphingCalculator();
    // System.out.println(graph);

    // Testing dilates
    dilateUpDown(5, graph1);
    dilateLeftRight(5, graph1);

    // Testing translates
    translateLeftRight(100, graph1);
    translateUpDown(500, graph1);

    // Testing display
    display();


  }

  public static void translateUpDown(int a, boolean[][] PixelArray) {
  boolean[][] copy = new boolean[PixelArray.length - a][PixelArray[0].length];
  for (int i = a;i < PixelArray.length;i ++) {
    for (int j = 0;j < PixelArray.length;j ++) {
      if (PixelArray[i][j]) {
        copy[i - a][j] = true;
      }
      else {
        copy[i - a][j] = false;
      }
    }
  }
  PixelArray = copy;
}
public static void translateLeftRight(int a, boolean[][] PixelArray) {
  boolean[][] copy = new boolean[PixelArray.length][PixelArray[0].length - a];
  for (int i = 0;i < PixelArray.length;i ++) {
    for (int j = a;j < PixelArray.length;j ++) {
      if (PixelArray[i][j]) {
        copy[i][j - a] = true;
      }
      else {
        copy[i][j - a] = false;
      }
    }
  }
  PixelArray = copy;
}
public static void dilateUpDown(int a, boolean[][] PixelArray) {
  boolean[][] copy = new boolean[PixelArray.length * a][PixelArray[0].length];
  for (int i = 0;i < PixelArray.length * a;i ++) {
    for (int j = 0;j < PixelArray.length;j ++) {
      if (PixelArray[i][j]) {
        copy[i + (i - PixelArray.length / 2) / Math.abs(i - PixelArray.length / 2) * a][j] = true;
      }
      else {
        copy[i + (i - PixelArray.length / 2) / Math.abs(i - PixelArray.length / 2) * a][j] = false;
      }
    }
  }
  PixelArray = copy;
}
public static void dilateLeftRight(int a, boolean[][] PixelArray) {
  boolean[][] copy = new boolean[PixelArray.length][PixelArray[0].length * a];
  for (int i = 0;i < PixelArray.length;i ++) {
    for (int j = 0;j < PixelArray.length * a;j ++) {
      if (PixelArray[i][j]) {
        copy[i][j + (j - PixelArray.length / 2) / Math.abs(j - PixelArray.length / 2) * a] = true;
      }
      else {
        copy[i][j + (j - PixelArray.length / 2) / Math.abs(j - PixelArray.length / 2) * a] = false;
      }
    }
  }
  PixelArray = copy;
}

  public static void translateUpDown(int a, boolean[][] PixelArray) {
    boolean[][] copy = new boolean[PixelArray.length - a][PixelArray[0].length];
    for (int i = a;i < PixelArray.length;i ++) {
      for (int j = 0;j < PixelArray.length;j ++) {
        if (PixelArray[i][j]) {
          copy[i - a][j] = true;
        }
        else {
          copy[i - a][j] = false;
        }
      }
    }
    PixelArray = copy;
  }

  public static void translateLeftRight(int a, boolean[][] PixelArray) {
    boolean[][] copy = new boolean[PixelArray.length][PixelArray[0].length - a];
    for (int i = 0;i < PixelArray.length;i ++) {
      for (int j = a;j < PixelArray.length;j ++) {
        if (PixelArray[i][j]) {
          copy[i][j - a] = true;
        }
        else {
          copy[i][j - a] = false;
        }
      }
    }
    PixelArray = copy;
  }

  public static void dilateUpDown(int a, boolean[][] PixelArray) {
    boolean[][] copy = new boolean[PixelArray.length * a][PixelArray[0].length];
    for (int i = 0;i < PixelArray.length * a;i ++) {
      for (int j = 0;j < PixelArray.length;j ++) {
        if (PixelArray[i][j]) {
          copy[i + (i - PixelArray.length / 2) / Math.abs(i - PixelArray.length / 2) * a][j] = true;
        }
        else {
          copy[i + (i - PixelArray.length / 2) / Math.abs(i - PixelArray.length / 2) * a][j] = false;
        }
      }
    }
    PixelArray = copy;
  }

  public static void dilateLeftRight(int a, boolean[][] PixelArray) {
    boolean[][] copy = new boolean[PixelArray.length][PixelArray[0].length * a];
    for (int i = 0;i < PixelArray.length;i ++) {
      for (int j = 0;j < PixelArray.length * a;j ++) {
        if (PixelArray[i][j]) {
          copy[i][j + (j - PixelArray.length / 2) / Math.abs(j - PixelArray.length / 2) * a] = true;
        }
        else {
          copy[i][j + (j - PixelArray.length / 2) / Math.abs(j - PixelArray.length / 2) * a] = false;
        }
      }
    }
    PixelArray = copy;
  }

}
