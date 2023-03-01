import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Jeu {
	

	int de_dos[][] = new int[8][8];// indique si l'image que va afficher sera de dos ou de face , 1 si de_dos et 0
	// sinon
	
	int[][] temps_face = new int[8][8];// contient le temps d'ouverture d'une carte
	

	int num;// contient le numero de l'image ouverte precedement

	int[][] trouver = new int[8][8];// si la valeur vaut 1 alors la position correspondante a deja ete decouverte

	int ab, ord;// contiennent l'abscisse et l'ordonne de la derniere image ouverte
	
	float tab[][] = new float[64][2]; //coordonées des cartes a chaque instant , concerne uniquement le niveau 2 

	int score_1, score2;
	
	int nb_essaie;
	int joueur;

	private int largeur_img;
	
	float[][] vitesse = new float[64][2];// tableau des vitesses de deplacement
	

	float[] liste = new float[128];//pour remplir tab pour la premiere fois, je recupere les abscisse suivit des ordonnées de chaque case du niveau 2 ou 3 a l'instant initiale
	//(8*8)*2=128
	
	private int debut;//permet de refermer les cartes 2 secondes apres l'affichage du plateau, il permet de mettre toutes les cartes de dos une seule fois et non pas a chaque fois que l'entete sera egale a 1
	
	Jeu(int niveau, int largeur_bordure, int largeur_espace, int h_acceuil, int longueur_espace)
	{
		score_1 = 0;
		score2 = 0;
		nb_essaie = 0;
		joueur=1;
		largeur_img=100;
		debut=0;
		
		num = 0;
		ab = -1;
		ord = -1;
		

		for (int w = 0; w < 8; w++) {
			for (int x = 0; x < 8; x++) {
				de_dos[w][x] = 0;
			}
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temps_face[i][j] = 0;
			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				trouver[i][j] = 0;
			}
		}
		

		if(niveau==2)
		{
			// on rempli la liste des coordonnees initiaux pour le niveau 2
			int a = 0;
			for (int i = 0; i < 8; i = i + 2) {
				for (int j = 0; j < 8; j = j + 2) {
					liste[a] = i;
					liste[a + 1] = j;
					a++;
					a++;
				}
			}
		}
		
		if(niveau==3)
		{
			// on rempli la liste des coordonnees initiaux pour le niveau 3
			int a = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					liste[a] = i;
					liste[a + 1] = j;
					a++;
					a++;
				}
			}
		}
		

		if (niveau == 2) 
		{
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 2; j++) {
						do {
							this.vitesse[i][j] = -159 + (float) (Math.random() * 120 * 2) + 40 * 2;
						} while (this.vitesse[i][j] < 40 && this.vitesse[i][j] > -40);

					}
				}

				int a = 0;
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 2; j++) {
						if (j == 0)/// c'est une coord abscisse
							tab[i][j] = largeur_bordure + largeur_espace + liste[a] * largeur_img / 2;
						else
							tab[i][j] = largeur_bordure + h_acceuil + longueur_espace + liste[a] * largeur_img / 2;
						a++;
					}
				}
		}
		if (niveau == 3) 
		{
				for (int i = 0; i < 64; i++) {
					for (int j = 0; j < 2; j++) {
						do {
							this.vitesse[i][j] = -159 + (float) (Math.random() * 120 * 2) + 40 * 2;
						} while (this.vitesse[i][j] < 40 && this.vitesse[i][j] > -40);

					}
				}

				int a = 0;
				for (int i = 0; i < 64; i++) {
					for (int j = 0; j < 2; j++) {
						if (j == 0)/// c'est une coord abscisse
							tab[i][j] = largeur_bordure + largeur_espace + liste[a] * largeur_img ;
						else
							tab[i][j] = largeur_bordure + h_acceuil + longueur_espace + liste[a] * largeur_img ;
						a++;
					}
				}
		}



	}
	
	

	public int[][] getTemps_face() {
		return temps_face;
	}



	public void setTemps_face(int[][] temps_face) {
		this.temps_face = temps_face;
	}



	public int[][] getDe_dos() {
		return de_dos;
	}



	public void setDe_dos(int[][] de_dos) {
		this.de_dos = de_dos;
	}




	public int getScore_1() {
		return score_1;
	}





	public void setScore_1(int score_1) {
		this.score_1 = score_1;
	}





	public int getScore2() {
		return score2;
	}





	public void setScore2(int score2) {
		this.score2 = score2;
	}





	public int getNb_essaie() {
		return nb_essaie;
	}





	public void setNb_essaie(int nb_essaie) {
		this.nb_essaie = nb_essaie;
	}





	public int getJoueur() {
		return joueur;
	}





	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}


	



	public int[][] getTrouver() {
		return trouver;
	}



	public void setTrouver(int[][] trouver) {
		this.trouver = trouver;
	}



	public float[][] getTab() {
		return tab;
	}





	public void setTab(float[][] tab) {
		this.tab = tab;
	}



	
	void verif_click(int x, int y, int niveau, int largeur_bordure, int largeur_espace, int h_acceuil, int face, int longueur_espace, int nb_joueur, int[][] g)
	{
		
		if(face==3)
		{
			if (niveau == 0) {
				for (int i = 0; i < 8; i = i + 2) {
					for (int j = 0; j < 8; j = j + 2) {
						if (x > largeur_bordure + largeur_espace + i * largeur_img / 2
								&& x < largeur_bordure + largeur_espace + i * largeur_img / 2 + largeur_img
								&& y > h_acceuil + largeur_bordure + longueur_espace + j * largeur_img / 2
								&& y < h_acceuil + largeur_bordure + longueur_espace + j * largeur_img / 2
										+ largeur_img) {

							if(nb_essaie<=999999)
								nb_essaie++;
							de_dos[i][j] = 0;
							temps_face[i][j] = 0;

							if (nb_joueur != 1) {
								if (nb_essaie % 2 == 0)
									this.joueur = (this.joueur + 2) % 2 + 1;//on bascule au joueur suivant
							}
							
							
							if (g[i][j] == num && (i != ab || j != ord) && trouver[ab][ord] == 0 && trouver[i][j] == 0) 
							{
								num = 0;
								de_dos[ab][ord] = 0;
								trouver[ab][ord] = 1;
								ab = i;
								ord = j;
								trouver[i][j] = 1;

								if (nb_joueur != 1) 
								{
									if (nb_essaie % 2 == 0)// donc la valeur de joueur est celui de l'etat qui doit jouer
															// mtn et non celui du joueur qui vient de jouer
									{
										if (joueur == 2)
											this.score_1++;
										else if (joueur == 1)
											this.score2++;
									} else {
										if (joueur == 1)
											this.score_1++;
										else if (joueur == 2)
											this.score2++;
									}

								}

							} 
							else 
							{
								ab = i;
								ord = j;
								num = g[i][j];
							}
			

						}

					}
				}

			}

			if (niveau == 1) {
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (x > largeur_bordure + largeur_espace + i * largeur_img
								&& x < largeur_bordure + largeur_espace + i * largeur_img + largeur_img
								&& y > h_acceuil + largeur_bordure + longueur_espace + j * largeur_img
								&& y < h_acceuil + largeur_bordure + longueur_espace + j * largeur_img + largeur_img) {
							
							if(nb_essaie<=999999)
								nb_essaie++;
							de_dos[i][j] = 0;
							temps_face[i][j] = 0;

							if (nb_joueur != 1) {
								if (nb_essaie % 2 == 0)
									this.joueur = (this.joueur + 2) % 2 + 1;
							}
							
							
							if (g[i][j] == num && (i != ab || j != ord) && trouver[ab][ord] == 0 && trouver[i][j] == 0) 
							{
								num = 0;
								de_dos[ab][ord] = 0;
								trouver[ab][ord] = 1;
								ab = i;
								ord = j;
								trouver[i][j] = 1;

								if (nb_joueur != 1) 
								{
									if (nb_essaie % 2 == 0)// donc la valeur de joueur est celui de l'etat qui doit jouer
															// mtn et non celui du joueur qui vient de jouer
									{
										if (joueur == 2)
											this.score_1++;
										else if (joueur == 1)
											this.score2++;
									} else {
										if (joueur == 1)
											this.score_1++;
										else if (joueur == 2)
											this.score2++;
									}

								}

							} 
							else 
							{
								ab = i;
								ord = j;
								num = g[i][j];
							}
			


						}

					}
				}
			}

			if (niveau == 2) 
			{
				int a = 15;
				for (int i = 6; i >= 0; i = i - 2) {
					for (int j = 6; j >= 0; j = j - 2) {
						if (x > tab[a][0] && x < tab[a][0] + largeur_img && y > tab[a][1]
								&& y < tab[a][1] + largeur_img) {
							
							if(nb_essaie<=999999)
								nb_essaie++;
							de_dos[i][j] = 0;
							temps_face[i][j] = 0;

							if (nb_joueur != 1) {
								if (nb_essaie % 2 == 0)
									this.joueur = (this.joueur + 2) % 2 + 1;
							}

							if (g[i][j] == num && (i != ab || j != ord) && trouver[ab][ord] == 0 && trouver[i][j] == 0) 
							{
								num = 0;
								de_dos[ab][ord] = 0;
								trouver[ab][ord] = 1;
								ab = i;
								ord = j;
								trouver[i][j] = 1;

								if (nb_joueur != 1) 
								{
									if (nb_essaie % 2 == 0)// donc la valeur de joueur est celui de l'etat qui doit jouer
															// mtn et non celui du joueur qui vient de jouer
									{
										if (joueur == 2)
											this.score_1++;
										else if (joueur == 1)
											this.score2++;
									} else {
										if (joueur == 1)
											this.score_1++;
										else if (joueur == 2)
											this.score2++;
									}

								}

							} 
							else 
							{
								ab = i;
								ord = j;
								num = g[i][j];
							}
			

							
							i = -1;
							break;
						}
						a--;

					}
				}

			}
			
			
			if (niveau == 3) 
			{
				int a = 63;
				for (int i = 7; i >= 0; i--) {
					for (int j = 7; j >= 0; j--) {
						if (x > tab[a][0] && x < tab[a][0] + largeur_img && y > tab[a][1]
								&& y < tab[a][1] + largeur_img) {
							
							if(nb_essaie<=999999)
								nb_essaie++;
							de_dos[i][j] = 0;
							temps_face[i][j] = 0;

							if (nb_joueur != 1) 
							{
								if (nb_essaie % 2 == 0)
									this.joueur = (this.joueur + 2) % 2 + 1;
							}

							
							if (g[i][j] == num && (i != ab || j != ord) && trouver[ab][ord] == 0 && trouver[i][j] == 0) 
							{
								num = 0;
								de_dos[ab][ord] = 0;
								trouver[ab][ord] = 1;
								ab = i;
								ord = j;
								trouver[i][j] = 1;

								if (nb_joueur != 1) 
								{
									if (nb_essaie % 2 == 0)// donc la valeur de joueur est celui de l'etat qui doit jouer
															// mtn et non celui du joueur qui vient de jouer
									{
										if (joueur == 2)
											this.score_1++;
										else if (joueur == 1)
											this.score2++;
									} else {
										if (joueur == 1)
											this.score_1++;
										else if (joueur == 2)
											this.score2++;
									}

								}

							} 
							else 
							{
								ab = i;
								ord = j;
								num = g[i][j];
							}
			

							
							
							i = -1;
							break;
						}
						a--;

					}
				}

			}
		}
		

	}


	void retournement_cartes(int niveau, int delta, int face, int nb_joueur, int entete)
	{
		if(face==3)
		{
			
			if(entete == 1 && debut==0)//au debut, toutes les cartes sont ouvertes, et apres 2 seconde, ie le temps de passage de l'entete 0 a l'entete 1, toutes les cartes se refermment
			{
				for (int w = 0; w < 8; w++) {
					for (int x = 0; x < 8; x++) {
						de_dos[w][x] = 1;
					}
				}
				
				debut=1;
			}
			
			if(entete == 1)
			{
				// on gere le retournement des cartes
				if (niveau == 0 || niveau == 2) 
				{
					for (int i = 0; i < 8; i = i + 2) 
					{
						for (int j = 0; j < 8; j = j + 2) 
						{

							if (de_dos[i][j] == 0 && trouver[i][j] == 0) 
							{
									temps_face[i][j] += delta;
	
									if (temps_face[i][j] >= 1000) 
									{
										temps_face[i][j] = 0;
										de_dos[i][j] = 1;
									}
									
							}

						}
					}
				}

				if (niveau == 1 || niveau==3) 
				{
					for (int i = 0; i < 8; i ++) {
						for (int j = 0; j < 8; j ++) {

							if (de_dos[i][j] == 0 && trouver[i][j] == 0) 
							{
									temps_face[i][j] += delta;
	
									
									if (temps_face[i][j] >= 1000) 
									{
										temps_face[i][j] = 0;
										de_dos[i][j] = 1;
									}
									
							}

						}
					}
					
				}

			}
		
		}
	
	}	
	void bougercarte (int x, int y, int niveau, int face, int delta, int largeur_bordure, int h_acceuil)
	{
		// le cas ou niveau==2, on fait les cartes bougers

		if(face == 3)
		{
			if (niveau == 2) 
			{
				for (int i = 0; i < 16; i++) 
				{
					for (int j = 0; j < 2; j++) {
						tab[i][j] = tab[i][j] + (vitesse[i][j] * delta) / 1000;

						if (j == 0) {
							if (tab[i][j] <= largeur_bordure) {
								tab[i][j] = largeur_bordure;
								vitesse[i][j] *= -1;
							}

							if (tab[i][j] >= Slick.largeur - largeur_bordure - largeur_img) {
								tab[i][j] = Slick.largeur - largeur_bordure - largeur_img;
								vitesse[i][j] *= -1;
							}
						}

						if (j == 1) {
							if (tab[i][j] <= largeur_bordure + h_acceuil) {
								tab[i][j] = largeur_bordure + h_acceuil;
								vitesse[i][j] *= -1;
							}

							if (tab[i][j] >= Slick.longueur - largeur_bordure - largeur_img) {
								tab[i][j] = Slick.longueur - largeur_bordure - largeur_img;
								vitesse[i][j] *= -1;
							}

						}

					}
				}
			}
			
			if (niveau == 3) 
			{
				for (int i = 0; i < 64; i++) 
				{
					for (int j = 0; j < 2; j++) {
						tab[i][j] = tab[i][j] + (vitesse[i][j] * delta) / 1000;

						if (j == 0) {
							if (tab[i][j] <= largeur_bordure) {
								tab[i][j] = largeur_bordure;
								vitesse[i][j] *= -1;
							}

							if (tab[i][j] >= Slick.largeur - largeur_bordure - largeur_img) {
								tab[i][j] = Slick.largeur - largeur_bordure - largeur_img;
								vitesse[i][j] *= -1;
							}
						}

						if (j == 1) {
							if (tab[i][j] <= largeur_bordure + h_acceuil) {
								tab[i][j] = largeur_bordure + h_acceuil;
								vitesse[i][j] *= -1;
							}

							if (tab[i][j] >= Slick.longueur - largeur_bordure - largeur_img) {
								tab[i][j] = Slick.longueur - largeur_bordure - largeur_img;
								vitesse[i][j] *= -1;
							}

						}

					}
				}
			}
		}
		
	}
	
	int verifGagner(int niveau, int face, int entete)
	{
		if(face==3)
		{
			// apres avoir gagner, on affiche le message de felicitation 

			int d = 1;
			if (niveau == 0 || niveau == 2) 
			{

				for (int i = 0; i < 8; i = i + 2) {
					for (int j = 0; j < 8; j = j + 2) {
						if (trouver[i][j] == 0) {

							d = 0;
						}

					}
				}

				if (d == 1) {
					// on change l'image d'entete

					return 2;
				}
			}

			if (niveau == 1) 
			{

				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (trouver[i][j] == 0)
							d = 0;
					}
				}

				if (d == 1) {
					// on change l'image d'entete
					return 2;

				}
			}
		}
		return entete;
		
	}
}
