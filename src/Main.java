import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		Slick jeu = new Slick("MEMORY");
		AppGameContainer app= new AppGameContainer(jeu);
		app.setTargetFrameRate(400);
		app.setShowFPS(false);
		app.setDisplayMode(Slick.largeur, Slick.longueur, false);
		app.start();
	}

}
