package game;

/**
 * Class for item Computer which is like a scroll
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Computer implements Item {

	/**
	 * The name of the computer
	 */
	private String name;

	/**
	 * The writing on the computer
	 */
	private String writing;

	/**
	 * Simple Constructor
	 * 
	 * @param name    The name of this Computer
	 * @param writing The message on this Computer
	 */
	public Computer(String name, String writing) {
		this.name = name;
		this.writing = writing;
	}

	/**
	 * Uses the Computer, which causes the player to read it
	 */
	public void use(Player player) {
		System.out.println(writing);
	}

	/**
	 * Return the name of this Computer
	 * 
	 * @return A string of this Computer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a string giving the details of the computer: its name and the writing
	 * on it
	 */
	public String details() {
		return "Name: " + name + "\nMessage: " + writing;
	}

}
