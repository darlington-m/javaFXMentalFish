import javafx.scene.image.Image;

/**
 * Implements the Move Left Command
 * 
 * @author Darlington Moyo
 *
 */
public class MoveLeft implements MoveCommandIf {

	int counter = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see MoveCommandIf#execute(Player)
	 */
	@Override
	public void execute(Player player) {
		// TODO Auto-generated method stub

		player.x = player.r.getX();
		player.y = player.r.getY();

		player.x -= player.dx;

		if (player.x < -50) {
			player.x = 800;
		}

		player.r.setX(player.x);

		if (player.imageMoveNum % 1 == 0) {

			player.image = new Image(AssignmentTemplate.class.getResource(
					"resources/head-left" + counter + ".gif").toExternalForm());
			counter++;

			if (counter > 12) {
				this.counter = 1;
			}
		}

	}

}
