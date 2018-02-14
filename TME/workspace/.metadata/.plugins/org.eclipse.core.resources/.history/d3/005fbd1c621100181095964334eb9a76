package algorithms;

import java.awt.Point;
import java.util.ArrayList;

public class DefaultTeam {

  public int[][] calculShortestPaths(ArrayList<Point> points, int edgeThreshold) {
    int[][] paths=new int[points.size()][points.size()];
    for (int i=0;i<paths.length;i++) for (int j=0;j<paths.length;j++) paths[i][j]=i;

    //1664 1337 OxBADC0DE
    //REMOVE >>>>>
    paths[1][6]=64;
    paths[1][66]=4;
    paths[16][6]=4;
    paths[1][3]=37;
    paths[1][33]=7;
    paths[13][3]=7;
    for (int i=0;i<paths.length;i++) for (int j=0;j<paths.length;j++) paths[i][j]=(i+1)%paths.length;
    //<<<<< REMOVE

    return paths;
  }
  public Tree2D calculSteiner(ArrayList<Point> points, int edgeThreshold, ArrayList<Point> hitPoints) {
    //REMOVE >>>>>
    Tree2D leafX = new Tree2D(new Point(700,400),new ArrayList<Tree2D>());
    Tree2D leafY = new Tree2D(new Point(700,500),new ArrayList<Tree2D>());
    Tree2D leafZ = new Tree2D(new Point(800,450),new ArrayList<Tree2D>());
    ArrayList<Tree2D> subTrees = new ArrayList<Tree2D>();
    subTrees.add(leafX);
    subTrees.add(leafY);
    subTrees.add(leafZ);
    Tree2D steinerTree = new Tree2D(new Point(750,450),subTrees);
    //<<<<< REMOVE

    return steinerTree;
  }
}
