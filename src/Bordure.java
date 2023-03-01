import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bordure {
	int largeur_bordure, largeur_espace, longueur_espace;// largeur de la bordure qui encadre la grille de jeu,espace qui encadre la
	// grille de jeu
	
	Bordure(int niveau)
	{
		if (niveau == 0) {
			
			largeur_bordure = 50;
			largeur_espace = 400;
			longueur_espace=200;
		}
		if (niveau == 1) {
			largeur_bordure = 40;
			largeur_espace = 210;
			longueur_espace=10;
		}
		if (niveau == 2) {
			largeur_bordure = 50;
			largeur_espace = 400;
			longueur_espace=200;
		}
		if (niveau == 3) {
			largeur_bordure = 40;
			largeur_espace = 210;
			longueur_espace=10;
		}

	}

	public int getLargeur_bordure() {
		return largeur_bordure;
	}

	public void setLargeur_bordure(int largeur_bordure) {
		this.largeur_bordure = largeur_bordure;
	}

	public int getLargeur_espace() {
		return largeur_espace;
	}
	public int getLongueur_espace() {
		return longueur_espace;
	}

	public void setLargeur_espace(int largeur_espace) {
		this.largeur_espace = largeur_espace;
	}
	
	
	public void dessiner(Graphics g,int face, int niveau, int h_acceuil, int largeur_img)
	{
		if(face==3)
		{
			if (niveau == 0) 
			{
				g.setColor(Color.red);
				for (int i = 0; i < largeur_bordure; i++) {
					g.drawRect(i, h_acceuil + i, largeur_img * 4 + largeur_espace * 2 -  2*i + largeur_bordure * 2,
							largeur_img * 4 + longueur_espace * 2 - 2*i + largeur_bordure * 2);
				}
			} 
			else if (niveau == 1) 
			{
				g.setColor(Color.lightGray);
				for (int i = 0; i < largeur_bordure; i++) {
					g.drawRect(i, h_acceuil + i, largeur_img * 8 + largeur_espace * 2 - 2 * i + largeur_bordure * 2,
							largeur_img * 8 + longueur_espace * 2 - 2 * i + largeur_bordure * 2);
				}
			} 
			else if (niveau == 2) 
			{
				g.setColor(Color.red);
				for (int i = 0; i < largeur_bordure; i++) {
					g.drawRect(i, h_acceuil + i, largeur_img * 4 + largeur_espace * 2 - 2 * i + largeur_bordure * 2,
							largeur_img * 4 + longueur_espace * 2 - 2 * i + largeur_bordure * 2);
				}
			}
			else if (niveau == 3) 
			{
				g.setColor(Color.lightGray);
				for (int i = 0; i < largeur_bordure; i++) {
					g.drawRect(i, h_acceuil + i, largeur_img * 8 + largeur_espace * 2 - 2 * i + largeur_bordure * 2,
							largeur_img * 8 + longueur_espace * 2 - 2 * i + largeur_bordure * 2);
				}
			} 
		}

	}
	
}
