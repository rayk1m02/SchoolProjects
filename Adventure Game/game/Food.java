package game;

/**
 * Class for item food
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Food implements Item {

	/**
	 * Name of this food
	 */
	private String name;

	/**
	 * The amount of healing this food does
	 */
	private int healing;

	/**
	 * Simple Constructor
	 * 
	 * @param name    The name of the food
	 * @param healing The amount of healing this food provides
	 */
	public Food(String name, int healing) {
		this.name = name;
		this.healing = healing;
	}

	/**
	 * Use this food and describes what happens on use.
	 */
	public void use(Player player) {
		player.heal(healing);
		player.removeItem(this);// food will be gone after being eaten.
		System.out.println("You ate your " + name + "\n Your health is " + player.getHealth());
	}

	/**
	 * Return the name of this Food
	 * 
	 * @return A string of this Food's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a String with the details of this food: Name and Amount of Healing
	 */
	public String details() {
		return "Name: " + name + "\nHealing: " + healing;
	}

}
