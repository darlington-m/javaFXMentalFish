import java.util.TimerTask;

/**
 * Decrements seconds of every move. Player give 8 seconds per move and class
 * counts down from 8 to 0 and resets each time.
 * 
 * @author Darlington Moyo
 *
 */
public class MoveTimer extends TimerTask {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		if (AssignmentTemplate.getSecs() > 0) {
			AssignmentTemplate.setSecs(AssignmentTemplate.getSecs() - 1);
		} else if (AssignmentTemplate.getSecs() == 0) {
			AssignmentTemplate.setSecs(8);
		}
	}

}
