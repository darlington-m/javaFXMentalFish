import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Creates Bonus objects that are called every now and then to reward good play.
 * Class utilises the Singleton Pattern, meaning only one Bonus object can 
 * exist at a given time.
 * 
 * @author Darlington Moyo
 */
public class Bonus {

	private static Bonus instance = null;
	public Image image;
	public Rectangle r;
	private int pointsValue;

	/**
	 * @param x
	 * @param y
	 */
	private Bonus(int x, int y) {

		image = new Image(AssignmentTemplate.class.getResource(
				"resources/star.png").toExternalForm());
		r = new Rectangle(x, y, image.getWidth(), image.getHeight());
		pointsValue = 500;
	}

	/**
	 * Checks if an instance of Bonus in already initialised before initialising
	 * one. If Instance value is not null, a null object is returned.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Bonus getInstance(int x, int y) {
		if (instance == null) {
			instance = new Bonus(x, y);
		}
		return instance;
	}

	/**
	 * Returns value of points for a Bonus object
	 * 
	 * @return
	 */
	public int getPointsValue() {
		return pointsValue;
	}

	/**
	 * Sets value of points for a Bonus object
	 * 
	 * @param pointsValue
	 */
	public void setPointsValue(int pointsValue) {
		this.pointsValue = pointsValue;
	}
}
