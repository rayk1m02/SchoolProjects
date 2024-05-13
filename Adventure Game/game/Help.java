package game;

import java.util.HashMap;
/**
 * Class to list all the available commands.
 * @author joshuayang, raymondkim
 *
 */
public class Help implements Command {
	
	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * All the valid commands
	 */
	private HashMap<String, Command> cmd;
	
	/**
	 * The game.
	 */
	private Game game;
	
	public Help(String name, HashMap<String, Command> cmd, Game game) {
		this.cmd = cmd;
		this.game = game;
		this.name = name;
	}
	
	public void execute(String[] words) {
		System.out.println("Here are the list of available commands:");
		for (String it : cmd.keySet()) {
			System.out.println(it);
		}
		if (game.getCurrentRoom().getCommand() != null) {//if the room has extra command
			System.out.println(game.getCurrentRoom().getCommand().getName());
		}
		//get reference of the game and then the room
		
	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
