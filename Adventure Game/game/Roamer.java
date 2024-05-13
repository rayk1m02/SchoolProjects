package game;

/**
 * Class for roamers who are monsters moving around the world randomly
 * @author joshuayang, raymondkim
 *
 */
public class Roamer implements NPC {

	/**
	 * The damage that this monster does
	 */
	private double damage;

	/**
	 * The health that this monster has
	 */
	private double health;

	/**
	 * If this monster is dead.
	 */
	private boolean dead;

	/**
	 * The name of this monster.
	 */
	private String name;

	/**
	 * The loot that the monster holds.
	 */
	private Item loot;

	/**
	 * The room this monster is at.
	 */
	private Room location;

	/**
	 * This game
	 */
	private Game game;

	/**
	 * Constructor
	 * 
	 * @param d The damage The damage assigned to the roamer
	 * @param h The health assigned to the roamer
	 */
	public Roamer(String n, double d, double h, Room room, Game game) {
		name = n;
		damage = d;
		health = h;
		dead = false;
		location = room;
		this.game = game;
	}

	/**
	 * Getter method for the name of this roamer.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the damage that this romaer does.
	 * 
	 * @return The attack damage of this romaer
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * Returns the amount of health the NPC has.
	 * 
	 * @return The health the NPC has in double form.
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * To kill the roamer.
	 */
	public void killed() {
		dead = true;
		drop();
		location.removeNpc(this);
		game.removeRoamer(this);//if the roamer is dead, remove it from game   	
		System.out.println("You win! The enemy died.");
	}

	/**
	 * Is the NPC dead?
	 * 
	 * @return A boolean, true if the NPC is dead, false otherwise.
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Deal damage to this roamer
	 * 
	 * @param damage The amount of damage dealt
	 */
	public void dealDamage(double damage) {
		health -= damage;

	}

	/**
	 * Print roamer's dialogue
	 */
	public void engage() {
		System.out.println(
				this.getName() + " says: Awwwwwwwww...Java! Awwwwww!" + "\n It seems that it cannot talk...");
	}

	/**
	 * Setter for an NPC's item
	 * 
	 * @param item The NPc's new item
	 */
	public void setItem(Item item) {
		this.loot = item;
	}

	/**
	 * Method for dropping NPC loot into the room
	 */
	public void drop() {
		if (loot != null) {
			location.addItems(loot.getName(), loot);
			System.out.println(loot.getName() + " fell to the floor.");
			this.loot = null;
			
		}
	}

	/**
	 * Setter method for NPCs' locations and also for moving.
	 * 
	 * @param door The door that it is moving to.
	 */
	public void moveTo(Door door) {
		if (!door.isLocked()) {// if the door is not locked
			location.removeNpc(this);// remove this npc from the current room
			location = door.toRoom();// move
			location.setNpc(name, this);
		}
	}

	/**
	 * Getter method for npcs
	 * 
	 * @return The room this npc is at
	 */
	public Room getLocation() {
		return location;
	}



	/**
	 * Method to make npcs randomly moving
	 */
	public void randomMove() {
		if (location != game.getCurrentRoom()) {// if a roamer is seen by player, it cannot move
			int i = 0;
			Door doors[] = new Door[location.getMap().size()];// a list all available doors
			for (Door d : location.getMap().values()) {
				doors[i] = d;// moveTo method would help to tell if the door is locked
				i++;
			}
			int random = (int) (Math.random() * doors.length);
			this.moveTo(doors[random]);// randomly enter a door
		}
	}
}
