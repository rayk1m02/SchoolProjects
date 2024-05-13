package game;

/**
 * Class for item USB to win the game
 * @author joshuayang, raymondkim
 *
 */
public class USB implements Item {

	/**
	 * Name of this usb
	 */
	private String name;
	
	/**
	 * Room to use this usb
	 */
	private Room room;
	
	/**
	 * This game
	 */
	private Game game;
	
	/**
	 * Constructor
	 * @param name
	 * @param room
	 */
	public USB(String name, Room room, Game game) {
		this.name = name;
		this.room = room;
		this.game = game;
	}
	
	/**
	 * Use this item and describes what happens on use.
	 */
	@Override
	public void use(Player player) {
		if (room == player.getCurrentRoom()) {
		System.out.println("Successfully used the USB to the central PC of Mey-Sci. You win.");
		game.finishGame();
		} else {
			System.out.println("You cannot use it here. Find the lab.");
		}
	}

	/**
	 * Returns a String listing the details of the Item
	 * @return The string description
	 */
	@Override
	public String details() {		
		return "Name:" + name + "\na good looking USB";
	}

	/**
	 * Returns a String giving the name of the Item
	 * @return A string of the name
	 */
	@Override
	public String getName() {
		return name;
	}

}
