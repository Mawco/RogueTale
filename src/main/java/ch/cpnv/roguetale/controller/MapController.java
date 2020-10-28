package ch.cpnv.roguetale.controller;

import java.util.Vector;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.main.Main;

public class MapController implements Controller {
	private Image background;
	private Vector<Vector2f> map = new Vector<Vector2f>();
	private static final int TILE_DIMENSION = 70;
	
	public MapController() throws SlickException {
		this.background = new Image("ch\\cpnv\\roguetale\\images\\background\\tile.png");
		this.background.setFilter(Image.FILTER_NEAREST);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin) {
		Image bg = this.getBackground();
		Vector<Vector2f> map = this.getMap();
		int 	xOrigin 	= Math.round(origin.x),
				yOrigin 	= Math.round(origin.y),
				height 		= Main.BASE_HEIGHT,
				width 		= Main.BASE_WIDTH,
				doubleChunk = TILE_DIMENSION * 2,
				minScreenX 	= xOrigin - doubleChunk,
				maxScreenX 	= xOrigin + width + doubleChunk,
				minScreenY 	= yOrigin - height - doubleChunk,
				maxScreenY 	= yOrigin + doubleChunk;
		
		// Draw background
		for (Vector2f vector : map) {
			// Multiply by 70 for image dimension
			int 	x 				= Math.round(vector.x),
					y 				= Math.round(vector.y),
					posMapX 		= x * TILE_DIMENSION,
					posMapY 		= y * TILE_DIMENSION,
					tilePosXDiff 	= posMapX - xOrigin,
					tilePosYDiff 	= - posMapY + yOrigin;
			
			if (this.isInScreen(minScreenX, maxScreenX, minScreenY, maxScreenY, posMapX, posMapY))	{
				bg.drawCentered(tilePosXDiff, tilePosYDiff);
			}
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		int x 			= Math.round(origin.x),
			y 			= Math.round(origin.y),
			minHeight 	= y - Main.BASE_HEIGHT,
			minWidth 	= x,
			maxHeight 	= y,
			maxWidth 	= x + Main.BASE_WIDTH;
		
		for (int h = minHeight/TILE_DIMENSION - 2; h < maxHeight/TILE_DIMENSION + 2; h++) {
			for (int w = minWidth/TILE_DIMENSION - 2; w < maxWidth/TILE_DIMENSION + 2; w++) {
				Vector2f newPos = new Vector2f(w, h);
				// Check to not add duplicata
				if (!this.map.contains(newPos)) {
					this.map.add(newPos);
				}
			}
		}
	}
	
	@Override
	public void keyReleased(int key, char c, GameContainer gc) {
		// Nothing to do
	}

	@Override
	public void keyPressed(int key, char c, GameContainer gc) {
		// Nothing to do
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		// Nothing to do
	}
	
	private Boolean isInScreen(float minScreenX, float maxScreenX, float minScreenY, float maxScreenY, float posMapX, float posMapY) {
		return minScreenX < posMapX && maxScreenX > posMapX 
				&& minScreenY < posMapY && maxScreenY > posMapY;
	}
	
	public Image getBackground() {
		return background;
	}

	public Vector<Vector2f> getMap() {
		return map;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
}
