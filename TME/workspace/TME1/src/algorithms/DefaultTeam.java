package algorithms;

import java.awt.Point;
import java.util.ArrayList;

public class DefaultTeam {
  public Tree2D calculSteiner(ArrayList<Point> points) {
    /*******************
     * PARTIE A EDITER *
     *******************/
    Tree2D leafX = new Tree2D(new Point(700,400),new ArrayList<Tree2D>());
    Tree2D leafY = new Tree2D(new Point(700,500),new ArrayList<Tree2D>());
    Tree2D leafZ = new Tree2D(new Point(800,450),new ArrayList<Tree2D>());

    ArrayList<Tree2D> subTrees = new ArrayList<Tree2D>();
    subTrees.add(leafX);
    subTrees.add(leafY);
    subTrees.add(leafZ);
    Tree2D myResult = new Tree2D(new Point(750,450),subTrees);

    return myResult;
  }
}
