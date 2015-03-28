/**
 * Factory for QuestionProduct. Initiated different types of QuestionProduct
 * objects depending on the "discrim" provided. Three types of Question Products
 * can be initiated, HardQuestionProduct, NormalQuestionProduct and
 * EasyQuestionProduct.
 * 
 * @author Darlington Moyo
 *
 */
class Factory implements FactoryIf {

	/*
	 * (non-Javadoc)
	 * 
	 * @see FactoryIf#createProduct(java.lang.String, java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public QuestionProduct createProduct(String discrim, String img,
			String question, int answerValue) {
		// TODO Auto-generated method stub
		if (discrim.equals("EasyQuestion")) {
			return (new EasyQuestionProduct(img, question, 20, answerValue));
		} else if (discrim.equals("NormalQuestion")) {
			return (new NormalQuestionProduct(img, question, 35, answerValue));
		} else if (discrim.equals("HardQuestion")) {
			return (new HardQuestionProduct(img, question, 50, answerValue));
		} else {
			return (null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see FactoryIf#createProduct()
	 */
	@Override
	public QuestionProduct createProduct() {
		// TODO Auto-generated method stub
		return (null);
	}

}
