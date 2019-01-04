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

  public static void main(String[] args){
    // Testing solve
    System.out.println(solve(1, 1, 2, 2));

    // Testing storeVals
    GraphingCalculator graph = new GraphingCalculator();
    System.out.println(graph);
  }

}
