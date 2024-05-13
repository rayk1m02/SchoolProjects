package game;

import java.util.HashMap;

/**
 * Class for listing what items are picked (for project)
 *
 * @author joshuayang, raymondkim
 *
 */
public class Inventory implements Command {
	
	/**
	 * Name of this Command.
	 */
	String name;

	/**
	 * The player.
	 */
	private Player player;
	
	/**
	 * The inventory of this player.
	 */
	private HashMap<String, Item> inventory;
	
	public Inventory(String name, Player player) {
		this.player = player;
		this.name = name;
	}
	
	public void execute(String[] words) {
		inventory = player.getInventory();
		System.out.println("You currently have:\n");
		for (Item it: inventory.values()) {
			System.out.print(it.details() + "\n");
			System.out.println();
		}
	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

	
}
