package ch.cpnv.roguetale.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.entity.projectile.Projectile;

public class ProjectileController implements Controller {
	private final ArrayList<Projectile> projectiles;
	
	public ProjectileController() {
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void render(GameContainer gc, Graphics g, Vector2f origin, Player p) throws SlickException {
		for(Projectile projectile : projectiles) {
			projectile.draw(origin, gc);
		}
	}

	@Override
	public void update(GameContainer gc, int delta, Player p) throws SlickException {
		moveProjectiles(delta);
		removeExpiredProjectiles();
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
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void moveProjectiles(int delta) {
		for(Projectile projectile : projectiles) {
			projectile.move(delta);
		}
	}
	
	private void removeExpiredProjectiles() {
		// The remove method does not work in a "for(Projectile projectile : projectiles)" loop
		// https://stackoverflow.com/questions/3184883/concurrentmodificationexception-for-arraylist
		for(Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = iterator.next();
			if (projectile.isExpired()) {
				iterator.remove();
			}
		}
	}

}
