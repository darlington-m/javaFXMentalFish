import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ScoreLibrary maintains ArrayList for the scores. Allows addition and 
 * deleting of scores.
 * 
 * @author Darlington Moyo
 *
 */
public class ScoreLibrary implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Score> scoreList = new ArrayList<Score>();
	private String libraryName;

	/**
	 * Default constructor for ScoreLibrary Class
	 */
	public ScoreLibrary() {
	}

	/**
	 * Constructor for ScoreLibrary
	 * 
	 * @param name
	 */
	public ScoreLibrary(String name) {
		libraryName = name;
	}

	/**
	 * @return
	 */
	public String getName() {
		return libraryName;
	}

	/**
	 * Add score to library
	 * 
	 * @param score
	 */
	public void addScore(Score score) {
		// List<Score> oldValue = scoreList;
		scoreList = new ArrayList<Score>(scoreList);
		scoreList.add(score);
		// firePropertyChange("books", oldValue, books);
		// firePropertyChange("booksCount", oldValue.size(), books.size());
	}

	/**
	 * Get all scores from library
	 * 
	 * @return scoreList
	 */
	public List<Score> getScores() {
		return scoreList;
	}

	/**
	 * Get Score by selected index
	 * 
	 * @param index
	 * @return score
	 */
	public Score getScore(int index) {

		Score score = scoreList.get(index);
		return score;
	}

	public ArrayList<Score> getScoreList() {
		return (ArrayList<Score>) scoreList;
	}

	/**
	 * Replace scores in library
	 * 
	 * @param newBooks
	 */
	public void replaceScores(List<Score> newScoreList) {
		scoreList = newScoreList;
	}
}
