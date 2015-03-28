import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class for QuestionProduct object. All methods as inplemented in
 * respective concrete implementations.
 * 
 * @author Darlington Moyo
 *
 */
abstract class QuestionProduct {

	Rectangle r;
	Image image;
	protected int answerValue;
	protected int pointsValue;
	protected String question;

	/**
	 * @param imageFile
	 * @param question
	 * @param pointsValue
	 * @param answerValue
	 */
	protected QuestionProduct(String imageFile, String question, int pointsValue,  int answerValue) {
		
		int x = getRandom(0, 750);
		int y = getRandom(0, 450);
		// TODO Auto-generated constructor stub
		
		image = (new Image(AssignmentTemplate.class.getResource(
				"resources/" + imageFile).toExternalForm()));
		r = new Rectangle(x, y, image.getWidth(), image.getHeight());
		this.question = question;
		this.answerValue = answerValue;
		this.pointsValue = pointsValue;

	}

	/**
	 * Returns value of answerValue 
	 * 
	 * @return
	 */
	public abstract int getAnswerValue();

	/**
	 *Changes value of answerValue
	 * 
	 * @param answerValue
	 */
	public abstract void setAnswerValue(int answerValue);

	/**
	 * Returns value of question
	 * 
	 * @return
	 */
	public abstract String getQuestion();
	
	/**
	 * Changes value of question
	 * 
	 * @param question
	 */
	public abstract void setQuestion(String question);
	
	/**
	 * Returns value of pointsValue
	 * 
	 * @return
	 */
	public abstract int getPointsValue();

	
	/**
	 * Changes value of pointsValue
	 * 
	 * @param pointsValue
	 */
	public abstract void setPointsValue(int pointsValue);
	
	
	/**
	 * Returns integer with a value between high and low values
	 * 
	 * @param low
	 * @param high
	 * @return int between high and low values
	 */
	private int getRandom(int low, int high) {

		Random r = new Random();
		int Low = low;
		int High = high;
		int rand = r.nextInt(High - Low) + Low;
		return rand;
	}
}
