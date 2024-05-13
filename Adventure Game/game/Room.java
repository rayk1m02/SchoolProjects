package game;

import java.util.HashMap;

/**
 * Room.java
 * 
 * Class to model a room in the game.
 *
 * @author joshuayang, raymondkim
 *         
 */

public class Room {

	/**
	 * A collection of items.
	 */
	private HashMap<String, Item> items;
	
	/**
	 * A collection of NPCs.
	 */
	private HashMap<String, NPC> npcs;
	
	/**
	 * A description of this room
	 */
	private String description;

	/**
	 * The map to store the rooms and the direction in pairs
	 */
	
	private HashMap<String, Door> directions;
	
	/**
	 * The command in this room.
	 */
	private Command cmd;

	/**
	 * Constructor.
	 * 
	 * @param description A String describing this room to the user.
	 */
	public Room(String description) {
		this.description = description;
		directions = new HashMap<String, Door>();// create all directions
		items = new HashMap<String, Item>();
		npcs = new HashMap<String, NPC>();
		cmd = null;
	}
	
	/**
	 * Setter method for command of this room
	 * @param cmd The command to add.
	 */
	public void setCommand(Command cmd) {
		this.cmd = cmd;
	}
	
	/**
	 * Getter method for command of this room
	 * @return The commend of this room.
	 */
	public Command getCommand() {
		return cmd;
	}

	/**
	 * Add a direction and a room
	 * @param direction
	 * @param room
	 */
	public void addDirections(String direction, Door door) {
		directions.put(direction, door);
	}

	/**
	 * Choose on direction and get the room of this direction
	 * 
	 * @param key The direction
	 * @return The room of this direction
	 */
	public Door getDirections(String key) {
		return directions.get(key);
	}
	
	/**
	 * Getter method for the hashmap of directions
	 * @return The hashmap of directions.
	 */
	public HashMap<String, Door> getMap(){
		return directions;
	}

	/**
	 * Add items to this room and change the description.
	 * @param name
	 * @param item
	 */
	public void addItems(String name,Item item) {
		this.description += " with "  + name;
		items.put(name, item);
	}
	
	/**
	 * Get the NPCs in the room.
	 * @return HashMap of NPCs
	 */
	public HashMap<String, NPC> getNpcs() {
		return npcs;
	}
	
	/**
	 * The method to set NPC for this room.
	 * @param npc
	 */
	public void setNpc(String name, NPC npc) {
		npcs.put(name, npc);
	}
	
	/**
	 * Remove npc from this room
	 * @param npc The npc to remove
	 */
	public void removeNpc(NPC npc) {
		npcs.remove(npc.getName());
	}
	/**
	 * Remove items from this room and change description.
	 * @param name
	 * @param item
	 */
	public void removeItem(String name) {
		items.remove(name);
		this.description = this.description.replace(" with " + name, "");
	}
	
	/**
	 * Get access to a certain item
	 * @param name The name of the item
	 * @return An item
	 */
	public Item getItem(String name) {
		return items.get(name);
	}
	
	/**
	 * Get the description of the current room
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Get access of all items
	 * @return The hash map that contains all items
	 */
	public HashMap<String, Item> listItems() {
		return items;
	}
	
	/**
	 * Get all directions that the room has
	 * @return The hashmap that contains all diretions.
	 */
	public HashMap<String, Door> listDirections() {
		return directions;
	}


}
