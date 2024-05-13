package game;

/**
 * The class of command "buff"
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Buff implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	/**
	 * The player to buff
	 */
	private Player player;

	/**
	 * Constructor
	 * 
	 * @param player
	 */
	public Buff(String name, Player player) {
		this.player = player;
		this.name = name;
	}

	@Override
	public void execute(String[] words) {
		if (player.getHealth() < 20) {
			System.out.println("You got buffed from Math Department");
			player.heal(10);
			System.out.println("You health: " + player.getHealth());
		} else {
			System.out.println("You have enough health. You cannot be buffed anymore!");
		}
	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
