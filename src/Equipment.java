import java.io.Serializable;
import java.util.Random;
import javax.swing.*;

public class Equipment implements Serializable{
	String name;
	ImageIcon image;
	int ATK, DEF, endurance, cost;

	public Equipment(String name, int ATK, int DEF, int endurance) {
		this.name = name;
		this.ATK = ATK;
		this.DEF = DEF;
		this.endurance = endurance;

	}
	
	// equipment data
	public String print() {
		String des = name + "      \nATK: " + ATK + "      \nDEF: " + DEF + "      \nDQR:" + endurance + "      \nCost: " + cost;
		return des;
	}

	// determine the generated equipment quality by random
	public  Equipment changeQuality() {
		Random generator = new Random();
		int rand = generator.nextInt(100) + 1;

		int level = 0;
		if (rand >= 1 && rand <= 20) {
			cost /= 2;
			name = "Worn-out " + name;
			if (this instanceof AddATK)
				ATK /= 2;
			else if (this instanceof AddDEF)
				DEF /= 2;
		} else {
			if (rand >= 21 && rand <= 50) {
				name = "Normal " + name;
				level = 1;
			} else if (rand >= 51 && rand <= 75) {
				name = "First-class " + name;
	      		level = 2;
			} else if (rand >= 76 && rand <= 90) {
				name = "Excellent " + name;
				level = 3;
			} else if (rand >= 91 && rand <= 96) {
    			name = "Artisan " + name;
				level = 4;
			} else if (rand >= 97 && rand <= 99) {
				name = "Supreme " + name;
				level = 5;
			} else if (rand >= 100 && rand <= 100) {
				name = "Legendary " + name;
				level = 6;
			}
			cost *= level;
			if (this instanceof AddATK)
				ATK += 3 * level;
			else if (this instanceof AddDEF)
				DEF += 3 * level;
		}
		return this;
	}
   
}

class Blank extends Equipment {
	public Blank() {
		super("", 0, 0, 9999);
	}
}

class AddATK extends Equipment {
	public AddATK(String name, int ATK, int endurance) {
		super(name, ATK, 0, endurance);

	}
}

class Earrings extends AddATK {
	public Earrings() {
		super("Earrings", 5, 100);
		this.cost = 200;
	}
}

class Bracelet extends AddATK {
	public Bracelet() {
		super("Bracelet", 8, 100);
		this.cost = 300;
	}
}

class AddDEF extends Equipment {
	public AddDEF(String name, int DEF, int endurance) {
		super(name, 0, DEF, endurance);
	}
}

class Helmet extends AddDEF {
	public Helmet() {
		super("Helmet", 5, 100);
		this.cost = 200;
	}
}

class Breastplate extends AddDEF {
	public Breastplate() {
		super("Breastplate", 8, 100);
		this.cost = 300;
	}
}

class Kneecap extends AddDEF {
	public Kneecap() {
		super("Kneecap", 3, 100);
		this.cost = 100;
	}
}
