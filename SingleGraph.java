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
  private int numRotates = 0;

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
      int count = 0;
      int[][] coloredIndex = new int[PixelArray.length * 2][2];
      for(int i=1;i<width2;i++){
          for(int j=0;j<height2;j++){
              if (graph[i][j]){
                PixelArray[j][width2-i-1] = 0; // rgb value of black
                coloredIndex[count][0] = j;
                coloredIndex[count][1] = i;
                count++;
              }
          }
      }

      BufferedImage bufferImage=new BufferedImage(height, width,BufferedImage.TYPE_INT_RGB);
      for(int y=0;y<height2;y++){
          for(int x=0;x<width2;x++){
            bufferImage.setRGB(x,y,bufferimage.getRGB(x,y));
          }
      }

      int sidePara = numRotates % 2;
      for (int i=1; i<coloredIndex.length - 1; i++){
        if (q1==0 || sidePara == 1){ // Add test for horizontal also
          if (coloredIndex[i][0] < coloredIndex[i+1][0]){
            for (int pixel=coloredIndex[i][0]; pixel<coloredIndex[i+1][0]; pixel++){
              bufferImage.setRGB(pixel, width2-coloredIndex[i][1]-1, 0); // rgb value of black
            }
          }
          if (coloredIndex[i][0] > coloredIndex[i+1][0]){
            for (int pixel=coloredIndex[i+1][0]; pixel<coloredIndex[i][0]; pixel++){
              bufferImage.setRGB(pixel, width2-coloredIndex[i+1][1]-1, 0); // rgb value of black
            }
          }
        }
        if (coloredIndex[i][1] < coloredIndex[i+1][1]){
          for (int pixel=coloredIndex[i][1]; pixel<coloredIndex[i+1][1]; pixel++){
            bufferImage.setRGB(coloredIndex[i][0], width2-pixel-1, 0); // rgb value of black
          }
        }
        if (coloredIndex[i][1] > coloredIndex[i-1][1]){
          for (int pixel=coloredIndex[i][1]; pixel>coloredIndex[i-1][1]; pixel--){
            bufferImage.setRGB(coloredIndex[i][0], width2-pixel-1, 0); // rgb value of black
          }
        }
      }

      for (int y=0; y<height2; y++){
          for (int x = 0; x<width2; x++){
            if (graph[y][x]){
              bufferImage.setRGB(x,height2-y-1,0);
            }
          }
      }

      File outputfile = new File("graph.jpg");
      ImageIO.write(bufferImage, "jpg", outputfile);
    }
    catch(Exception ee){
      ee.printStackTrace();
    }
  }

  // Returns x
  public double getX(){
    return x1;
  }

  // Returns c
  public int getC(){
    return c1;
  }

  // Translates graph vertically by a units
  public void translateUpDown(int a) {
    c1 = (int) Math.round(c1 + a);
    storeVals(q1, x1, c1);
  }

  // Translates graph horizontally by a units
  public void translateLeftRight(int a) {
    c1 = (int) Math.round((q1*a*a)+(-x1*a)+c1);
    x1 = x1 + ((-2 * q1) * a);
    storeVals(q1, x1, c1);
  }

  // Dilates graph vertically by a factor of a
  public void dilateUpDown(int a) {
    q1 = q1 * a;
    x1 = x1 * a;
    c1 = c1 * a;
    storeVals(q1, x1, c1);
  }

  // Dilates graph horizontally by a factor of a
  public void dilateLeftRight(int a) {
    x1 = x1 / a;
    q1 = q1 / (a*a);
    storeVals(q1, x1, c1);
  }

  // Rotates graph 90 degrees clockwise
  public void rotate90C(){
    numRotates -= 1;
    for (int i=0; i<height; i++){
      for (int j=0; j<width; j++){
        graph[i][j] = false;
      }
    }
    for (int i=0; i<height; i++){
      int reformedI = i-1000;
      for (int j=-1; j<3; j+=2){
        int reformedJ = (int) Math.round((x1+(j*Math.sqrt((x1*x1)-(4*q1*c1)+(4*q1*reformedI))))/(2*q1));
        if (reformedJ >= -1000 && reformedJ <= 1000){
          graph[reformedJ+1000][i] = true;
        }
      }
    }
  }

  // Rotates graph 90 degrees counterclockwise
  public void rotate90CC(){
    numRotates += 1;
    for (int i=0; i<height; i++){
      for (int j=0; j<width; j++){
        graph[i][j] = false;
      }
    }
    for (int i=0; i<height; i++){
      int reformedI = i-1000;
      for (int j=-1; j<3; j+=2){
        int reformedJ = (int) Math.round((x1+(j*Math.sqrt((x1*x1)-(4*q1*c1)-(4*q1*reformedI))))/(-2*q1));
        if (reformedJ >= -1000 && reformedJ <= 1000){
          graph[reformedJ+1000][i] = true;
        }
      }
    }
  }

  // Stores values that satisfy y = q(x^2) + x(x) + c into graph
  public void storeVals(double q, double x, int c){
    for (int i=-1000; i<(height/2); i++){
      int yval = (int) Math.round((q*i*i) + (x*i) + c);
      if (yval < (height/2) && yval >= (-1 * height / 2)){
        graph[yval+1000][i+1000] = true;
      }
    }
  }

  // Prints all values that satisfy the equation
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
