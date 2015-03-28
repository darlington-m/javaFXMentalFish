/**
 * Interface for the QuestionProduct Factory
 * 
 * @author Darlington Moyo
 *
 */
interface FactoryIf {

	/**
	 * Creates null objects 
	 * 
	 * @return null 
	 */
	QuestionProduct createProduct();

	/**
	 * Creates QuestionProducts objects depending on the 'discrim' given
	 * 
	 * @param discrim
	 * @param img
	 * @param question
	 * @param answerValue
	 * @return QuestionProduct object
	 * 
	 */
	QuestionProduct createProduct(String discrim, String img,
			String question, int answerValue);

}
