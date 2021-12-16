import java.io.Serializable;

import javax.swing.ImageIcon;

public class Agents implements Serializable {
	int TotalHP;
	int HP, attack, defence, level = 1, exp = 0;
	ImageIcon image;
	String name;
	Equipment[] equips = new Equipment[5];

	public Agents(int HP, int attack, int defence) {
		TotalHP = HP;
		this.HP = HP;
		this.attack = attack;
		this.defence = defence;
		for (int index = 0; index < equips.length; index++) {
			equips[index] = new Blank();
		}
	}

	// Change equipment
	public Equipment changeEquip(Equipment equip) {
		Equipment oldequip = new Blank();
		if (equip instanceof Earrings) {
			oldequip = equips[0];
			equips[0] = equip;
		} else if (equip instanceof Bracelet) {
			oldequip = equips[1];
			equips[1] = equip;
		} else if (equip instanceof Helmet) {
			oldequip = equips[2];
			equips[2] = equip;
		} else if (equip instanceof Breastplate) {
			oldequip = equips[3];
			equips[3] = equip;
		} else if (equip instanceof Kneecap) {
			oldequip = equips[4];
			equips[4] = equip;
		}
		this.attack += (equip.ATK - oldequip.ATK);
		this.defence += (equip.DEF - oldequip.ATK);

		return oldequip;
	}

	// Endurance down
	public void enduranceDown() {
		for (int index = 0; index < equips.length; index++) {
			if (equips[index] != null) {
				equips[index].endurance--;
				if (equips[index].endurance <= 0) {
					attack -= equips[index].ATK;
					defence -= equips[index].DEF;
					equips[index] = new Blank();
				}
			}
		}
	}

	// Attack
	public String attack(Enemy[] human, int current) {
		int hurt;
		// Attack the valid object
		if (human[current].HP == 0) {
			for (int index = 0; index < human.length; index++) {
				if (human[index].HP != 0) {
					current = index;
					break;
				}
			}
		}
		if (attack > human[current].defence) {
			human[current].HP -= (attack - human[current].defence);
			hurt = attack - human[current].defence;
			if (human[current].HP < 0) {
				hurt -= 0 - human[current].HP;
				human[current].HP = 0;
			}
			return name + " deals " + hurt + " damage to " + human[current].name + "\n";
		} else {
			return name + " failed to penetrate the defense!\n";
		}
	}

	// Properties
	public synchronized String print() {
		synchronized (this) {
			String des = "Name:" + name + "\n\nLevel:" + level + "\n\nExp:" + exp + "/" + (level * 100) + "\n\nHP:" + HP
					+ "/" + TotalHP + "\n\nAttack:" + attack + "\n\nDefence:" + defence + "\n\n";
			for (int index = 0; index < equips.length; index++) {
				if (equips[index] != null && !(equips[index] instanceof Blank))
					des += equips[index].print() + "\n\n";
			}
			return des;
		}
	}

	// level
	public boolean levelUp(int exp) {
		this.exp += exp;
		if (this.exp >= 100 * level) {
			this.exp -= 100 * level;
			level++;
			TotalHP += 10;
			attack += 1;
			defence += 1;
			return true;
		}
		return false;
	}
}

class Land extends Agents {

	public Land(int HP, int attack, int defence) {
		super(HP, attack, defence);

	}

}

class Chen extends Land {
	public Chen() {
		super(300, 25, 12);
		name = "Ch'en";
		image = new ImageIcon("Images/Chen.png");
	}

	public String toString() {
		return "Name:Ch'en\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Amiya extends Land {
	public Amiya() {
		super(300, 28, 10);
		name = "Amiya";
		image = new ImageIcon("Images/Amiya.png");
	}

	public String toString() {
		return "Name:Amiya\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Kal extends Land {
	public Kal() {
		super(350, 23, 13);
		name = "Kal'tsit";
		image = new ImageIcon("Images/Kal.png");
	}

	public String toString() {
		return "Name:Kal'tsit\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Siege extends Land {
	public Siege() {
		super(400, 30, 18);
		name = "Siege";
		image = new ImageIcon("Images/Siege.png");
	}

	public String toString() {
		return "Name:Siege\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class SilverAsh extends Land {
	public SilverAsh() {
		super(350, 35, 18);
		name = "SilverAsh";
		image = new ImageIcon("Images/SilverAsh.png");
	}

	public String toString() {
		return "Name:SilverAsh\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Mountain extends Land {
	public Mountain() {
		super(450, 25, 20);
		name = "Mountain";
		image = new ImageIcon("Images/SilverAsh.png");
	}

	public String toString() {
		return "Name:Mountain\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Nian extends Land {
	public Nian() {
		super(450, 25, 20);
		name = "Nian";
		image = new ImageIcon("Images/Nian.png");
	}

	public String toString() {
		return "Name:Mountain\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}


class Ocean extends Agents {
	public Ocean(int HP, int attack, int defence) {
		super(HP, attack, defence);
	}
}

class Skadi extends Ocean {
	public Skadi() {
		super(400, 36, 20);
		name = "Skadi";
		image = new ImageIcon("Images/Skadi.png");
	}

	public String toString() {
		return "Name:Skadi\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Specter extends Ocean {
	public Specter() {
		super(400, 36, 20);
		name = "Specter";
		image = new ImageIcon("Images/Specter.png");
	}

	public String toString() {
		return "Name:Specter\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Andreana extends Ocean {
	public Andreana() {
		super(400, 36, 20);
		name = "Andreana";
		image = new ImageIcon("Images/Andreana.png");
	}

	public String toString() {
		return "Name:Andreana\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Gladiia extends Ocean {
	public Gladiia() {
		super(400, 36, 20);
		name = "Gladiia";
		image = new ImageIcon("Images/Gladiia.png");
	}

	public String toString() {
		return "Name:Gladiia\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}


class Sky extends Agents {
	public Sky(int HP, int attack, int defence) {
		super(HP, attack, defence);
	}
}

class Schwarz extends Sky {
	public Schwarz() {
		super(400, 32, 17);
		name = "Schwarz";
		image = new ImageIcon("Images/Schwarz.png");
	}

	public String toString() {
		return "Name:Schwarz\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Passenger extends Sky {
	public Passenger() {
		super(400, 32, 17);
		name = "Schwarz";
		image = new ImageIcon("Images/Passenger.png");
	}

	public String toString() {
		return "Name:Passenger\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Platinum extends Sky {
	public Platinum() {
		super(400, 32, 17);
		name = "Platinum";
		image = new ImageIcon("Images/Platinum.png");
	}

	public String toString() {
		return "Name:Platinum\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}

class Exusiai extends Sky {
	public Exusiai() {
		super(400, 32, 17);
		name = "Exusiai";
		image = new ImageIcon("Images/Exusiai.png");
	}

	public String toString() {
		return "Name:Exusiai\nAttack:" + attack + "\tDefence:" + defence + "\n";
	}
}