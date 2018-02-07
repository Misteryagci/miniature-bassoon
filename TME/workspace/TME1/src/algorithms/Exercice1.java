package algorithms;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
public class Exercice1 {
	private ArrayList<Point> ensembleDepart;
	
	/* 
	 * Permet d'initialiser la liste des points de depart depuis le fichier input.points donné dans le TME
	 * Retourne le nombre des points crées. 
	*/
	private int initaliseFromInputFile() {
		int cpt = 0;
		this.ensembleDepart = new ArrayList<Point>();
		try (BufferedReader br = new BufferedReader(new FileReader("input.points"))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] lineTable = sCurrentLine.split(" ");
				Point p = new Point(Integer.parseInt(lineTable[0]),Integer.parseInt(lineTable[1]));
				this.ensembleDepart.add(p);
				cpt++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cpt;
	}
	
	public Exercice1() {
		this.initaliseFromInputFile();
	}
	
	private ArrayList<Point> getEnsembleDeDepart() {
		return this.ensembleDepart;
	}
	
	private boolean removeFromEnsembleDeDepart(Point p) {
		return this.ensembleDepart.remove(p);
	}
	
	/* Permet de sélectionner un point aléatoire depuis l'ensemble des points de départ */
	private Point selectRandomPoint() {
		if ((this.ensembleDepart == null)||(this.ensembleDepart.size() == 0))
		{
			return null;
		}
		else 
		{
			int max = this.getEnsembleDeDepart().size();
			int min = 0;
			Random random = new Random();
			int index = random.nextInt(max - min + 1) + min;
			return this.ensembleDepart.get(index);
		}
	}
	
	private ArrayList<Double> distances(Point p)
	{
		ArrayList<Double> res = new ArrayList<Double>();
		for (int i = 0; i<this.ensembleDepart.size();i++)
		{
			/* On parcourt la liste des points de départ */
			res.add(this.ensembleDepart.get(i).distance(p));
		}
		return res;
	}
	
	/* La méthode retourne le point le plus proche au point passé en paramètres*/
	private Point selectPlusProche(Point p) {
		ArrayList<Double> list = this.distances(p);
		int minIndex = list.indexOf(Collections.min(list));
		return this.ensembleDepart.get(minIndex);
	}
	
	public ArrayList<Point> arbreCouvrant() {
		/* On initialise la liste de points visité à une liste vide */
		ArrayList<Point> listeDePointsVisite = new ArrayList<Point>();
		/* On commence par sélectionner un point de départ aléatoire */
		Point pd = this.selectRandomPoint();
		/* Nous supprimons ce point depuis l'ensemble des points de départ */
		this.removeFromEnsembleDeDepart(pd);
		/* On ajout le point pd à la liste des points visite */
		listeDePointsVisite.add(pd);
		while(this.getEnsembleDeDepart().size()>0) {
			/* On récupère le point le plus proche au point pd depuis l'ensemble des points de départ*/
			pd = this.selectPlusProche(pd);
			this.removeFromEnsembleDeDepart(pd);
			/* On ajout le point pd à la liste des points visite */
			listeDePointsVisite.add(pd);
		}
		return listeDePointsVisite;
	}

	
	public static void main(String[] args) {
		Exercice1 e = new Exercice1();
		System.out.println(e.arbreCouvrant().size());
	}
}
