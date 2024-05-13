package game;

import java.util.HashMap;

/**
 * Game.java
 * 
 * Class to model the game as a collection of rooms. The rooms are organized as
 * a graph, and the Room objects are nodes in the graph.
 *
 * @author Thomas VanDrunen, joshuayang, raymondkim
 */

public class Game {

	/**
	 * The current room the user is in. This serves to purposes-- it is our only
	 * permanent connection to the rooms in this game (the other rooms are reachable
	 * by traversing this room's "doors"-- and it maintains the state by
	 * representing the user's current location.
	 */
	private Room currentRoom;

	/**
	 * Keeps track of whether this game is over or not.
	 */
	private boolean over;

	/**
	 * Keeps track of the player
	 */
	private Player player;

	/**
	 * NPCs in this game
	 */
	private HashMap<String, NPC> npcs;

	/**
	 * Doors in this game
	 */
	private HashMap<String, Door> doors;

	/**
	 * The rooms of this game
	 */
	private Room[] rooms;

	/**
	 * NPCs that move randomly in this game(not in npcs map)
	 */
	private HashMap<String, NPC> roamers;

	/**
	 * Constructor to set up the game.
	 */
	public Game() {
		player = new Player();
		npcs = new HashMap<String, NPC>();
		doors = new HashMap<String, Door>();
		// add more rooms
		rooms = new Room[14];
		roamers = new HashMap<String, NPC>();
//        rooms[0] = new Room("A large cavernous opening stretches in front of you. "
//        		+ "You see a door to the North and to the South and "
//        		+ "a Scroll lies in front of you.");
//        rooms[0].addItems("Instructions", new Scroll("Instructions", "Escape or Survive! Either find the secret"
//        		+ "exit or kill all the monsters in the cave!"));

		rooms[0] = new Room("the bathroom of Mey-Sci in the 3rd floor");
		rooms[1] = new Room("hallway of the 3rd floor");
		rooms[2] = new Room("classroom 310");
		rooms[3] = new Room("classroom 329");
		rooms[4] = new Room("hallway of the 2nd floor");
		rooms[5] = new Room("classroom 201");
		rooms[6] = new Room("classroom 202");
		rooms[7] = new Room("Green house");
		rooms[8] = new Room("CS-lab");
		rooms[9] = new Room("hallway of the 1st floor");
		rooms[10] = new Room("classroom 184");
		rooms[11] = new Room("room of math/cs, you can do something else here");
		rooms[12] = new Room("Mastodon room");
		rooms[13] = new Room("Chamber");
		// initial room0: bathroom

		rooms[0].addDirections("out", new keyDoor(rooms[1], false));
		// room1: hallway of the 3rd floor
		rooms[1].addDirections("back", new keyDoor(rooms[0], false));
		rooms[1].addDirections("left", new teleportDoor(rooms[10], false));
		// teleport you to classroom 184
		rooms[1].addDirections("right", new keyDoor(rooms[3], false));
		rooms[1].addDirections("downstairs", new keyDoor(rooms[4], false));
		// room2: classroom 310
		rooms[2].addDirections("back", new keyDoor(rooms[1], false));// the room you will not get in
		// room3: classroom 329
		rooms[3].addDirections("back", new keyDoor(rooms[1], false));
		// room4: hallway of the 2nd floor
		rooms[4].addDirections("upstairs", new keyDoor(rooms[1], false));
		rooms[4].addDirections("greenhouse", new keyDoor(rooms[7], false));
		rooms[4].addDirections("forward", new keyDoor(rooms[5], false));
		rooms[4].addDirections("downstairs", new keyDoor(rooms[9], false));
		rooms[4].addDirections("right", new keyDoor(rooms[6], false));
		// room5: classroom 201
		rooms[5].addDirections("back", new keyDoor(rooms[4], false));
		Character k = new Character("Dr.Kim", "There are roamers in Mey-Sci" + "\nOne of them will drop a nice weapon.",
				1, rooms[5], this);
		npcs.put(k.getName(), k);
		rooms[5].setNpc(k.getName(), k);
		// room6: classroom 202
		rooms[6].addDirections("back", new keyDoor(rooms[4], false));
		// room7: the green house
		rooms[7].addDirections("out", new keyDoor(rooms[4], false));
		Door door1 = new keyDoor(rooms[8], true);// door from hallway to cslab is locked
		Monster panda = new Monster("panda", 5, 15, rooms[7], this);
		rooms[7].setNpc(panda.getName(), panda);
		panda.setItem(new Key("labkey", rooms[9], door1));// panda drops the key for lab
		npcs.put(panda.getName(), panda);
		// room8:cslab
		rooms[8].addDirections("out", new keyDoor(rooms[9], false));

		// room9: hallway of first floor
		rooms[9].addDirections("cslab", door1);
		rooms[9].addDirections("upstairs", new keyDoor(rooms[4], false));
		rooms[9].addDirections("right", new keyDoor(rooms[10], false));
		rooms[9].addDirections("forward", new keyDoor(rooms[11], false));
		rooms[9].addDirections("mastodonroom", new keyDoor(rooms[12], false));
		// room10: classroom 184
		rooms[10].addDirections("back", new keyDoor(rooms[9], false));

		Character v = new Character("Dr.VanDrunen", "The mastodon has the thing you need."
				+ "\nBe careful, it's very strong! Find the math room or eat to increase your health.", 1, rooms[10], this);

		npcs.put("Dr.VanDrunen", v);
		rooms[10].setNpc(v.getName(), v);
		v.setItem(new Armor("discreteArmor", 20));// nice armor
		// room11: classroom 131
		rooms[11].addDirections("out", new keyDoor(rooms[9], false));
		Character d = new Character("Dr.Diedrichs", "Mastodon is in mastodon room now.", 1, rooms[11], this);
		npcs.put("Dr.Diedrichs", d);
		rooms[11].setNpc(d.getName(), d);
		
		rooms[0].addItems("laptop", new Computer("laptop", "Laptop screen reads:\nYou are trapped here "
				+ "because of a Broken Simulation!!\nFind the key and enter the Lab. Then use the USB!!"));
		
		
		rooms[1].addItems("Apple", new Food("Apple", 1));
		rooms[5].addItems("Mango", new Food("Mango", 2));
		rooms[6].addItems("Orange", new Food("Orange", 3));
		rooms[1].addItems("Sword", new Weapon("Sword", 5));

		Monster m = new Monster("Mastodon", 30, 30, rooms[13], this);
		Item usb = new USB("USB", rooms[8], this);// usb to be used in the lab
		m.setItem(usb);
		npcs.put("Mastodon", m);
		// room12: Mastodon Room, but mastodon is not here at first
		Door door2 = new keyDoor(rooms[9], false);
		doors.put("door2", door2);// add door2 to the hashmap of all doors
		rooms[12].addDirections("out", door2);

		// room13: chamber: where mastodon is at
		rooms[13].setNpc(m.getName(), m);
		Door door3 = new keyDoor(rooms[12], false);
		doors.put("door3", door3);

		rooms[11].setCommand(new Buff("buff", player));

		


		// create roamers
		Roamer roamer1 = new Roamer("roamer1", 3, 12, rooms[1], this);
		Roamer roamer2 = new Roamer("roamer2", 3, 12, rooms[4], this);
		Roamer roamer3 = new Roamer("roamer3", 3, 12, rooms[9], this);
		
		roamer1.setItem(new Food("Banana", 3));
		roamer2.setItem(new Weapon("razerKeyboard", 13));
		// add them to rooms
		rooms[1].setNpc(roamer1.getName(), roamer1);
		rooms[4].setNpc(roamer2.getName(), roamer2);
		rooms[9].setNpc(roamer3.getName(), roamer3);

		roamers.put(roamer1.getName(), roamer1);// first roamer
		roamers.put(roamer2.getName(), roamer2);
		roamers.put(roamer3.getName(), roamer3);
		over = false;
		currentRoom = rooms[0];
	}

	/**
	 * Getter for all the romaers in this game.
	 * 
	 * @return
	 */
	public HashMap<String, NPC> getRoamers() {
		return roamers;
	}

	/**
	 * Getter method for a specific room
	 * 
	 * @param number The number of the room
	 * @return The room corresponding to the number
	 */
	public Room getRoom(int number) {
		return rooms[number];
	}

	/**
	 * Return the room in which the user is currently.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Method for an npc might move to another room through door
	 * 
	 * @param npc  The npc who moves
	 * @param door The door the npc is going through
	 */
	public void npcMove(NPC npc, Door door) {
		npc.moveTo(door);
	}

	/**
	 * Get the door you want
	 * 
	 * @param door
	 * @return The door you want to get to.
	 */
	public Door getDoor(String door) {
		return doors.get(door);
	}

	/**
	 * To get one npc from this game
	 * 
	 * @param npc The name of the npc.
	 * @return The NPC object
	 */
	public NPC getNPC(String npc) {
		return npcs.get(npc);
	}

	/**
	 * Returns the current player.
	 * 
	 * @return The player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Add an item to the player's inventory.
	 * 
	 * @param item The item to be added
	 */
	public void addItem(Item item) {
		player.addItem(item);
	}

	/**
	 * Remove an item from the player's inventory.
	 * 
	 * @param item The item to be removed
	 */
	public void removeItem(Item item) {
		player.removeItem(item);
	}

	/**
	 * Is this game over or not?
	 */
	public boolean isOver() {
		return over;
	}

	/**
	 * Move into a different current room.
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * Indicate that the game is now over.
	 */
	public void finishGame() {
		over = true;
	}
	
	/**
	 * Remove a roamer from this game.
	 * @param roamer
	 */
	public void removeRoamer(NPC roamer) {
		roamers.remove(roamer.getName());
	}

}
