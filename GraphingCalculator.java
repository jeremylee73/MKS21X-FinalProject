import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GraphingCalculator {
  private boolean[][] graph1;
  private boolean[][] graph2;

  public GraphingCalculator(){
    graph1 = new boolean[21][21];
    storeVals(1, 0, graph1);
  }

  public static void storeVals(int x, int c, boolean[][] graph){
    for (int i=0; i<=20; i++){
      for (int j=0; j<=20; j++){
        if ((i-10) == (x*(j-10)) + c){
          graph[i][j] = true;
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

      for(int i=0;i<width;i++){
          for(int j=0;j<height;j++){
              PixelArray[i][j]=bufferimage.getRGB(i, j);
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

  public static void main(String[] args){
    // Testing solve
    System.out.println(solve(1, 1, 2, 2));

    // Testing storeVals
    GraphingCalculator graph = new GraphingCalculator();
    System.out.println(graph);

    // Testing display
    display();
  }

}
