import javax.swing.ImageIcon;

public class Enemy {
	final int TotalHP;
	protected int HP, attack, defence;
	ImageIcon image;
	String name;

	public Enemy(int HP, int attack, int defence) {
		this.TotalHP = HP;
		this.HP = HP;
		this.attack = attack;
		this.defence = defence;
	}

	public String attack(Agents[] animal, int current) {
		int hurt;
		// Attack the valid object
		if (animal[current].HP == 0) {
			for (int index = 0; index < animal.length; index++) {
				if (animal[index].HP != 0) {
					current = index;
					break;
				}
			}
		}
		if (this.attack > animal[current].defence) {
			animal[current].HP -= (attack - animal[current].defence);
			hurt = attack - animal[current].defence;
			if (animal[current].HP < 0) {
				hurt-= 0 - animal[current].HP;
				animal[current].HP = 0;
			}
			return name + " deals " + hurt + " damage to " + animal[current].name + "\n\n";
		} else {
			return name + " failed to penetrate the defense!\n\n";
		}

	}

	public void setName(int sum) {
		this.name += " " + (sum + 1) + " ";
	}
}

class EnemyBegin extends Enemy {
	public EnemyBegin(int process) {
		super(200 + process * 10, 25 + process * 2, 5 + process);
		switch (process) {
		case 0:
			this.name = "Originium Slug";
			this.image = new ImageIcon("Images/Slime_start.png");
			break;
		case 1:
			this.name = "Hound";
			this.image = new ImageIcon("Images/Hound_start.png");
			break;
		case 2:
			this.name = "Soldier";
			this.image = new ImageIcon("Images/Soldier_start.png");
			break;
		}

	}
}

class EnemyLand extends Enemy {
	public EnemyLand(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "Crossbowman";
			this.image = new ImageIcon("Images/Crossbowman_land.png");
			break;
		case 1:
			this.name = "Caster";
			this.image = new ImageIcon("Images/Caster_land.png");
			break;
		case 2:
			this.name = "Senior Caster";
			this.image = new ImageIcon("Images/Senior_Caster_land.png");
			break;
		case 3:
			this.name = "Caster Leader";
			this.image = new ImageIcon("Images/Caster_Leader_land.png");
			break;
		case 4:
			this.name = "Senior Caster Leader";
			this.image = new ImageIcon("Images/Senior_Caster_Leader_land.png");
			break;
		}
	}
}

class EnemySea extends Enemy {
	public EnemySea(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "Bitey";
			this.image = new ImageIcon("Images/Bitey_sea.png");
			break;
		case 1:
			this.name = "Divman";
			this.image = new ImageIcon("Images/Divman_sea.png");
			break;
		case 2:
			this.name = "Divman Leader";
			this.image = new ImageIcon("Images/Divman_leader_sea.png");
			break;
		case 3:
			this.name = "Duck Lord";
			this.image = new ImageIcon("Images/Duck_Lord_sea.png");
			break;
		case 4:
			this.name = "Gopnik";
			this.image = new ImageIcon("Images/Gopnik_sea.png");
			break;

		}
	}
}

class EnemySky extends Enemy {
	public EnemySky(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "Monster";
			this.image = new ImageIcon("Images/Monster_sky.png");
			break;
		case 1:
			this.name = "Monster Mk II";
			this.image = new ImageIcon("Images/Monster2_sky.png");
			break;
		case 2:
			this.name = "Airborne Soldier";
			this.image = new ImageIcon("Images/Airborne_Soldier_sky.png");
			break;
		case 3:
			this.name = "Airborne Soldier Leader";
			this.image = new ImageIcon("Images/Airborne_Soldier_Leader_sky.png");
			break;
		case 4:
			this.name = "Raptor";
			this.image = new ImageIcon("Images/Raptor_sky.png");
			break;

		}
	}
}

class EnemyEnd extends Enemy {
	public EnemyEnd(int process) {
		super(350 + process * 10, 70 + process * 5, 50 + process * 3);
		switch (process) {
		case 0:
			this.name = "Big Bob";
			this.image = new ImageIcon("Images/Big_Bob.png");
			break;
		case 1:
			this.name = "Crownslayer";
			this.image = new ImageIcon("Images/Crownslayer.png");
			break;
		case 2:
			this.name = "Jesselton Williams";
			this.image = new ImageIcon("Images/Jesselton_Williams.png");
			break;
		case 3:
			this.name = "Rat King";
			this.image = new ImageIcon("Images/Rat_King.png");
			break;
		case 4:
			this.name = "FrostNova";
			this.image = new ImageIcon("Images/FrostNova.png");
			break;

		}
	}
}