package game;

/**
 * Interface for different kinds of NPCs
 * @author joshuayang, raymondkim
 *
 */
public interface NPC {
	
	/**
	 * Returns the amount of health the NPC has.
	 * @return The health the NPC has in double form.
	 */
	public double getHealth();
	
	/**
	 * Deal damage to this NPC
	 * @param damage The amount of damage dealt
	 */
	public void dealDamage(double damage);
	
	/**
	 * Is the NPC dead?
	 * @return A boolean, true if the NPC is dead, false otherwise.
	 */
	public boolean isDead();
	
	/**
	 * Getter for the damage that this monster does.
	 * @return The attack damage of this monster
	 */
	public double getDamage();
	
	/**
	 * To kill the NPC.
	 */
	public void killed();
	
	/**
	 * Getter method for the name of this monster.
	 * @return The name.
	 */
	public String getName();
	
	/**
	 * Print NPC dialogue
	 */
	public void engage();
	
	/**
	 * Setter for an NPC's item
	 * @param item The NPc's new item
	 */
	public void setItem(Item item);
	
	/**
	 * Method for dropping NPC loot into the room
	 */
	public void drop();
	
	/**
	 * Setter method for NPCs' locations and also for moving.
	 * @param door The door that it is moving to.
	 */
	public void moveTo(Door door);
	
	/**
	 * Getter method for npcs
	 * @return The room this npc is at
	 */
	public Room getLocation();
	
	/**
	 * Method to make npcs randomly moving 
	 */
	public void randomMove();

}
