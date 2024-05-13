package game;

/**
 * Class for door that connects two adjacent rooms
 * @author joshuayang, raymondkim
 *
 */
public class keyDoor implements Door{

	
	/**
	 * The room behind this door.
	 */
	private Room to;
	
	/**
	 * Represent if this door is locked.
	 */
	private boolean locked;
	
	/**
	 * Constructor
	 */
	public keyDoor(Room to, boolean locked) {
		this.to = to;
		this.locked = locked;
	}
	
	/**
	 * To get to the room behind this door.
	 * @return
	 */
	public Room toRoom() {
		return to;
	}

	/**
	 * To see if this door is locked.
	 * @return True if it is locked. False if not.
	 */
	public boolean isLocked() {
		return locked;
	}
	
	  /**
     * Locks the room.
     */
    public void lock() { locked = true; }
    
    /**
     * Unlocks the room.
     */
    public void unlock() { locked = false; }
}
