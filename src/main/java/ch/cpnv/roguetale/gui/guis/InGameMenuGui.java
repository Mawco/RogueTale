package ch.cpnv.roguetale.gui.guis;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.controller.GuiController;
import ch.cpnv.roguetale.entity.character.Player;
import ch.cpnv.roguetale.font.FontManager;
import ch.cpnv.roguetale.font.FontType;
import ch.cpnv.roguetale.gui.Gui;
import ch.cpnv.roguetale.gui.GuiUtils;
import ch.cpnv.roguetale.gui.button.buttons.OptionButton;
import ch.cpnv.roguetale.gui.button.buttons.ReturnButton;
import ch.cpnv.roguetale.main.Main;
import ch.cpnv.roguetale.weapon.Weapon;

public class InGameMenuGui extends Gui {
	private ArrayList<String> desc;
	private ArrayList<String> desc2;
	
	public InGameMenuGui(Gui prevGui) {
		super(prevGui);
		try {
			this.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init() throws SlickException {
		int w = Main.BASE_WIDTH,
			h = Main.BASE_HEIGHT;

		this.buttonList.add(new OptionButton(w*3/4, h - 80, this));		
		this.buttonList.add(new ReturnButton(w/4, h - 80, this));
	}
	
	public void render(GameContainer gc, Graphics g, Vector2f origin) throws SlickException {
		GuiUtils.renderDefaultBackground(g);
		super.render(gc, g, origin);
		// Display weapons
		Player p = GameGui.getPlayerController().getPlayer();
		Weapon first = p.getPrimaryWeapon();
		Weapon second = p.getSecondaryWeapon();
		FontManager.getInstance().setFont(FontType.Small, g);
		
		if (this.desc == null)
			this.desc = GuiUtils.formatDisplayText(first.getDescription(), Main.BASE_HEIGHT/2, g);
		
		if (this.desc2 == null)
			this.desc2 = GuiUtils.formatDisplayText(second.getDescription(), Main.BASE_HEIGHT/2, g);
		
		this.renderWeapons(gc, g, first, 30, this.desc);
		this.renderWeapons(gc, g, second, Main.BASE_WIDTH/2 + 20, this.desc2);
	}
	
	public void renderWeapons(GameContainer gc, Graphics g, Weapon weapon, int x, ArrayList<String> desc) {
		String name = weapon.getName();
		Image icon = weapon.getIcon().getScaledCopy(64, 64);
		Color fill = new Color(63,169,30);
		Color border = new Color(82,193,26);
		
		GuiUtils.renderBox(
				new Rectangle(x - 10, 30, Main.BASE_WIDTH/2 - 30, Main.BASE_HEIGHT*3/4), 
				fill, 
				border, 
				2, g);
		
		int y = 90;
		int space = 20;
		
		FontManager.getInstance().setFont(FontType.Small, g);
		for (String text : desc) {
			g.drawString(text, x + 100, y);
			y += space;
		}
		FontManager.getInstance().setFont(FontType.Title, g);
		g.drawString(name, x, 40);
		icon.draw(x, 90);
		
		// Caracteristics
		if (y < 90 + 70)
			y = 160;
		y += 30;
		ArrayList<String> carac = weapon.getCaracteristics();
		FontManager.getInstance().setFont(FontType.Small, g);
		
		for (String str : carac) {
			g.drawString(str, x, y); 
			y += 20;
		}
		
		FontManager.getInstance().resetDefaultFont(g);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		super.update(gc, delta, origin);

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		if (Input.KEY_ESCAPE == key) {
			GuiController.getInstance().setDisplayGui(this.prevGui);
		}

		super.keyReleased(key, c, gc);
	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		super.keyPressed(key, c, gc);

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		super.mousePressed(button, x, y);

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		super.mouseMoved(oldx, oldy, newx, newy);
	}

}
