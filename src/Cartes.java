import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

//correspond a l'interface 3

public class Cartes {

	int [][]g=new int[8][8];

	int largeur_img;// largeur de chaque case de la grille
	

	float tab[][] = new float[64][2]; //coordonées des cartes a chaque instant , concerne uniquement le niveau 2
	

	int de_dos[][] = new int[8][8];// indique si l'image que va afficher sera de dos ou de face , 1 si de_dos et 0 sinon
	
	int clonedelta;
	int[][] temps_face = new int[8][8];//clone de celui qui se trouve dans jeu
	
	int[][] delaisAnim=new int [8][8];//definit le temps d'animation d'une image lorsqu'on la retourne
	
	int[][] trouver = new int[8][8];//clone de celui qui se trouve dans jeu
	

	
	public Cartes()
	{
		clonedelta=0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				temps_face[i][j] = 0;
				delaisAnim[i][j]=0;
			}
		}
		
		for(int i=0; i<8; i=i+2)
		{
			for(int j=0; j<8; j=j+2)
			{
				g[i][j]=0;
			}
		}
		
		int num=0;
		int nums[]=new int[32];/*contient le numero d'une image deja utilisee*/
		int taille=0;
		int i=0, j=0;
		int b=0, c=0;
		
		do
		{
			
			do
			{
				i=(int)(Math.random()*4);
				j=(int)(Math.random()*4);
				
				
				b=0;
				
				if(g[i*2][j*2]!=0)/*donc on lui a deja affecter une image*/
					b=1;
				
			}while(b==1);
			
		
			
			if(b==0)
			{
				c=0;
				do
				{
					c=0;
					num=(int)(Math.random()*32+1);//donc 32 possibilites d'images differentes
					
					for(int k=0; k<taille/2; k++)
					{
						if(nums[k]==num)
						{
							c=1;break;
						}
					}
					
				}while(c==1);
				
				nums[taille/2]=num;
				
				g[i*2][j*2]=num;
				
				
				taille++;
				
				c=0;
				
				
				do
				{
					c=0;
					i=(int)(Math.random()*4);
					j=(int)(Math.random()*4);
										
					if(g[i*2][j*2]!=0)/*donc on lui a deja affecter une image*/
						c=1;
					
				}while(c==1);
				
				g[i*2][j*2]=num;
				taille++;
				
						
			}
		
			
		}while(taille!=16);
		


		for (int w = 0; w < 8; w++) {
			for (int x = 0; x < 8; x++) {
				de_dos[w][x] = 0;
			}
		}
		
		largeur_img = 100;
		
		
	}
	
	public Cartes(int niveau)
	{
		this();
		
		if(niveau==1 || niveau==3)
		{
			
	
			for(int i=0; i<8; i++)
			{
				for(int j=0; j<8; j++)
				{
					g[i][j]=0;
				}
			}
			
			int num=0;
			int nums[]=new int[32];
			int taille=0;
			int i=0, j=0;
			int b=0;
			
			do
			{
				do
				{
					i=(int)(Math.random()*8);
					j=(int)(Math.random()*8);
					
					
					b=0;
					
					if(g[i][j]!=0)/*donc on lui a deja affecter une image*/
						b=1;
					
					
				}while(b==1);
				
				
				
				
				if(b==0)
				{
					int c=0;
					do
					{
						c=0;
						num=(int)(Math.random()*32+1);//donc 32 possibilites d'images differentes
						
						for(int k=0; k<taille/2; k++)
						{
							if(nums[k]==num)
							{
								c=1;break;
							}
						}
						
					}while(c==1);
					
					nums[taille/2]=num;
					g[i][j]=num;
				
					taille++;
					
					c=0;
					
					do
					{
						c=0;
						i=(int)(Math.random()*8);
						j=(int)(Math.random()*8);
						
						if(g[i][j]!=0)/*donc on lui a deja affecter une image*/
							c=1;
						
						
					}while(c==1);
					
					g[i][j]=num;
					
					taille++;
			
							
				}
				
			}while(taille!=64);
		}
		
		
		
	}
	
	
	
	void changement(float[][] tab, int face, int niveau, int[][] de_dos, int delta, int[][] temps_face, int[][] trouver)
	{
		if(face==3)
		{
			if(niveau==2 || niveau==3)
			{
				this.tab=tab;
			}
			this.de_dos=de_dos;
			this.clonedelta=delta;
			this.temps_face=temps_face;
			this.trouver=trouver;
		}
	}
	
	
	void dessiner (Graphics gr, int niveau, int face, Image[] img_affichee_0, int largeur_bordure, int largeur_espace, int h_acceuil, int longueur_espace)
	{
		if(face==3)
		{
		
			//x et y coin superiieur gauche; x2 et y2 coin inferieur droit
			
			if (niveau == 0) 
			{
				gr.setColor(Color.white);
				for (int i = 0; i < 8; i = i + 2) {
					for (int j = 0; j < 8; j = j + 2) {
						if (de_dos[i][j] == 0) 
						{
							if(this.clonedelta<=this.temps_face[i][j])
							{
								//ce cas concerne une carte sur laquelle on vient de cliquer
								this.animimg(gr, largeur_bordure + largeur_espace + i * largeur_img / 2, largeur_bordure + longueur_espace + j * largeur_img / 2+ h_acceuil, img_affichee_0, i, j);
							}
							else
							{
								//ce cas concerne les cartes trouver depuis longtemps
								gr.drawImage(img_affichee_0[g[i][j]],
										largeur_bordure + largeur_espace + i * largeur_img / 2,
										largeur_bordure + longueur_espace + j * largeur_img / 2 + h_acceuil);
							}
							
						}

						else
						{
							delaisAnim[i][j]=0;
							gr.drawImage(img_affichee_0[0], largeur_bordure + largeur_espace + i * largeur_img / 2,
									largeur_bordure + longueur_espace + j * largeur_img / 2 + h_acceuil);
						}
							

						gr.drawRect(largeur_bordure + largeur_espace + i * largeur_img / 2,
								largeur_bordure + longueur_espace + j * largeur_img / 2 + h_acceuil, largeur_img,
								largeur_img);
						gr.drawRect(largeur_bordure + largeur_espace + i * largeur_img / 2 + 1,
								largeur_bordure + longueur_espace + j * largeur_img / 2 + h_acceuil + 1, largeur_img,
								largeur_img);
					}

				}
			}

			if (niveau == 1) 
			{
				gr.setColor(Color.white);
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (de_dos[i][j] == 0) 
						{
							if(this.clonedelta<=this.temps_face[i][j])
							{
								this.animimg(gr, largeur_bordure + largeur_espace + i * largeur_img, largeur_bordure + longueur_espace + j * largeur_img + h_acceuil, img_affichee_0, i, j);
							}
							else
							{
								gr.drawImage(img_affichee_0[g[i][j]],
										largeur_bordure + largeur_espace + i * largeur_img,
										largeur_bordure + longueur_espace + j * largeur_img + h_acceuil);
							}
						}

						else
						{
							delaisAnim[i][j]=0;
							gr.drawImage(img_affichee_0[0], largeur_bordure + largeur_espace + i * largeur_img,
									largeur_bordure + longueur_espace + j * largeur_img + h_acceuil);
						}
							

						gr.drawRect(largeur_bordure + largeur_espace + i * largeur_img,
								largeur_bordure + longueur_espace + j * largeur_img + h_acceuil, largeur_img,
								largeur_img);
						gr.drawRect(largeur_bordure + largeur_espace + i * largeur_img + 1,
								largeur_bordure + longueur_espace + j * largeur_img + h_acceuil + 1, largeur_img - 2,
								largeur_img - 2);
					}

				}
			}

			if (niveau == 2) 
			{
				int a = 0, b = 0;
				gr.setColor(Color.white);
				for (int i = 0; i < 8; i = i + 2) 
				{
					for (int j = 0; j < 8; j = j + 2) {
						if (de_dos[i][j] == 0) 
						{
							
							if(this.clonedelta<=this.temps_face[i][j])
							{
								this.animimg(gr, tab[a][b], tab[a][b + 1], img_affichee_0, i, j);
							}
							else
								gr.drawImage(img_affichee_0[g[i][j]], tab[a][b], tab[a][b + 1]);
						}

						else
						{
							delaisAnim[i][j]=0;
							gr.drawImage(img_affichee_0[0], tab[a][b], tab[a][b + 1]);
						}

						gr.drawRect(tab[a][b], tab[a][b + 1], largeur_img, largeur_img);
						gr.drawRect(tab[a][b] + 1, tab[a][b + 1] + 1, largeur_img, largeur_img);

						a++;
					}

				}
			}
			
			
			if (niveau == 3) 
			{
				int a = 0, b = 0;
				gr.setColor(Color.white);
				for (int i = 0; i < 8; i++) 
				{
					for (int j = 0; j < 8; j++) {
						if (de_dos[i][j] == 0) {
							
							if(this.clonedelta<=this.temps_face[i][j])
							{
								this.animimg(gr,  tab[a][b], tab[a][b + 1], img_affichee_0, i, j);
							}
							else
								gr.drawImage(img_affichee_0[g[i][j]], tab[a][b], tab[a][b + 1]);
						}

						else
						{
							delaisAnim[i][j]=0;
							gr.drawImage(img_affichee_0[0], tab[a][b], tab[a][b + 1]);
						}

						gr.drawRect(tab[a][b], tab[a][b + 1], largeur_img, largeur_img);
						gr.drawRect(tab[a][b] + 1, tab[a][b + 1] + 1, largeur_img, largeur_img);

						a++;
					}

				}
			}
			
		}

	}

	
	public void animimg(Graphics gr, float posx, float posy, Image[] img_affichee_0, int indx, int indy)
	{
		
			if(delaisAnim[indx][indy] < this.largeur_img/2)
			{
				delaisAnim[indx][indy]++;
				gr.drawImage(img_affichee_0[0], posx+delaisAnim[indx][indy], posy, posx+this.largeur_img-delaisAnim[indx][indy], posy+this.largeur_img, 0, 0, 100, 100);
			}
			
			else
			{
				if(de_dos[indx][indy] == 1)//si l'animation de l'image est terminee on attend si/que l'image soit de_dos avant reinitialiser le delaisAnim pour ne pas que l'animation tourne en boucle
					delaisAnim[indx][indy]=0;
				gr.drawImage(img_affichee_0[g[indx][indy]], posx, posy);
			}
				
	}
	public int[][] getG() {
		return g;
	}

	public void setG(int[][] g) {
		this.g = g;
	}

	public int getLargeur_img() {
		return largeur_img;
	}

	public void setLargeur_img(int largeur_img) {
		this.largeur_img = largeur_img;
	}

	public void setTab(float[][] tab) {
		this.tab = tab;
	}


	public int[][] getDe_dos() {
		return de_dos;
	}

	public void setDe_dos(int[][] de_dos) {
		this.de_dos = de_dos;
	}
	
	
	
	

}
