
/**
 * Interface for all moves.
 * 
 * @author Dalington Moyo
 *
 */
public interface MoveCommandIf {
	
	/**
	 * Implemented by each class that implements the MoveCommandIf interface. Method uses 
	 * the current player method to find current coordinates and then replaces the player image
	 * with one pointing to the direction required. Also changes player object's direction.
	 * 
	 * @param player
	 */
	public void execute(Player player);

}
