package game;

/**
 * PlayGame.java
 * 
 * Program to control the playing of this game.
 *
 * @author Thomas VanDrunen, joshuayang, raymondkim
 * 
 */

public class PlayGame {
    
    public static void main(String[] args) {
        System.out.println("Welcome to Adventure Time!");
        Game game = new Game();   // reference to the game object
        Parser parser = new Parser();

        while (! game.isOver()) {
            parser.executeTurn(game);
         // roamers will move randomly until the player see them
    		for (NPC it : game.getRoamers().values()) {
    			it.randomMove();
    		}   
        //if player enter a room 11, mastodon will come out
        if (game.getCurrentRoom() == game.getRoom(11) && game.getNPC("Mastodon").getLocation() != game.getRoom(12))
        game.npcMove(game.getNPC("Mastodon"), game.getDoor("door3"));
        
        }		
        System.out.println("Game over.");
    }
    
}
