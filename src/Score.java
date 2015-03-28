import java.io.Serializable;
import java.util.Date;


/**
 * Creates a score object to be stored in array of scores
 * 
 * @author Darlington Moyo
 *
 */
public class Score implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String playerName;
	private int scoreValue;
	private Date date;
	
	/**
	 * @param playerName
	 * @param scoreValue
	 */
	public Score(String playerName, int scoreValue) {
		this.playerName = playerName;
		this.scoreValue = scoreValue;
		this.date = new Date();
	}

	/**
	 * Returns value of playerName
	 * 
	 * @return playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Changes value of playerName
	 * 
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	
	/**
	 * Returns value of scoreValue
	 * 
	 * @return scoreValue
	 */
	public int getScoreValue() {
		return scoreValue;
	}

	
	/**
	 * Changes the value of scoreValue
	 * 
	 * @param scoreValue
	 */
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	/**
	 * Returns value of date
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	
	/**
	 * Changes the value of date
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
