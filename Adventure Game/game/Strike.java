package game;

/**
 * Class for striking the enemy
 * @author joshuayang, raymondkim
 *
 */
public class Strike implements Command {

	/**
	 * Name of this Command.
	 */
	String name;

	/**
	 * The player who strikes.
	 */
	private Player player;

	/**
	 * The npc who is strikes.
	 */
	private NPC object;

	/**
	 * The game.
	 */
	private Game game;

	
	/**
	 * Constructor
	 * 
	 * @param game
	 * @param player
	 * @param object
	 */
	public Strike(String name, Game game, Player player) {
		this.name = name;
		this.game = game;
		this.player = player;
		object = player.getEnemy();
	}

	@Override
	public void execute(String[] words) {
		if (player.getEnemy() == null)
			System.out.println("You are hitting the air.");
		else {
			System.out.println("You strike " + object.getName() + "!");
			double dmg = player.getPower();
			object.dealDamage(dmg);
			System.out.println("You hurt " + object.getName() + " " + dmg + " health.");
			System.out.println(object.getName() + "'s health is " + object.getHealth());
			if (object.getHealth() <= 0) {
				player.setEnemy(null);
				object.killed();//object will be removed after being dead.
				return;
			}
			double dmg2 = object.getDamage() - player.getDefense();// damage from monster after reduction
			if (dmg2 < 0) dmg2 = 0;//the damage can't be negative
			System.out.println(object.getName() + " hurts you " + dmg2);
			player.takeDamage(dmg2);
			System.out.println("Your health is " + player.getHealth());
			if (player.getHealth() <= 0) {
				System.out.println("You died");
				this.game.finishGame();
			}

		}

	}

	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
