package game;

import java.util.HashMap;

/**
 * Class for usage of item.
 * 
 * @author joshuayang, raymondkim
 *
 */
public class UseItem implements Command {

	/**
	 * Name of this Command.
	 */
	String name;

	/**
	 * Player who uses this item
	 */
	private Player player;

	/**
	 * Constructor.
	 * 
	 * @param player
	 */
	public UseItem(String name, Player player) {
		this.name = name;
		this.player = player;
	}

	@Override
	public void execute(String[] words) {
		String item = words[1];
		HashMap<String, Item> inventory = player.getInventory();
		if (inventory.containsKey(item)) {
			inventory.get(item).use(player);
		} else {
			System.out.println("You don't have this item.");
		}

	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
