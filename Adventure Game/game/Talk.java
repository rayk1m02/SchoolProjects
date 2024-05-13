package game;

/**
 * Class to talk with an NPC
 * @author joshuayang, raymondkim
 *
 */
public class Talk implements Command {

	/**
	 * Name of this Command.
	 */
	String name;
	
	/**
	 * The current room.
	 */
	private Room room;
	
	/**
	 * The npc you are talking with.
	 */
	private NPC npc;
	
	/**
	 * Constructor
	 */
	public Talk(String name, Room room) {
		this.room = room;
		this.name = name;
	}
	@Override
	public void execute(String[] words) {
		try {
			if (room.getNpcs().containsKey(words[1])) {//if there is this npc to talk to
			npc = room.getNpcs().get(words[1]);
			npc.engage();
			}
			else {
				System.out.println(words[1] + " is not in this room.");

			}
		} catch (Exception e) {
			System.out.println("Choose someone to talk with!");
		}

		//npc.engage();

	}
	
	/**
	 * Method to get the name of a command.
	 */
	public String getName() {
		return name;
	}

}
