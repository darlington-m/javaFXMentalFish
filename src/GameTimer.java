import java.util.TimerTask;

/**
 * Decrements the gameSecs field in AssignmentTemplate Class by 1 every one
 * second. Used to countdown each game from 90 seconds to 0
 * 
 * @author Darlington Moyo
 *
 */
public class GameTimer extends TimerTask {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		AssignmentTemplate.setGameSecs(AssignmentTemplate.getGameSecs() - 1);
	}
}