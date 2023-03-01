import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Entete {
	
	int h_acceuil;// hauteur du message de d'entete
	int acceuil;// largeur du bouton de retour a l'acceuil

	int entete; // vaut 0 si l'utilisateur a gagner la partie et on doit afficher le message de
				// felicitation, 1 sinon
	Image img;//  img contient l'image de bienvenue
	
	int delais;//temps de passage de l'entete 0 a l'entete 1
	
	int ecart_entete;//represente l'ecart entre les elts de l'entete lorsque le jeu est en cours
	int largeur_elt_entete;//represente la largeur entre les elts de l'entete lorsque le jeu est en cours
	

	int chrono_sec;// calcul en secondes le temps ecoule aucours d'une partie
	
	Image retour_acceuil;

	
	Entete() throws SlickException
	{
		h_acceuil = 100;
		img = new Image("entete.jpg");
		entete = 0;
		delais=0;
		acceuil=100;
		ecart_entete=40;
		largeur_elt_entete=200;
		chrono_sec = 0;
		retour_acceuil=new Image("acceuil.png");
	}
	
	
	
	public int getH_acceuil() {
		return h_acceuil;
	}



	public void setH_acceuil(int h_acceuil) {
		this.h_acceuil = h_acceuil;
	}



	public int getEntete() {
		return entete;
	}



	public void setEntete(int entete) {
		this.entete = entete;
	}



	int changement(int x, int y, int delta, int face)
	{
		if(face==3)
		{
			delais+=delta;
			
			if(delais>=2000 && entete==0)
				entete=1;
	
			if (chrono_sec / 1000 < 99999 && entete == 1)//on chronometre le jeu tant que le joueur n'a pas encore gagner
				chrono_sec += delta;
			
			if(x>0 && x<this.acceuil && y>0 && y< this.h_acceuil && entete!=0)
			{
				return 1;
			}
			
		}
		return face;
		
	}
	
	void dessiner(Graphics g, int face, int nb_joueur, int joueur, int niveau, String[] paque_img, int paq_img, int score_1, int score2, int nb_essaie)
	{
		if(face==3)
		{
			//on affiche le bouton retour a l'acceuil
			
			g.drawImage(retour_acceuil, 0, 0);
			

			g.setColor(Color.white);
			if(entete==0)
			{
				//on affiche le messsage de bienvenue
				g.drawImage(this.img, 0, 0);
			}
			else if (entete == 1) 
			{//le jeu est en cours
				
				int min=0, sec=0;
				min=(this.chrono_sec/1000)/60;
				sec=(this.chrono_sec/1000)%60;
				
				
				//on affiche le niveau
				
				g.setColor(Color.white);
				g.drawString("Niveau: "+niveau, this.acceuil+14, 4);
				g.drawString("Niveau: "+niveau, this.acceuil+14, 5);
				
				//on affiche le paque d'image
				g.drawString("Paque d'image: "+paque_img[paq_img], this.acceuil+this.ecart_entete+this.largeur_elt_entete-40, 4);
				g.drawString("Paque d'image: "+paque_img[paq_img], this.acceuil+this.ecart_entete+this.largeur_elt_entete-40, 5);
				
				//on affiche le nombre de joueur
				g.drawString("Nombre de Joueur: "+nb_joueur, this.acceuil+this.ecart_entete*2+this.largeur_elt_entete*2, 4);
				g.drawString("Nombre de Joueur: "+nb_joueur, this.acceuil+this.ecart_entete*2+this.largeur_elt_entete*2, 5);
				
				//on affiche le chrono
				g.drawString("Chrono: "+min+":"+sec, this.acceuil+this.ecart_entete*3+this.largeur_elt_entete*3+7, 4);
				g.drawString("Chrono: "+min+":"+sec, this.acceuil+this.ecart_entete*3+this.largeur_elt_entete*3+7, 5);
				
				//on affiche le nombre d'essaie
				g.drawString("Nombre d'essaie(s): "+nb_essaie/2, this.acceuil+this.ecart_entete*4+this.largeur_elt_entete*4, 4);
				g.drawString("Nombre d'essaie(s): "+nb_essaie/2, this.acceuil+this.ecart_entete*4+this.largeur_elt_entete*4, 5);
				
				g.setColor(Color.white);
				if (nb_joueur != 1) {
					// on affiche quelle joueur doit jouer
					g.drawString("JOUEUR " + joueur + " JOUEZ !", 604, 61);
				} 
				else 
				{
					g.drawString("JOUEZ !", 657, 61);
				}
				
				// si il y'a plus d'un joueur, on affiche le score des joueurs

				if (nb_joueur != 1) 
				{
					// pour le joueur 1
					g.drawString("Score 1: "+score_1, Slick.largeur-this.largeur_elt_entete, 44);
					g.drawString("Score 1: "+score_1, Slick.largeur-this.largeur_elt_entete, 45);
					
					// pour le joueur 2
					g.drawString("Score 2: "+score2, Slick.largeur-this.largeur_elt_entete, 74);
					g.drawString("Score 2: "+score2, Slick.largeur-this.largeur_elt_entete, 75);

				}

			} 
			else if(entete==2)
			{
				//c'est la fin de la partie
				
				if (nb_joueur != 1) 
				{
					if (score_1 > score2)
						g.drawString("FELICITATION !!  " + "JOUEUR 1 " + "VOUS AVEZ GAGNEZ", 484, 54);
					else if (score_1 < score2)
						g.drawString("FELICITATION !!  " + "JOUEUR 2 " + "VOUS AVEZ GAGNEZ", 484, 54);
					else
						g.drawString("FELICITATION !!  VOUS AVEZ GAGNEZ", 544, 45);
					
					
					// pour le joueur 1
					g.drawString("Score 1: "+score_1, Slick.largeur-this.largeur_elt_entete, 44);
					g.drawString("Score 1: "+score_1, Slick.largeur-this.largeur_elt_entete, 45);
					
					// pour le joueur 2
					g.drawString("Score 2: "+score2, Slick.largeur-this.largeur_elt_entete, 74);
					g.drawString("Score 2: "+score2, Slick.largeur-this.largeur_elt_entete, 75);
				} 
				else
					g.drawString("FELICITATION !!  VOUS AVEZ GAGNEZ", 574, 54);
				


				int min=0, sec=0;
				min=(this.chrono_sec/1000)/60;
				sec=(this.chrono_sec/1000)%60;		
				
				g.setColor(Color.white);
				g.drawString("Niveau: "+niveau, this.acceuil+14, 4);
				g.drawString("Niveau: "+niveau, this.acceuil+14, 5);
				
				//on affiche le paque d'image
				g.drawString("Paque d'image: "+paque_img[paq_img], this.acceuil+this.ecart_entete+this.largeur_elt_entete-40, 4);
				g.drawString("Paque d'image: "+paque_img[paq_img], this.acceuil+this.ecart_entete+this.largeur_elt_entete-40, 5);
				
				//on affiche le nombre de joueur
				g.drawString("Nombre de Joueur: "+nb_joueur, this.acceuil+this.ecart_entete*2+this.largeur_elt_entete*2, 4);
				g.drawString("Nombre de Joueur: "+nb_joueur, this.acceuil+this.ecart_entete*2+this.largeur_elt_entete*2, 5);
				
				//on affiche le chrono
				g.drawString("Chrono: "+min+":"+sec, this.acceuil+this.ecart_entete*3+this.largeur_elt_entete*3+7, 4);
				g.drawString("Chrono: "+min+":"+sec, this.acceuil+this.ecart_entete*3+this.largeur_elt_entete*3+7, 5);
				
				//on affiche le nombre d'essaie
				g.drawString("Nombre d'essaie(s): "+nb_essaie/2, this.acceuil+this.ecart_entete*4+this.largeur_elt_entete*4, 4);
				g.drawString("Nombre d'essaie(s): "+nb_essaie/2, this.acceuil+this.ecart_entete*4+this.largeur_elt_entete*4, 5);
			}

			g.drawRect(0, 0, Slick.largeur - 1, h_acceuil);
			g.drawRect(1, 1, Slick.largeur - 3, h_acceuil - 2);
			g.drawRect(2, 2, Slick.largeur - 5, h_acceuil - 4);

			
		}
		
	}
	
}
