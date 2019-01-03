public class GraphingCalculator {
  private int[][] graph1;
  private int[][] graph2;

  public GraphingCalculator(){

  }

  // public void storeVals(int x, int c, int[][] graph){
  //   for (int i=-10; i<=10; i++){
  //     for (int j=-10; j<=10; j++){
  //       if (i == (x*j) + c){
  //         graph[j] =
  //       }
  //     }
  //   }
  // }

  public static double solve(double x1, double c1, double x2, double c2){
    double newX = x2 - x1;
    double newC = c1 - c2;
    return (newC * 1.0) / newX;
  }

  public static void main(String[] args){
    System.out.println(solve(1, 1, 2, 2));
  }

}
