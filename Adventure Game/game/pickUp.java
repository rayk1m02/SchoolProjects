package game;


/**
 * Class for pick items up
 * @author joshuayang, raymondkim
 *
 */
public class pickUp implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * The player
	 */
	private Player player;
	
	/**
	 * The current room
	 */
	private Room room;

	public pickUp(String name, Room room, Player player) {
		this.room = room;
		this.player = player;
		this.name = name;
	}

	@Override
	public void execute(String[] words) {
		if (room.listItems().isEmpty()) {
			System.out.println("There are no items to pickup in this room.");
			return;
		}
		String itemName = words[1];
		if (room.listItems().containsKey(itemName)) {
			System.out.println("You picked up " + itemName + ".");
			player.addItem(room.getItem(itemName));
			room.removeItem(itemName);
		} else {
			System.out.println("No such item in this room");
			return;
		}
	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
