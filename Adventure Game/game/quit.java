package game;

/**
 * Class to quit the game
 * @author joshuayang, raymondkim
 *
 */
public class quit implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * The game.
	 */
	private Game game;
	
	/**
	 * Constructor
	 * @param game
	 */
	public quit(String name, Game game) {
		this.game = game;
		this.name = name;
	}
	
	@Override
	public void execute(String[] words) {
		game.finishGame();
	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
