package game;


/**
 * Class for choose next direction
 * 
 * @author joshuayang, raymondkim
 *
 */
public class Directions implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * The current room
	 */
	private Room room;

	/**
	 * The Game that has all the rooms
	 */
	private Game game;

	/**
	 * The player of this game.
	 */
	private Player player;

	/**
	 * Constructor.
	 * @param room
	 * @param game
	 * @param player
	 */
	public Directions(String name, Room room, Game game, Player player) {
		this.name = name;
		this.room = room;
		this.game = game;
		this.player = player;
	}

	@Override
	public void execute(String[] words) {

		Room nextroom;
		Door connect;
		if (player.getEnemy() != null) {
			System.out.println("You can't move to another room while fight!");
			return;
		}
		try {
			if (room.listDirections().containsKey(words[1])) {

				connect = room.getDirections(words[1]);
				nextroom = connect.toRoom();

				if (!connect.isLocked()) {
					game.setCurrentRoom(nextroom);
					player.setCurrentRoom(nextroom);// keep track of room in player class
				} else {
					System.out.println("This room is locked.");
				}
			}

			else {
				System.out.println("No door in that direction.");

			}
		} catch (Exception e) {
			System.out.println("You need to move to a direction.");
		}

	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}


}
