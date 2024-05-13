package game;

/**
 * The Command interface
 * 
 * @author joshuayang, raymondkim
 *
 */

public interface Command {
	
	public void execute(String[] words);
	
	public String getName();
	
}
