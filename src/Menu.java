import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//correspond a l'interface 1

public class Menu {
	
	int ecart_y_menu;// ecart verticale entre les elts du menu
	int longueur_menu, largeur_menu;// largeur et longueur des boutons du menus deroulant
	
	Image imgmenu ;// contient l'image des  boutons du menu
	
	Image fond;
	



	Menu () throws SlickException
	{

		ecart_y_menu = 100;
		longueur_menu = 150;
		largeur_menu = 400;
		

		imgmenu = new Image("b1.png");
		fond = new Image("fon4.png");


	}
	
	
	
	int changement(int x, int y, int largeur, int longueur, int face)
	{
		if(face==1)
		{
			//si on clique sur play sur par sur l'interface 3 et donc dans le jeu
			
			if(x>largeur/2-this.largeur_menu/2 && x<largeur/2-this.largeur_menu/2+this.largeur_menu && y> longueur/2 && y<longueur/2+this.longueur_menu)
				return 3;
			
			//si on clique sur parametre ca part a l'interface 2 et donc dans option_menu
			if(x>largeur/2-this.largeur_menu/2 && x<largeur/2-this.largeur_menu/2+this.largeur_menu && y> longueur/2+this.ecart_y_menu+this.longueur_menu && y<longueur/2+this.longueur_menu*2+this.ecart_y_menu)
				return 2;
		}
		
		return face;
	}

	public void dessiner(Graphics g, int face, int largeur, int longueur)
	{
		if(face==1)
		{
			g.setColor(Color.white);
			g.drawImage(fond, 0, 0);
			g.drawImage(this.imgmenu, largeur/2-this.largeur_menu/2, longueur/2);
			g.drawString("PLAY", largeur/2-this.largeur_menu/2+184, longueur/2+74);
			g.drawString("PLAY", largeur/2-this.largeur_menu/2+185, longueur/2+74);
			g.drawString("PLAY", largeur/2-this.largeur_menu/2+184, longueur/2+75);
			g.drawImage(this.imgmenu, largeur/2-this.largeur_menu/2, longueur/2+this.ecart_y_menu+this.longueur_menu);
			g.drawString("PARAMETRES", largeur/2-this.largeur_menu/2+164, longueur/2+this.ecart_y_menu+this.longueur_menu+74);
			g.drawString("PARAMETRES", largeur/2-this.largeur_menu/2+165, longueur/2+this.ecart_y_menu+this.longueur_menu+74);
			g.drawString("PARAMETRES", largeur/2-this.largeur_menu/2+164, longueur/2+this.ecart_y_menu+this.longueur_menu+75);
		}
	}
	
}


