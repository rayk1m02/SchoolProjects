package game;

/**
 * Class to remove items from inventory
 * @author joshuayang, raymondkim
 *
 */
public class Remove implements Command {

	/**
	 * Name of this command.
	 */
	String name;
	
	/**
	 * The current room
	 */
	private Room room;
	
	/**
	 * The player who does actions
	 */
	private Player player;
	
	/**
	 * Constructor
	 * @param room
	 * @param player
	 */
	public Remove(String name, Room room, Player player) {
		this.name = name;
		this.room = room;
		this.player = player;
	}
	
	@Override
	public void execute(String[] words) {
		String itemName = words[1];
		if (player.getInventory().containsKey(itemName)) {
			System.out.println("You dropped " + itemName + ".");
			room.addItems(itemName, player.getInventory().get(itemName));
			player.removeItem(player.getInventory().get(itemName));
		}
		else System.out.println("You do not have that item!");
	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
