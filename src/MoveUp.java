import javafx.scene.image.Image;

/**
 * Implements the Move Up command
 * 
 * @author user
 *
 */
public class MoveUp implements MoveCommandIf {

	int counter = 1;

	/* (non-Javadoc)
	 * @see MoveCommandIf#execute(Player)
	 */
	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub

		player.x = player.r.getX();
		player.y = player.r.getY();

		player.y -= player.dy;

		if (player.y < -50) {
			player.y = 500;
		}

		player.r.setY(player.y);

		if (player.imageMoveNum % 1 == 0) {

			player.image = new Image(AssignmentTemplate.class.getResource(
					"resources/head-up" + counter + ".gif").toExternalForm());
			counter++;

			if (counter > 12) {
				this.counter = 1;
			}
		}

	}

}
