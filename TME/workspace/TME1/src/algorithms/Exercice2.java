package algorithms;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;



public class Exercice2 {
	/* L'attribut ensembleDepart contient les points dans l'ensemble de départ */
	private ArrayList<Point> ensembleDepart;
	private ArrayList<Arc> listeSolution;
	private Tree2D tree;
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

	/* Retourne true si l'ajout de l'arc crée une cycle */
	private boolean isCreateCycle(Arc a)
	{
		if ((this.listeSolution == null)||(this.listeSolution.size() == 0))
		{
			return false;
		}
		boolean isLeftExtremite = false;
		boolean isRightExtremite = false;

		for (int i = 0;i<this.listeSolution.size();i++)
		{
			/* L'ajout d'un arc crée une cycle si et seulement si les deux extrémités de l'arc qu'on veut ajouter sont déjà présentes dans la liste de solution sous forme de un des deux extrémités de deux différentes arcs*/
			Arc sa = this.listeSolution.get(i);
			if ((isLeftExtremite == false) && (sa.isExtremite(a.getLeft())))
			{
				isLeftExtremite = true;
			}
			if ((isRightExtremite == false) && (sa.isExtremite(a.getRight())))
			{
				isRightExtremite = true;
			}
			if (isLeftExtremite && isRightExtremite)
			{
				break;
			}
		}
		return (isLeftExtremite && isRightExtremite);
	}

	/* La méthode retourne la l'ensemble de toutes les paire constitués de deux points de l'ensemble des points de départ */
	private ArrayList<Arc> aretesCandidates() {
		/* On contrôle d'abord si l'ensemble de départ est bien initialisé*/
		if ((this.ensembleDepart == null) || (this.ensembleDepart.size() == 0)) {
			/* Si ce n'est pas le cas on l'initialise */
			this.initaliseFromInputFile();
		}
		/* La liste des points res est la liste qu'on va retourner à la fin de cette méthode */
		ArrayList<Arc> res = new ArrayList<Arc>();
		/* On parcourt deux fois la liste des points de départ pour obtenir tous les paires des points. On va eviter les doublons de type (P1,P2) et (P2,P1) où P1 et P2 sont des ensemble des points départ*/
		for (int i = 0; i< this.ensembleDepart.size();i++)
		{
			for (int j=i+1;j<this.ensembleDepart.size();j++){
				res.add(new Arc(this.ensembleDepart.get(i), this.ensembleDepart.get(j)));
			}
		}
		return res;
	}

	private ArrayList<Arc> aretesCandidatesTries() {
		ArrayList<Arc> ac = this.aretesCandidates();
		ac.sort((o1,o2) -> ((o1.poid()-o2.poid())<0)?-1:((o1.poid()-o2.poid())==0)?0:1);
		return ac;
	}

	/* Méthode permet de transformer la liste de solution à une liste des points. */
	private ArrayList<Point> listeDesPoints() {
		ArrayList<Point> res = new ArrayList<Point>();
		for (int i = 0; i < this.listeSolution.size(); i++)
		{
			/* On parcourt la liste des solutions.*/
			Arc a = this.listeSolution.get(i);
			Point el = a.getLeft();
			Point er = a.getRight();
			/* On extrait les deux extrémités de l'arc pour ensuite contrôler s'il existe déjà dans la liste resultat */
			if (res.contains(el) == false)
			{
				/* Si la liste des points de contient pas le point d'extrémité gauche on l'ajout à la liste*/
				res.add(el);
			}
			if (res.contains(er) == false)
			{
				res.add(er);
			}
		}
		return res;
	}

	/* Retourn une liste des arcs à partir d'une liste des arcs et d'un point tel que le point est l'un des deux extrémités de l'arc*/
	private ArrayList<Arc> lesArcsDunPoint(Point p)
	{
		ArrayList<Arc> res = new ArrayList<Arc>();
		for (int i = 0;i<this.listeSolution.size();i++)
		{
			if (this.listeSolution.get(i).isExtremite(p))
			{
				res.add(this.listeSolution.get(i));
			}
		}
		return res;
	}
	
	private ArrayList<Point> convertListeDesArcALaListeDesPointsSaufLeRoot(Point root ,ArrayList<Arc> la)
	{
		ArrayList<Point> res = new ArrayList<Point>();
		for (int i = 0; i<la.size(); i++)
		{
			Arc a = la.get(i);
			Point l = a.getLeft();
			Point r = a.getRight();
			if ((l.equals(root) == false) && (res.contains(l) == false))
			{
				res.add(l);
			}
			if ((r.equals(root) == false) && (res.contains(r) == false))
			{
				res.add(r);
			}
		}
		return res;
	}


	public Exercice2() {
		this.initaliseFromInputFile();
		ArrayList<Arc> ac = this.aretesCandidatesTries();
		this.listeSolution = new ArrayList<Arc>();
		for (int i = 0; i<ac.size(); i++) {
			if (this.listeDesPoints().size() == this.ensembleDepart.size())
				break;
			{
			}
			if (this.isCreateCycle(ac.get(i))==false)
			{
				this.listeSolution.add(ac.get(i));
			}
		}
		/* Pour transformer la liste des arcs sous forme d'un arbre on choisit une racine aléatoire depuis la liste des points */
		Random random = new Random();
		int index = random.nextInt(this.ensembleDepart.size());
		Point root = this.ensembleDepart.get(index);
		this.tree = new Tree2D(root, new ArrayList<Tree2D>());

		ArrayList<Arc> la = this.lesArcsDunPoint(root);
		ArrayList<Point> ap = this.convertListeDesArcALaListeDesPointsSaufLeRoot(root, la);
		
		while (this.ensembleDepart.size()>0)
		{
			la = this.lesArcsDunPoint(root);
			ap = this.convertListeDesArcALaListeDesPointsSaufLeRoot(root, la);
			this.ensembleDepart.remove(root);
			for (int i=0;i<ap.size();i++)
			{
				/* On parcout la liste des points dont la racine est en relation*/
				root = ap.get(i);
				tree.addSubtree(transformeEnTree(root));
				this.ensembleDepart.remove(root);
			}
		}
	}
	
	private Tree2D transformeEnTree(Point p)
	{
		Tree2D res = new Tree2D(p, new ArrayList<Tree2D>());
		
		return res;
	}

	public static void main(String[] args) {
		Exercice2 e2 = new Exercice2();
	}

}
