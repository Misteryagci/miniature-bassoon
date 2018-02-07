package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {

  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une pair de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point p=points.get(0);
    Point q=points.get(1);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return new Line(p,q);
  }

  // calculDiametreOptimise: ArrayList<Point> --> Line
  //   renvoie une pair de points de la liste, de distance maximum.
  public Line calculDiametreOptimise(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point p=points.get(1);
    Point q=points.get(2);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return new Line(p,q);
  }

  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
    if (points.isEmpty()) {
      return null;
    }

    Point center=points.get(0);
    int radius=100;

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return new Circle(center,radius);
  }
  public double surface(Point p0,Point p1,Point p2) {
  	return (
			(
			 (p1.getX() - p0.getX()) *
			 (p2.getY() - p0.getY())
			) -
			(
			 (p2.getX() - p0.getX()) * 
			 (p1.getY() - p0.getY())
			) 
		   )/2);
  }
  public boolean interieur(Point p0,Point p1,Point p2,Point p) {
  	return (
			(Math.signum(surface(p0,p,p1) == Math.signum(surface(p1,p,p2)) &&
			(Math.signum(surface(p0,p,p1) == Math.signum(p2,p,p0))		    
			);
  }
  // enveloppeConvexe: ArrayList<Point> --> ArrayList<Point>
  //   renvoie l'enveloppe convexe de la liste.
  public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points){
	// un point est sur l'enveloppe convexe s'il n'est pas à l'intérieur
	// d'aucun triangle formé par les autres points
	ArrayList<Point> nonEnveloppe  = new ArrayList<Point>();    
	ArrayList<Point> enveloppe = new ArrayList<Point>();
	
	for (int i = 0;i<points.size();i++) {
		for (int j = i+1;j<points.size();j++){
			for (int k = j+1;k<points.size();k++) {
				for (int l=0;l<points.size();l++) {
					if (('l != i) && (l != j) && (l != k)) {
						if (interieur(points,i,j,k,l) { 
							//le point l est-il à l'intérieur du triangle (i,j,k)
							nonEnveloppe.add(points.get(i));
						}
					} 
				}
			}
		}	
	}
	for (int i = 0 ; i < points.size() ; i++)
	{
		enveloppe.add(points.get(i));
	}
	enveloppe.removeAll(nonEnveloppe);
	return enveloppe;	
	/*if (points.size()<3) {
      return null;
    }

    ArrayList<Point> enveloppe = new ArrayList<Point>();

    enveloppe.add(points.get(0));
    enveloppe.add(points.get(1));
    enveloppe.add(points.get(2));

    return points; */
  }

}
