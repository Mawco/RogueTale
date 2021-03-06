package ch.cpnv.roguetale.save;

import java.io.Serializable;
import java.util.ArrayList;

import ch.cpnv.roguetale.save.enums.PurchaseType;
import ch.cpnv.roguetale.save.other.Purchase;

public class SavePurchase implements Serializable {
	private static final long serialVersionUID = 5700901588841937807L;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

	public SavePurchase() {
		this.setDefaultData();
	}
	
	public void setDefaultData() {
		this.purchases.add(new Purchase(PurchaseType.healthplus, "+ 1 coeur de base", 0, 5, 500, 1.5f));
		this.purchases.add(new Purchase(PurchaseType.bonusSpeedPerLevel, "+ 1% de vitesse tous les niveaux", 0, 5, 1000, 2f));
		this.purchases.add(new Purchase(PurchaseType.dashCooldownReduction, "- 10% du temps de recharge du dash", 0, 5, 500, 1.5f));
		this.purchases.add(new Purchase(PurchaseType.HUDEnemyLevel, "Permet de voir le niveau des ennemis", 0, 1, 1000, 1f));
		this.purchases.add(new Purchase(PurchaseType.HUDEnemyLife, "Permet de voir la vie des ennemis", 0, 1, 1500, 1f));
		this.purchases.add(new Purchase(PurchaseType.SellWeapon, "Permet de vendre les armes ramassées pour 50 pièces", 0, 5, 1500, 2f));
		this.purchases.add(new Purchase(PurchaseType.MidasTouch, "Gagne 10 pièces tous les coeurs supplémentaires ramassés", 0, 5, 2000, 2f));
	}
	
	public void setPurchases(ArrayList<Purchase> list) {
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		for (Purchase p : this.purchases) {
			boolean found = false;
			
			for (Purchase p2 : list) {
				if (p.getName().name().equalsIgnoreCase(p2.getName().name())) {
					found = true;
					purchases.add(p2);
				}
			}
			
			if (!found) {
				purchases.add(p);
			}
		}
		
		this.purchases = purchases;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}
	
	public Purchase getPurchase(PurchaseType name) {
		for (Purchase p : this.purchases) {
			
			if (p.getName().name().equalsIgnoreCase(name.name())) {
				return p;
			}
		}
		
		return null;
	}
}
