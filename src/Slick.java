import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

public class Slick extends BasicGame {

	
	public static int largeur = 1300, longueur = 1000;// dimension de la fenetre
	
	Animation animation;
	Menu menu;
	Option_menu option;
	Entete entete;
	Interface interfasse;
	Bordure bordure;
	Jeu jeu;
	Cartes cartes;


	int x, y;// abscisse et ordonné de l'endroit ou le joueur click





	public Slick(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		// TODO Auto-generated method stub

		this.animation.dessiner(g, this.interfasse.getFace());
		// on affiche le message de l'entete et  on affiche les chronometres
		this.entete.dessiner(g, this.interfasse.getFace(), this.option.getNb_joueur(), this.jeu.getJoueur(), this.option.getNiveau(), this.option.getPaque_img(), this.option.getPaq_img(), this.jeu.getScore_1(), this.jeu.getScore2(), this.jeu.getNb_essaie());
	
		// on dessine les bordures
		this.bordure.dessiner(g,this.interfasse.getFace(), this.option.getNiveau(), this.entete.getH_acceuil(), this.cartes.getLargeur_img());
		// on dessine le menu
		this.menu.dessiner(g, this.interfasse.getFace(), Slick.largeur, Slick.longueur);
		// on dessine les options du menu
		this.option.dessiner(g, this.interfasse.getFace(), this.largeur, this.longueur);
		// on affiche les images
		cartes.dessiner (g, this.option.getNiveau(), this.interfasse.getFace(), this.option.getImg_affichee_0(), this.bordure.getLargeur_bordure(), this.bordure.getLargeur_espace(), this.entete.getH_acceuil(), this.bordure.getLongueur_espace());
		
		
		
	}



	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

		animation = new Animation();
		menu = new Menu();
		option = new Option_menu(0, 2, 1); //niveau, nb_joueur,  paq_img
		entete = new Entete();
		interfasse = new Interface(0);
		bordure = new Bordure(0);
		cartes = new Cartes(0);
		jeu = new Jeu(this.option.getNiveau(), this.bordure.getLargeur_bordure(), this.bordure.getLargeur_espace(), this.entete.getH_acceuil(), this.bordure.getLongueur_espace());
	
		x = 200;
		y = 200;	

		largeur = 1300;
		longueur = 1000;
		
		
	}

	

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		// TODO Auto-generated method stub
		Input inp = gc.getInput();

		this.interfasse.setFace(this.animation.Anime(delta, this.interfasse.getFace()));
		
		
		if (inp.isMousePressed(Input.MOUSE_LEFT_BUTTON) || inp.isMousePressed(Input.MOUSE_RIGHT_BUTTON)
				|| inp.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)) 
		{
			int[] tab= new int[2]; tab[0]=0; tab[1]=this.interfasse.getFace();
			x = inp.getMouseX();
			y = inp.getMouseY();
			jeu.verif_click(x, y, this.option.getNiveau(), this.bordure.getLargeur_bordure(), this.bordure.getLargeur_espace(), this.entete.getH_acceuil(), this.interfasse.getFace(), this.bordure.getLongueur_espace(), this.option.getNb_joueur(), this.cartes.getG());// gestion du retournement d'une carte lors d'un clic de souris
			
			this.interfasse.setFace(menu.changement(x, y, Slick.largeur, Slick.longueur, this.interfasse.getFace()));//gestion d'un click sur un elt du menu
		
			tab=option.changement(x, y, this.interfasse.getFace());//on gere un changement d'option dans l'interface 2
			this.interfasse.setFace(tab[1]);
			if(tab[0] == 1)//tab[0] est soit 0, soit 1 et represente si oui il faut faire un basculement dans la classe jeu ie tout reinitialiser
			{
				option = new Option_menu(this.option.getNiveau(), this.option.getNb_joueur(), this.option.getPaq_img());
				entete = new Entete();
				bordure = new Bordure(this.option.getNiveau());
				cartes = new Cartes(this.option.getNiveau());
				jeu = new Jeu(this.option.getNiveau(), this.bordure.getLargeur_bordure(), this.bordure.getLargeur_espace(), this.entete.getH_acceuil(), this.bordure.getLongueur_espace());
			}
		
	    }

		jeu.retournement_cartes(this.option.getNiveau(), delta, this.interfasse.getFace(), this.option.getNb_joueur(), this.entete.getEntete());//on gere le retournement d'une carte 
		this.jeu.bougercarte(x, y, this.option.getNiveau(), this.interfasse.getFace(), delta, this.bordure.getLargeur_bordure(), this.entete.getH_acceuil());
		entete.setEntete(jeu.verifGagner(option.getNiveau(), interfasse.getFace(), entete.getEntete()));//on change l'entete si on gagne
		
		this.interfasse.setFace(entete.changement(x, y, delta, interfasse.getFace()));//on enleve le message e bienvenue et on affiche les informations relatif aux jeu apres un temps
		
		this.cartes.changement(this.jeu.getTab(), this.interfasse.getFace(), this.option.getNiveau(), this.jeu.getDe_dos(), delta, this.jeu.getTemps_face(), this.jeu.getTrouver());
		
		this.jeu.verifGagner(this.option.getNiveau(), this.interfasse.getFace(), this.entete.getEntete());
		
	}

}
