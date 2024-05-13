package game;

/**
 * Class for Escape command to flee from a fight
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Escape implements Command {

	/**
	 * Name of this Command.
	 */
	String name;

	/**
	 * The player who escapes
	 */
	private Player player;

	/**
	 * Constructor.
	 * 
	 * @param player
	 */
	public Escape(String name, Player player) {
		this.player = player;
		this.name = name;
	}

	@Override
	public void execute(String[] words) {
		player.setEnemy(null);
		System.out.println("You succsefully escape from the fight!");
	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
