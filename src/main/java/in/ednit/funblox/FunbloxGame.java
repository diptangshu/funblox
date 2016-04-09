package in.ednit.funblox;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunbloxGame extends BasicGame {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FunbloxGame.class);

	public FunbloxGame(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		g.drawString("Play Funblox!", 50, 50);
		
	}
	
	public static void main(String[] args) {
		
		LOGGER.info("Staring Funblox...");
		
		try {
			AppGameContainer gameContainer = new AppGameContainer(new FunbloxGame("Funblox"));
			gameContainer.setDisplayMode(640, 480, false);
			gameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
   
}
