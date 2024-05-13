package game;

/**
 * Class to set player an enemy
 * @author joshuayang, raymondkim
 *
 */
public class Fight implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * The npc you are fighting with
	 */
	private NPC object;
	
	/**
	 * The player in this fight
	 */
	private Player player;	
	
	/**
	 * Constructor
	 * @param name
	 * @param object
	 * @param player
	 */
	public Fight(String name, Player player) {

		this.player = player;
		this.name = name;
	}
	
	@Override
	public void execute(String[] words) {
		
			if (player.getCurrentRoom().getNpcs().containsKey(words[1])) {//if there is this enemy
				object = player.getCurrentRoom().getNpcs().get(words[1]);
				if (player.getEnemy() != null) {//if player is already fight with someone else
					System.out.println("You are still fighting with another enemy.");
				} else {
					System.out.println("You're in a fight with " + object.getName());
					player.setEnemy(object);
				}
			}

			else {
				System.out.println(words[1] + " is not in this room.");

			}
		

	}
	
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}


}

