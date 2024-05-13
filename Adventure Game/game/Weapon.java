package game;

/**
 * Class for weapon item to change the damage of player
 * @author joshuayang, raymondkim
 *
 */
public class Weapon implements Item{

	/** The name of the weapon */
	private String name;
	
	/** Amount of damage the weapon deals */
	private double damage;
	
	
	/** * @author joshuayang
	 * Simple Constructor
	 * @param item Name of the weapon
	 * @param damage Damage of the weapon
	 * @param durability Durability of the weapon
	 */
	public Weapon(String item, double damage) {
		this.name = item;
		this.damage = damage;
	}
	
	/**
	 * Equip the Weapon, which deals damage to a creature.
	 */
	public void use(Player player) {
		player.setWeapon(this);
		System.out.println("You equip " + name);
	}
	
	/**
	 * Return the name of this Weapon
	 * @return A string of this Weapon's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the damage of this Weapon
	 * @return A double of this Weapon's damage
	 */
	public double getDamage() {
		return damage;
	}
	

	
	/**
	 * Returns a string giving the details of the weapon: Name, Amount of Damage, and Current Durability
	 */
	public String details() {
		return "Name: " + name + "\nDamage: " + damage;
	}
	
}
