import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

//correspond a l'interface 0

public class Animation {
	private int animation;// entier qui permet le basculement entre les animations et le debut du jeu
	Image anima;// anima contient l'image d'animation
	
	int chrono, chrono2, anim;// les chronos sont utilises pour gerer les animations avant d'afficher la page
	// d'acceuil du jeu
	//chrono s'occupe du message memory et chrono2 s'occupe de faire defiler les images
	
	Image memory[] = new Image[6];// contient chacune des lettres du mot memory pour une animation
	int coord[][] = new int[46][2];// tableau des coordonnes des images de l'animation
	
	Image fondacceuil;

	Sound sound;

	int timer3;// temps de duree du song du debut pendant l'animation
	
	Animation() throws SlickException
	{
		fondacceuil= new Image("fondacceuil.png");
		
		sound = new Sound("D:\\1 ere anne 3il\\1er_SEMESTRE\\POO\\mini projetc1 numero2\\son\\song1.wav");
		sound.playAt(1f, 1f, 0, 0, 0);
		animation = 0;

		anima = new Image("anim.png");
		
		for (int i = 1; i <= 6; i++) {
			memory[i - 1] = new Image("memory/" + i + ".png");
		}

		for (int i = 0; i < Slick.largeur/100; i++) {//0-13
			coord[i][0] = i * 100;
			coord[i][1] = 0;
		}

		for (int i = Slick.largeur/100; i < Slick.largeur/100+Slick.longueur/100; i++) {//13-23
			coord[i][0] = Slick.largeur-100;
			coord[i][1] = (i-13)*100;
		}
		
		for (int i = Slick.largeur/100+Slick.longueur/100; i < Slick.largeur*2/100+Slick.longueur/100; i++) {//23-36
			coord[i][0] = Slick.largeur - 100* (i - (23-1));
			coord[i][1] = Slick.longueur - 100;
		}
		
		for (int i = Slick.largeur*2/100+Slick.longueur/100; i < Slick.largeur*2/100+Slick.longueur*2/100; i++) {//36-46
			coord[i][0] = 0;
			coord[i][1] = Slick.longueur - 100*(i - (36-1));
		}

		chrono = 0;
		chrono2 = 0;
		anim = 0;

		timer3 = 0;

	}
	
	int Anime(int delta, int face)
	{
		if(face==0)
		{

			
			
			timer3 += delta;

			// gestion des animations
			chrono += delta;
			chrono2 += delta;
			int temps = 374;

			if (chrono >= temps) {
				animation = 1;
			}
			if (chrono >= temps * 2) {
				animation = 2;
			}
			if (chrono >= temps * 3) {
				animation = 3;
			}
			if (chrono >= temps * 4) {
				animation = 4;
			}
			if (chrono >= temps * 5) {
				animation = 5;
			}
			if (chrono >= temps * 6) {
				animation = 6;
			}
			if (chrono >= temps * 19) {
				animation = 7;
				anim = 47;
			}

			if (chrono2 >= 100 && anim != (Slick.largeur*2/100+Slick.longueur*2/100)) {
				//chrono2 >= 100 signifie que les images commencent a apparaitre 100ms apres le demaraage du jeu
				chrono2 = 0;
				anim++;
	
			}

		
			if (animation == 7) {
				// des que le jeu commence, on arrete le song

				if (timer3 >= 1000)
					sound.stop();
				
				 //donc l'animation est terminer et il faut mtn passer au menu
				return 1;
			}
		}

		return face;
	}
	
	
	
	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public int getChrono() {
		return chrono;
	}

	public void setChrono(int chrono) {
		this.chrono = chrono;
	}

	public int getChrono2() {
		return chrono2;
	}

	public void setChrono2(int chrono2) {
		this.chrono2 = chrono2;
	}

	public int getTimer3() {
		return timer3;
	}

	public void setTimer3(int timer3) {
		this.timer3 = timer3;
	}

	void dessiner (Graphics g, int face)
	{
		if(face==0)
		{
			g.drawImage(this.fondacceuil, 100, 100);
			
			for (int i = 1; i <= 6; i++) {
				if (animation == i) {
					for (int j = 1; j <= i; j++) {
						g.drawImage(memory[j - 1], 344 + (j - 1) * 100, 474);
					}
				}
			}

			for (int i = 1; i <= 46; i++) {
				if (anim == i) {
					for (int j = 1; j <= i; j++) {
						g.drawImage(anima, coord[j - 1][0], coord[j - 1][1]);
					}
				}
			}
		}
		
	}
}

