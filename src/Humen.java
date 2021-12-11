import javax.swing.ImageIcon;

public class Humen {
	final int TotalHP;
	protected int HP, attack, defence;
	ImageIcon image;
	String name;

	public Humen(int HP, int attack, int defence) {
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

class HumenZoo extends Humen {
	public HumenZoo(int process) {
		super(200 + process * 10, 25 + process * 2, 5 + process);
		switch (process) {
		case 0:
			this.name = "动物园饲养员";
			this.image = new ImageIcon("Images/ZooFeeders.JPG");
			break;
		case 1:
			this.name = "动物园游客";
			this.image = new ImageIcon("Images/ZooTourists.JPG");
			break;
		case 2:
			this.name = "动物园保安";
			this.image = new ImageIcon("Images/ZooGuards.JPG");
			break;
		}

	}
}

class HumenLand extends Humen {
	public HumenLand(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "偷猎者";
			this.image = new ImageIcon("Images/LandPoacher.JPG");
			break;
		case 1:
			this.name = "狩猎者";
			this.image = new ImageIcon("Images/LandHunters.JPG");
			break;
		case 2:
			this.name = "货车司机";
			this.image = new ImageIcon("Images/LandCar.JPG");
			break;
		case 3:
			this.name = "野味餐馆老板";
			this.image = new ImageIcon("Images/LandRes.JPG");
			break;
		case 4:
			this.name = "工厂安保";
			this.image = new ImageIcon("Images/LandGuards.JPG");
			break;
		}
	}
}

class HumenSea extends Humen {
	public HumenSea(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "渔民";
			this.image = new ImageIcon("Images/SeaFishing.JPG");
			break;
		case 1:
			this.name = "捕鲸船";
			this.image = new ImageIcon("Images/SeaShip.JPG");
			break;
		case 2:
			this.name = "工厂安保";
			this.image = new ImageIcon("Images/SeaGuards.JPG");
			break;
		case 3:
			this.name = "海鲜餐馆员工";
			this.image = new ImageIcon("Images/SeaStaff.JPG");
			break;
		case 4:
			this.name = "海洋声纳";
			this.image = new ImageIcon("Images/SeaSonar.JPG");
			break;

		}
	}
}

class HumenSky extends Humen {
	public HumenSky(int process) {
		super(250 + process * 10, 32 + process * 5, 10 + process * 3);
		switch (process) {
		case 0:
			this.name = "掏鸟窝的熊孩子";
			this.image = new ImageIcon("Images/SkyKids.JPG");
			break;
		case 1:
			this.name = "捕鸟者";
			this.image = new ImageIcon("Images/SkyCatcher.JPG");
			break;
		case 2:
			this.name = "市场小贩";
			this.image = new ImageIcon("Images/SkyKeeper.JPG");
			break;
		case 3:
			this.name = "猎鸟人";
			this.image = new ImageIcon("Images/SkyKiller.JPG");
			break;
		case 4:
			this.name = "飞机";
			this.image = new ImageIcon("Images/SkyPlane.JPG");
			break;

		}
	}
}

class HumenEnd extends Humen {
	public HumenEnd(int process) {
		super(300 + process * 10, 55 + process * 5, 40 + process * 3);
		switch (process) {
		case 0:
			this.name = "谈判人";
			this.image = new ImageIcon("Images/EndSpeaker.JPG");
			break;
		case 1:
			this.name = "反对谈判者";
			this.image = new ImageIcon("Images/EndProtest.JPG");
			break;
		case 2:
			this.name = "人群";
			this.image = new ImageIcon("Images/EndPeople.JPG");
			break;
		case 3:
			this.name = "军队";
			this.image = new ImageIcon("Images/EndArmy.JPG");
			break;
		case 4:
			this.name = "护卫武警";
			this.image = new ImageIcon("Images/EndGuards.JPG");
			break;

		}
	}
}