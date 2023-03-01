
	import java.awt.Font;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;
	import org.newdawn.slick.Image;
	import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

	//correspond a l'interface 2

	public class Option_menu {
		int niveau;// n=0 si 4*4 ; n=1 si 8*8 ; n=2 si si 4*4 et les cartes bougent
		int ecart_y_menu;// ecart verticale entre les elts du menu
		int longueur_menu, largeur_menu;// largeur et longueur des boutons du menus deroulant
		
		Image imgmenu ;// contient l'image des  boutons du menu


		int nb_joueur;//contient le nombre de joueur
		
		int joueur;//contient le numero du joueur a qui c'est le tour de jouer
		

		Image img_affichee_0[] = new Image[33];// contient le l'image correspondante a chacune valeur de la grille
		
		String[] paque_img = new String[4];// correspond aux noms des 4 paquets d'image possible
		int paq_img;// correspond au numero du paque avec lequel on joue
		private int ecart_x_menu;
		
		Image fond;
		
		boolean ouvert[]=new boolean[3];//represente si on a cliquer sur changer de niveau, sur paque d'image, ou sur mode joueur
		
		



		Option_menu (int niveau, int nb_joueur, int paq_img) throws SlickException
		{

			//"Kristen ITC"
			//Font font = new Font("Verdana", Font.BOLD, 32);
			//TrueTypeFont ttf = new TrueTypeFont(font, true);

			this.nb_joueur=nb_joueur;
			// on rempli les tableaux d'images des cases de la grille

			paque_img[0] = "paysage";
			paque_img[1] = "fleur";
			paque_img[2] = "maison";
			paque_img[3] = "noel";
			this.paq_img = paq_img;

			for (int i = 0; i < 33; i++) {
				img_affichee_0[i] = new Image("image_affichee_0/" + paque_img[paq_img] + "/" + i + ".png");
			}
			ecart_y_menu = 100;
			ecart_x_menu = 25;
			longueur_menu = 150;
			largeur_menu = 400;
			

			imgmenu = new Image("b1.png");
			fond = new Image("fon5.png");
			
			this.niveau=niveau;


		}
		
		
		
		public int getNiveau() {
			return niveau;
		}



		public void setNiveau(int niveau) {
			this.niveau = niveau;
		}



		public int getNb_joueur() {
			return nb_joueur;
		}



		public void setNb_joueur(int nb_joueur) {
			this.nb_joueur = nb_joueur;
		}



		public int getJoueur() {
			return joueur;
		}



		public void setJoueur(int joueur) {
			this.joueur = joueur;
		}



		public String[] getPaque_img() {
			return paque_img;
		}



		public void setPaque_img(String[] paque_img) {
			this.paque_img = paque_img;
		}



		public int getPaq_img() {
			return paq_img;
		}



		public void setPaq_img(int paq_img) {
			this.paq_img = paq_img;
		}
		


		public Image[] getImg_affichee_0() {
			return img_affichee_0;
		}



		public void setImg_affichee_0(Image[] img_affichee_0) {
			this.img_affichee_0 = img_affichee_0;
		}



		int changerNiveau(int x, int y, int face)
		{
			int okay=0;
			
			
			if(x> this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>0 && y<this.longueur_menu)
			{
				if(ouvert[0]==true)
					ouvert[0]=false;
				else
				{
					ouvert[2]=false;
					ouvert[1]=false;
					ouvert[0]=true;
				}
			}
			
			if(ouvert[0]==true)
			{
				if(x>this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>this.longueur_menu && y<this.longueur_menu*2)
				{
					ouvert[0]=false;
					okay=1;
					this.niveau=0;
				}
				if(x>this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>this.longueur_menu*2 && y<this.longueur_menu*3)
				{
					ouvert[0]=false;
					okay=1;
					this.niveau=1;
				}
				if(x>this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>this.longueur_menu*3 && y<this.longueur_menu*4)
				{
					ouvert[0]=false;
					okay=1;
					this.niveau=2;
				}
				if(x>this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>this.longueur_menu*4 && y<this.longueur_menu*5)
				{
					ouvert[0]=false;
					okay=1;
					this.niveau=3;
				}
				
			}
			
			return okay;
		}
		
		int changerPaque(int x, int y, int face)
		{
			int okay=0;
			
			if(x> this.ecart_x_menu*2+this.largeur_menu && x<this.ecart_x_menu*2+this.largeur_menu+this.largeur_menu && y>0 && y<0+this.longueur_menu)
			{
				if(ouvert[1]==true)
					ouvert[1]=false;
				else
				{
					ouvert[2]=false;
					ouvert[1]=true;
					ouvert[0]=false;
				}
			}
			if(ouvert[1]==true)
			{
				if(x>this.ecart_x_menu*2+this.largeur_menu && x<this.ecart_x_menu*2+this.largeur_menu*2 && y>0+this.longueur_menu && y<0+this.longueur_menu*2)
				{
					ouvert[1]=false;
					okay=1;
					this.paq_img=0;
				}
				if(x>this.ecart_x_menu*2+this.largeur_menu && x<this.ecart_x_menu*2+this.largeur_menu*2 && y>0+this.longueur_menu*2 && y<0+this.longueur_menu*3)
				{
					ouvert[1]=false;
					okay=1;
					this.paq_img=1;
				}
				if(x>this.ecart_x_menu*2+this.largeur_menu && x<this.ecart_x_menu*2+this.largeur_menu*2 && y>0+this.longueur_menu*3 && y<0+this.longueur_menu*4)
				{
					ouvert[1]=false;
					okay=1;
					this.paq_img=2;
				}
				if(x>this.ecart_x_menu*2+this.largeur_menu && x<this.ecart_x_menu*2+this.largeur_menu*2 && y>0+this.longueur_menu*4 && y<0+this.longueur_menu*5)
				{
					ouvert[1]=false;
					okay=1;
					this.paq_img=3;
				}
				
			}
			return okay;
		}
		
		int changerMode(int x, int y, int face)
		{
			int okay=0;
			
			if(x>this.ecart_x_menu*3+this.largeur_menu*2 && x<this.ecart_x_menu*3+this.largeur_menu*2+this.largeur_menu && y>0 && y<0+this.longueur_menu)
			{
				if(ouvert[2]==true)
					ouvert[2]=false;
				else
				{
					ouvert[2]=true;
					ouvert[1]=false;
					ouvert[0]=false;
				}
					
			}
			if(ouvert[2]==true)
			{
				if(x>this.ecart_x_menu*3+this.largeur_menu*2 && x<this.ecart_x_menu*3+this.largeur_menu*3 && y>0+this.longueur_menu && y<this.longueur_menu*2)
				{
					ouvert[2]=false;
					okay=1;
					this.nb_joueur=1;
				}
				if(x>this.ecart_x_menu*3+this.largeur_menu*2 && x<this.ecart_x_menu*3+this.largeur_menu*3 && y>this.longueur_menu*2 && y<this.longueur_menu*3)
				{
					ouvert[2]=false;
					okay=1;
					this.nb_joueur=2;
				}
			}
			
			return okay;
		}
		
		int nouvellePartie(int x, int y, int face)
		{
			int okay=0;
			
			if(x>this.ecart_x_menu && x<this.ecart_x_menu+this.largeur_menu && y>Slick.longueur-this.longueur_menu-this.ecart_y_menu && y<Slick.longueur-this.longueur_menu-this.ecart_y_menu+this.longueur_menu)
			{
				okay=1;
			}
			
			return okay;
		}
		
		int play(int x, int y, int face)
		{
			//renvoit le numero de l'interface
			int okay=2;
			
			if(x>Slick.largeur -this.largeur_menu-this.ecart_x_menu && x<Slick.largeur -this.largeur_menu-this.ecart_x_menu+this.largeur_menu && y>Slick.longueur-this.longueur_menu-this.ecart_y_menu && y<Slick.longueur-this.longueur_menu-this.ecart_y_menu +this.longueur_menu)
			{
				okay=3;
			}
			
			return okay;
		}
		
		
		int[] changement(int x, int y, int face)
		{
			int[] tab=new int[2];
			tab[1]=face;
			//cette methode retourne 2 valeurs:
			//la premiere est soit 0, soit 1 et represente si oui il faut faire un basculement dans la classe jeu ie tout reinitialiser
			//la seconde est la nouvelle valeur de l'interface
			
			if(face==2)
			{

				//on gere le click sur changer niveau
				if(tab[0] == 1)
				{
					tab[0]=this.changerNiveau(x, y, face);
					tab[0]=1;
				}
				else
					tab[0]=this.changerNiveau(x, y, face);
				
				//on gere le click sur changer le paque d'image
				if(tab[0] == 1)
				{
					tab[0]=this.changerPaque(x, y, face);
					tab[0] = 1;
				}
				else
					tab[0]=this.changerPaque(x, y, face);
				
				//on gere le click sur changer le mode
				if(tab[0] == 1)
				{
					tab[0]=this.changerMode(x, y, face);
					tab[0]=1;
				}
				else
					tab[0]=this.changerMode(x, y, face);
				
				//on gere le click sur nouvelle partie
				if(tab[0] == 1)
				{
					tab[0]=this.nouvellePartie(x, y, face);
					tab[0]=1;
				}
				else
					tab[0]=this.nouvellePartie(x, y, face);
				
				//on gere le click sur le bouton play
				tab[1]=this.play(x, y, face);
				
			}
			
			
			return tab;

		}

		public void dessiner(Graphics g, int face, int largeur, int longueur)
		{
			
			if(face==2)
			{
				g.setColor(Color.white);
				g.drawImage(fond, 0, 0);
				g.drawImage(this.imgmenu, this.ecart_x_menu, 0);
				//ttf.drawString(32.0f, 32.0f, "Your words here", Color.green);
				g.drawString("CHANGER DE NIVEAU", this.ecart_x_menu+124, 0+44);
				g.drawString("CHANGER DE NIVEAU", this.ecart_x_menu+125, 0+44);
				g.drawString("CHANGER DE NIVEAU", this.ecart_x_menu+124, 0+45);
				g.setColor(Color.black);
				g.drawString(""+niveau, this.ecart_x_menu+199, 0+75);
				g.drawString(""+niveau, this.ecart_x_menu+199, 0+76);
				
				g.setColor(Color.white);
				g.drawImage(this.imgmenu, this.ecart_x_menu*2+this.largeur_menu,0);
				g.drawString("PAQUE D'IMAGE", this.ecart_x_menu*2+this.largeur_menu+144, 0+44);
				g.drawString("PAQUE D'IMAGE", this.ecart_x_menu*2+this.largeur_menu+145, 0+44);
				g.drawString("PAQUE D'IMAGE", this.ecart_x_menu*2+this.largeur_menu+144, 0+45);
				g.setColor(Color.black);
				g.drawString(this.paque_img[this.paq_img], this.ecart_x_menu*2+this.largeur_menu+174,0+74);
				g.drawString(this.paque_img[this.paq_img], this.ecart_x_menu*2+this.largeur_menu+174,0+75);
				
				g.setColor(Color.white);
				g.drawImage(this.imgmenu, this.ecart_x_menu*3+this.largeur_menu*2, 0);
				g.drawString("MODE JOUEUR", this.ecart_x_menu*3+this.largeur_menu*2+154, 0+44);
				g.drawString("MODE JOUEUR", this.ecart_x_menu*3+this.largeur_menu*2+155, 0+44);
				g.drawString("MODE JOUEUR", this.ecart_x_menu*3+this.largeur_menu*2+154, 0+45);
				g.setColor(Color.black);
				g.drawString(""+this.nb_joueur, this.ecart_x_menu*3+this.largeur_menu*2+199, 0+74);
				g.drawString(""+this.nb_joueur, this.ecart_x_menu*3+this.largeur_menu*2+199, 0+75);
				
				
				//on dessine les options des options ci-dessus
				if(ouvert[0]==true)
				{
					g.setColor(Color.white);
					g.drawImage(this.imgmenu, this.ecart_x_menu, 0+this.longueur_menu);
					g.drawString("0", this.ecart_x_menu+199, 0+this.longueur_menu+74);
					g.drawImage(this.imgmenu, this.ecart_x_menu, 0+this.longueur_menu*2);
					g.drawString("1", this.ecart_x_menu+199, 0+this.longueur_menu*2+74);
					g.drawImage(this.imgmenu, this.ecart_x_menu, 0+this.longueur_menu*3);
					g.drawString("2", this.ecart_x_menu+199, 0+this.longueur_menu*3+74);
					g.drawImage(this.imgmenu, this.ecart_x_menu, 0+this.longueur_menu*4);
					g.drawString("3", this.ecart_x_menu+199, 0+this.longueur_menu*4+74);
				}
				
				if(ouvert[1]==true)
				{
					g.setColor(Color.white);
					g.drawImage(this.imgmenu, this.ecart_x_menu*2+this.largeur_menu, 0+this.longueur_menu);
					g.drawString(this.paque_img[0], this.ecart_x_menu*2+this.largeur_menu+174, 0+this.longueur_menu+70);
					g.drawImage(this.imgmenu, this.ecart_x_menu*2+this.largeur_menu, 0+this.longueur_menu*2);
					g.drawString(this.paque_img[1], this.ecart_x_menu*2+this.largeur_menu+174, 0+this.longueur_menu*2+70);
					g.drawImage(this.imgmenu, this.ecart_x_menu*2+this.largeur_menu, 0+this.longueur_menu*3);
					g.drawString(this.paque_img[2], this.ecart_x_menu*2+this.largeur_menu+174, 0+this.longueur_menu*3+70);
					g.drawImage(this.imgmenu, this.ecart_x_menu*2+this.largeur_menu, 0+this.longueur_menu*4);
					g.drawString(this.paque_img[3], this.ecart_x_menu*2+this.largeur_menu+174, 0+this.longueur_menu*4+70);
				}
				
				if(ouvert[2]==true)
				{
					g.setColor(Color.white);
					g.drawImage(this.imgmenu, this.ecart_x_menu*3+this.largeur_menu*2, 0+this.longueur_menu);
					g.drawString(""+1, this.ecart_x_menu*3+this.largeur_menu*2+199, 0+this.longueur_menu+70);
					g.drawImage(this.imgmenu, this.ecart_x_menu*3+this.largeur_menu*2, 0+this.longueur_menu*2);
					g.drawString(""+2, this.ecart_x_menu*3+this.largeur_menu*2+199, 0+this.longueur_menu*2+70);

				}
				
				//on continue de dessiner les options
				
				g.setColor(Color.white);
				g.drawImage(this.imgmenu, this.ecart_x_menu, Slick.longueur-this.longueur_menu-this.ecart_y_menu);
				g.drawString("NOUVELLE PARTIE", this.ecart_x_menu+134, Slick.longueur-this.longueur_menu-this.ecart_y_menu+70);
				g.drawString("NOUVELLE PARTIE", this.ecart_x_menu+134, Slick.longueur-this.longueur_menu-this.ecart_y_menu+71);
				
				g.drawImage(this.imgmenu, Slick.largeur -this.largeur_menu-this.ecart_x_menu, Slick.longueur-this.longueur_menu-this.ecart_y_menu);
				g.drawString("PLAY", Slick.largeur -this.largeur_menu-this.ecart_x_menu+177, Slick.longueur-this.longueur_menu-this.ecart_y_menu+70);
				g.drawString("PLAY", Slick.largeur -this.largeur_menu-this.ecart_x_menu+177, Slick.longueur-this.longueur_menu-this.ecart_y_menu+71);
				
				
				
				
				
			}
			
		}
		
	}


