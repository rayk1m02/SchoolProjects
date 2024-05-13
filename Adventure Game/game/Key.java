package game;

/**
 * Class for key to unlock specific door
 * @author joshuayang, raymondkim
 *
 */
public class Key implements Item {

	/**
	 * The room that the player must be in to
	 * unlock the locked room.
	 */
	Room access;
	
	/**
	 * The door to be unlocked.
	 */
	Door locked;
	
	/**
	 * The name of this item.
	 */
	String name;
	
	/**
	 * Constructor.
	 */
	public Key(String name, Room access, Door locked) {
		this.name = name;
		this.access = access;
		this.locked = locked;
	}

	/**
	 * Use this key and describes if it works.
	 */
	public void use(Player player) {
		if(player.getCurrentRoom() == access)
		{
			locked.unlock();
			System.out.println("You unlocked the door.");
		} else System.out.println("That key doesn't work here.");		
	
	}


	/**
	 * Returns a String listing the details of the key
	 * @return The string description
	 */
	public String details() {
		return "Name:" + name + "\nkey to unlock a door";
	}

	/**
	 * Returns a String giving the name of the key
	 * @return A string of the name
	 */
	public String getName() {
		return name;
	}

}
