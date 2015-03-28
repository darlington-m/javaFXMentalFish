/**
 * Concrete implementation of NormalQuestionProduct. Implements All the abstract
 * methods in QuestionProduct.
 * 
 * @author Darlington Moyo
 *
 */
public class NormalQuestionProduct extends QuestionProduct {

	protected NormalQuestionProduct(String imageFile, String question,
			int pointsValue, int answerValue) {
		super(imageFile, question, pointsValue, answerValue);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#getAnswerValue()
	 */
	@Override
	public int getAnswerValue() {
		// TODO Auto-generated method stub
		return answerValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#setAnswerValue(int)
	 */
	@Override
	public void setAnswerValue(int answerValue) {
		// TODO Auto-generated method stub
		this.answerValue = answerValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#getQuestion()
	 */
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return question;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#setQuestion(java.lang.String)
	 */
	@Override
	public void setQuestion(String question) {
		// TODO Auto-generated method stub
		this.question = question;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#getPointsValue()
	 */
	@Override
	public int getPointsValue() {
		// TODO Auto-generated method stub
		return pointsValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see QuestionProduct#setPointsValue(int)
	 */
	@Override
	public void setPointsValue(int pointsValue) {
		// TODO Auto-generated method stub
		this.pointsValue = pointsValue;
	}

}
