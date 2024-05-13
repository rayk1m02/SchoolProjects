package game;

/**
 * Class for Item Armor.
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Armor implements Item {

	/** The name of the armor */
	private String name;

	/** Amount of damage the armor reduces */
	private int damageReduction;

	/**
	 * Simple Constructor
	 * 
	 * @param item            Name of the armor
	 * @param damageReduction Damage reduction of the armor
	 * @param durability      Durability of the armor
	 */
	public Armor(String item, int damageReduction) {
		this.name = item;
		this.damageReduction = damageReduction;

	}

	/**
	 * Equip this item and describes what happens on use.
	 */
	public void use(Player player) {
		player.setArmor(this);
		System.out.println("You equip " + name);
	}

	/**
	 * Getter method for getting the damage reduction.
	 * 
	 * @return
	 */
	public double getReduction() {
		return damageReduction;
	}

	/**
	 * Return the name of this Armor
	 * 
	 * @return A string of this Armor's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a string giving the details of the armor: Name, Amount of Damage
	 * Reduction, and Current Durability
	 */
	public String details() {
		return "Name: " + name + "\nDamage Reduction: " + damageReduction;
	}

}
