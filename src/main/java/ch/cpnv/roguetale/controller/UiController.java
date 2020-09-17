package ch.cpnv.roguetale.controller;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.ui.LifePoint;

public class UiController implements Controller {
	private static UiController instance = null;
	
	protected static final int Y_POSITION = 5;
	protected static final int X_POSITION = 5;
	
	protected ArrayList<LifePoint> lifePoints;
	
	public static UiController getInstance() throws SlickException {
		if(instance == null) {
			instance = new UiController();
		}
		return instance;
	}
	
	private UiController() {
		lifePoints = new ArrayList<LifePoint>();
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		int x = X_POSITION;
		for(LifePoint lifePoint : lifePoints) {
			lifePoint.getSprite().draw(x, Y_POSITION);
			x += lifePoint.getSprite().getWidth();
		}

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// Set the correct number of lifePoints
		Player player = PlayerController.getInstance().getPlayer();
		while (lifePoints.size() < player.getMaxHealth()) {
			lifePoints.add(new LifePoint());
		}
		if (lifePoints.size() > player.getMaxHealth()) {
			lifePoints = new ArrayList<LifePoint>(lifePoints.subList(0, player.getMaxHealth() - 1));
		}
		
		// Set the content of the lifePoints : full/empty
		int remainingFullLifePoints = player.getCurrentHealth();
		for(LifePoint lifePoint : lifePoints) {
			if(remainingFullLifePoints > 0) {
				if(!lifePoint.Full()) {
					lifePoint.Full(true);
				}
				remainingFullLifePoints--;
			}
			else {
				if(lifePoint.Full()) {
					lifePoint.Full(false);
				}
			}
		}	
	}

	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

}
