package ch.cpnv.roguetale.save;

import java.io.IOException;

public class SaveController {
	private SaveGraphic graphic;
	private SaveSound sound;
	private SaveCommand command;
	private SaveProgress progress;
	private SavePurchase purchase;

	public SaveController() {
		this.graphic = new SaveGraphic();
		this.sound = new SaveSound();
	}
	
	public void loadAll() throws ClassNotFoundException, IOException {
		SaveManager sm = new SaveManager();
		sm.loadGraphic();
		sm.loadSound();
		sm.loadCommand();
		sm.loadProgress();
		sm.loadPurchase();
	}
	
	public void saveAll() throws IOException {
		SaveManager sm = new SaveManager();
		sm.saveGraphic();
		sm.saveSound();
		sm.saveCommand();
		sm.saveProgress();
		sm.savePurchase();
	}

	public SaveGraphic getGraphic() {
		return graphic;
	}

	public SaveSound getSound() {
		return sound;
	}

	public void setGraphic(SaveGraphic graphic) {
		this.graphic = graphic;
	}

	public void setSound(SaveSound sound) {
		this.sound = sound;
	}
	
	public SaveCommand getCommand() {
		return command;
	}

	public void setCommand(SaveCommand command) {
		this.command = command;
	}
	
	public SaveProgress getProgress() {
		return progress;
	}

	public void setProgress(SaveProgress progress) {
		this.progress = progress;
	}

	public SavePurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(SavePurchase purchase) {
		this.purchase = new SavePurchase();
		this.purchase.setPurchases(purchase.getPurchases());
	}
}
