package game;

/**
 * Interface for all kinds of doors
 * @author joshuayang, raymondkim
 *
 */
public interface Door {
	/**
	 * To get to the room behind this door.
	 * @return
	 */
	public Room toRoom();

	/**
	 * To see if this door is locked.
	 * @return True if it is locked. False if not.
	 */
	public boolean isLocked();
	
	  /**
     * Locks the room.
     */
    public void lock();
    
    /**
     * Unlocks the room.
     */
    public void unlock();
}
