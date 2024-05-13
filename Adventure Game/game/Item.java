package game;

/**
 * Interface for all items.
 * @author joshuayang, raymondkim
 *
 */
public interface Item {

	/**
	 * Use this item and describes what happens on use.
	 */
	public void use(Player player);
	
	/**
	 * Returns a String listing the details of the Item
	 * @return The string description
	 */
	public String details();
	
	/**
	 * Returns a String giving the name of the Item
	 * @return A string of the name
	 */
	public String getName();

}
