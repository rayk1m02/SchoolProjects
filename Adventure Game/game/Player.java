package game;

import java.util.HashMap;

/**
 * Player.java
 * 
 * Class to simulate the player. Contains important information about the
 * player, such as health, damage reduction, and inventory.
 *
 * @author joshuayang, raymondkim 
 */
public class Player {

	/**
	 * The current room the player is in.
	 */
	private Room currentRoom;

	/** The health of the player */
	private double health;

	/**
	 * The weapon the player equip
	 */
	private Weapon weapon;

	/**
	 * The armor the player equip
	 */
	private Armor armor;
	
	/**
	 * The enemy this player is fighting with.
	 */
	private NPC enemy;

	/** The inventory of the player */
	private HashMap<String, Item> inventory;

	/**
	 * Constructor.
	 */
	public Player() {
		health = 10.0;
		inventory = new HashMap<>();
		weapon = new Weapon("knife", 3);//default weapon
		armor = new Armor("shirt", 1);//default armor
		enemy = null;
	}
	
	/**
	 * Setter method for this player's enemy
	 * @param enemy
	 */
	public void setEnemy(NPC enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * Getter enemy for this player's enemy.
	 * @return
	 */
	public NPC getEnemy() {
		return enemy;
	}

	/**
	 * Returns the current health of the player.
	 * 
	 * @return The current health of a player
	 */
	public double getHealth() {
		return health;
	}


	/**
	 * Returns the current inventory of the player.
	 * 
	 * @return the inventory of the player
	 */
	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	/**
	 * Adds an item to the player's inventory.
	 * 
	 * @param item The item to be added
	 */
	public void addItem(Item item) {
		inventory.put(item.getName(), item);
	}

	/**
	 * Removes an item from the player's inventory.
	 * 
	 * @param item The item to be removed
	 */
	public void removeItem(Item item) {
		inventory.remove(item.getName(), item);
	}

	/**
	 * Heals the player.
	 * 
	 * @param health The amount to be healed
	 */
	public void heal(double health) {
		this.health += health;
	}

	/**
	 * Damages the player.
	 * 
	 * @param damage The amount to be damaged
	 */
	public void takeDamage(double damage) {
		this.health -= damage;
	}

	/**
	 * Get player's attack power
	 * 
	 * @return The player's attack power.
	 */
	public double getPower() {
		return weapon.getDamage();
	}

	/**
	 * Place the previous weapon back in the inventory and equip a new one.
	 * 
	 * @param weapon The weapon to equip.
	 */
	public void setWeapon(Weapon weapon) {
		addItem(this.weapon);
		this.weapon = weapon;
		this.removeItem(weapon);//when you equip an weapon it is not in your inventory
	}

	/**
	 * Gets the weapon currently equipped by the player.
	 * 
	 * @return The player's weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Get player's attack power
	 * 
	 * @return The player's attack power.
	 */
	public double getDefense() {
		return armor.getReduction();
	}

	/**
	 * Place the previous armor back in the inventory and equip a new one.
	 * 
	 * @param armor The armor to equip.
	 */
	public void setArmor(Armor armor) {
		addItem(this.armor);
		this.armor = armor;
		this.removeItem(armor);//when you equip an armor it is not in your inventory
	}

	/**
	 * Gets the armor currently equipped by the player.
	 * 
	 * @return The player's weapon
	 */
	public Armor getArmor() {
		return armor;
	}

	/**
	 * Move into a different current room.
	 * 
	 * @param room The Room to move to.
	 */
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}

	/**
	 * Return the room in which the user is currently.
	 * 
	 * @return The Room the player is in.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

}
