package game;

import java.util.HashMap;

/**
 * Class for checking items in this room
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Look implements Command {

	/**
	 * Name of this Command.
	 */
	String name;

	/**
	 * The items in the room.
	 */
	private HashMap<String, Item> Items = new HashMap<>();

	/**
	 * The items in the room.
	 */
	private HashMap<String, Door> Directions = new HashMap<>();

	/**
	 * The room.
	 */
	private Room room;


	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param room
	 */
	public Look(String name, Room room) {
		this.room = room;
		this.Items = room.listItems();
		this.Directions = room.listDirections();
		this.name = name;
	}

	public void execute(String[] words) {
		System.out.println("Items in this room are:");
		for (String it : Items.keySet()) {
			System.out.println(it);
		}
		System.out.println("\nDirections available are:");
		for (String it : Directions.keySet()) {
			System.out.println(it);
		}
		System.out.println("\nNPC in this room:");
		for (String it : room.getNpcs().keySet())
			System.out.println(it);

		System.out.println("\nYou took a look. The roamers are moving...\nIf you see them, they can't move!");
		
	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
