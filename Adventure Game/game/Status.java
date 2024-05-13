package game;

/**
 * Class to show a player's damage, defense, and health.
 * @author joshuayang, raymondkim
 *
 */
public class Status implements Command {

	/**
	 * The player.
	 */
	Player player;
	
	/**
	 * Name of this Command.
	 */
	String name;

	public Status(String name, Player player) {
		this.name = name;
		this.player = player;
	}

	public void execute(String[] words) {
		System.out.println("You are holding " + player.getWeapon().getName() + " of damage " + player.getPower());
		System.out.println("You are wearing " + player.getArmor().getName() + " of DamageReduction " + player.getDefense());
		System.out.println("Your heath: " + player.getHealth());
	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
