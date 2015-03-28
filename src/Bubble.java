import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Creates bubble objects used on the canvas to give an impression of the sea.
 * 
 * @author user
 *
 */
public class Bubble {

	public Image image;
	public Rectangle r;
	public int animCycle;
	public int counter;

	/**
	 * @param x
	 * @param y
	 * @param counter
	 */
	public Bubble(int x, int y, int counter) {

		image = new Image(AssignmentTemplate.class.getResource(
				"resources/tmp-0.gif").toExternalForm());
		r = new Rectangle(x, y, image.getWidth(), image.getHeight());
		this.counter = counter;
	}

	/**
	 * Changes the bubble image per every 3 animation cycles in the AnimationTimer() in 
	 * AssignmentTemplate class. animCycle value is rest to 0 when it gets to 25. This is done
	 * so that the games loops through all the bubble gif images again and again.
	 * 
	 */
	public void drawBubbles() {

		if (animCycle % 3 == 0) {

			image = new Image(AssignmentTemplate.class.getResource(
					"resources/tmp-" + counter + ".gif").toExternalForm());
			counter++;

			if (counter > 25) {
				this.counter = 0;
			}
		}
	}

}
