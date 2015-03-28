import javafx.scene.image.Image;

/**
 * Implements the Move right command
 * 
 * @author Darlington Moyo
 *
 */
public class MoveRight implements MoveCommandIf {

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

		player.x += player.dx;
		if (player.x > 800) {
			player.x = -50;
		}

		player.r.setX(player.x);

		if (player.imageMoveNum % 1 == 0) {

			player.image = new Image(AssignmentTemplate.class.getResource(
					"resources/head-right" + counter + ".gif").toExternalForm());
			counter++;

			if (counter > 12) {
				this.counter = 1;
			}
		}

	}

}
