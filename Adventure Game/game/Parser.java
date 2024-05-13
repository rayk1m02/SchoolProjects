package game;

import java.util.HashMap;

/**
 * Parser.java
 * 
 * Class to interpret the user's commands
 *
 * @author Thomas VanDrunen, joshuayang, raymondkim
 *
 */

import java.util.Scanner;
/**
 * Class to get player's input
 * @author joshuayang raykim
 *
 */
public class Parser {

	/**
	 * For user input from the keyboard.
	 */
	private Scanner keyboard;

	/**
	 * Plain constructor
	 */
	public Parser() {
		keyboard = new Scanner(System.in);
	}

	/**
	 * Let the user make one "turn" at this game. Give the user a description of the
	 * room, prompt for a command, and interpret the command.
	 * 
	 * @param game A reference to the object representing the game.
	 */
	public void executeTurn(Game game) {
		// The room that the user is in.
		Room room = game.getCurrentRoom();

		// every time you enter a room you need to choose your action
		System.out.println("\nYou are in " + room.getDescription());
		System.out.println("What do you wanna do?\n(Enter help to see what you can do)");
		System.out.print("Enter command--> ");
		String command = keyboard.nextLine();// .toLowerCase(); // user's command
		String[] words = command.split(" ");// split command by space
		HashMap<String, Command> cmd = new HashMap<>();

		Command help = new Help("help", cmd, game); // lists available commands
		Command look = new Look("look", room); // lists items, paths and NPC in room
		Command status = new Status("status", game.getPlayer()); // details for current item
		Command pickup = new pickUp("pickup", room, game.getPlayer()); // to pick up and add item to inventory
		Command inventory = new Inventory("inventory", game.getPlayer()); // list player inventory
		Command move = new Directions("move", room, game, game.getPlayer()); // able to move
		Command use = new UseItem("useitem", game.getPlayer());
		Command quit = new quit("quit", game); // quit game
		Command fight = new Fight("fight", game.getPlayer());
		Command talk = new Talk("talk", room);
		Command drop = new Remove("remove", room, game.getPlayer());
		Command strike = new Strike("strike", game, game.getPlayer());
		Command escape = new Escape("escape", game.getPlayer());

		cmd.put("help", help);
		cmd.put("look", look);
		cmd.put("status", status);
		cmd.put("pickup", pickup);
		cmd.put("inventory", inventory); // list of items as well as description of each
		cmd.put("move", move);
		cmd.put("fight", fight);
		cmd.put("quit", quit);
		cmd.put("use", use);
		cmd.put("talk", talk);
		cmd.put("drop", drop);
		cmd.put("strike", strike);
		cmd.put("escape", escape);

		Command unlock = game.getCurrentRoom().getCommand();
		try {
			if (cmd.containsKey(words[0])) {
				cmd.get(words[0]).execute(words);
				// else if the room has command
			} else if (words[0].equals(unlock.getName())) {
				unlock.execute(words);
			} else {// if there's an undefined command
				System.out.println("I do not know how to " + command + ".");
			}
		} catch (Exception e) {// if there's an only one word that is not defined

			System.out.println("I do not know how to " + command + ".");
		}

		
		
		
	}
}
