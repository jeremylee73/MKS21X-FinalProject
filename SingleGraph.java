import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SingleGraph {
  private boolean[][] graph;
  private double q1;
  private double x1;
  private int c1;
  private int height;
  private int width;

  public SingleGraph(int h, int w, double q, double m, int b) {
    height = h;
    width = w;
    graph = new boolean[height][width];
    q1 = q;
    x1 = m;
    c1 = b;
    storeVals(q1, x1, c1);
  }

  // Creates and displays image of graph1
  // Credit to https://stackoverflow.com/questions/11897297/constructing-image-from-2d-array-in-java
  public void display(){
    try {
      int[][] PixelArray;
      BufferedImage bufferimage=ImageIO.read(new File("graph.jpg"));
      int height2=bufferimage.getHeight();
      int width2=bufferimage.getWidth();
      PixelArray=new int[width2][height2];

      // Enters coordinates from graph and x and y axis.
      for(int i=1;i<width2;i++){
          for(int j=0;j<height2;j++){
              if (graph[i][j]){
                PixelArray[j][height2-i-1] = 0; // rgb value of black
              } else{
                PixelArray[j][height2-i-1]=16777215; // rgb value of whit
              }
              if (i == width2 / 2 || j == height2 / 2){
                PixelArray[j][height2-i-1]=0; // rgb value of black
              }
          }
      }
      BufferedImage bufferImage=new BufferedImage(height, width,BufferedImage.TYPE_INT_RGB);
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

  public double getX(){
    return x1;
  }

  public int getC(){
    return c1;
  }

  public void translateUpDown(int a) {
    c1 = (int) Math.round(c1 + a);
    storeVals(q1, x1, c1);
  }

  public void translateLeftRight(int a) {
    c1 = (int) Math.round((a*a)+(-x1*a)+c1);
    x1 = x1 + ((-2 * q1) * a);
    storeVals(q1, x1, c1);
  }

  public void dilateUpDown(int a) {
    q1 = q1 * a;
    x1 = x1 * a;
    c1 = c1 * a;
    storeVals(q1, x1, c1);
  }

  public void dilateLeftRight(int a) {
    x1 = x1 / a;
    q1 = q1 / (a*a);
    storeVals(q1, x1, c1);
  }

  public void rotate90CC(){
    for (int i=0; i<height; i++){
      for (int j=0; j<width; j++){
        int reformedJ = j - height / 2;
        int reformedI = i - width / 2;
        if ((-1 * reformedJ) == ((q1*reformedI*reformedI) + (x1 * reformedI) + c1)){
          graph[i][j] = true;
          //System.out.println("("+reformedJ+","+reformedI+")");
        } else {
          graph[i][j] = false;
        }
      }
    }
  }

  public void rotate90C(){
    for (int i=0; i<height; i++){
      for (int j=0; j<width; j++){
        int reformedJ = j - height / 2;
        int reformedI = i - width / 2;
        if (reformedJ == ((q1*reformedI*reformedI) + (-1 * x1 * reformedI) + c1)){
          graph[i][j] = true;
          //System.out.println("("+reformedJ+","+reformedI+")");
        } else {
          graph[i][j] = false;
        }
      }
    }
  }

  public void storeVals(double q, double x, int c){
    for (int i=0; i<height; i++){
      for (int j=0; j<width; j++){
        if ((i-height / 2) == ((q * (j-width / 2) * (j-width / 2)) + (x*(j- width / 2)) + c)){
          graph[i][j] = true;
        } else {
          //System.out.println("i: " + i + " j: " + j + " height: " + height + " width: " + width);
          graph[i][j] = false;
        }
      }
    }
  }

  public String toString(){
    String ans = "";
    for (int i=0; i<graph.length; i++){
      ans += "{";
      for (int j=0; j<graph[i].length; j++){
        if (graph[i][j]){
          ans += (i- height / 2) + "," + (j-width / 2) + "}";
        }
      }
      if (i != graph.length - 1){
        ans += ", ";
      } else {
        ans += "}";
      }
    }
    return ans;
  }
}
