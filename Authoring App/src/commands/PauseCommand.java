package commands;

import javax.swing.JOptionPane;
import authoring.GUI;
/**
 * A class to represent pause commands. Contains a string for the pause duration
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 2017-04-01
 */
public class PauseCommand implements PlayerCommand {

	private static final String String = null;
	private String waitTime;
	private GUI gui = new GUI();

	/***
	 * Constructor for PauseCommand.
	 *
	 * @param waitTime
	 *            The number of seconds for the pause to wait
	 */
	public PauseCommand(String waitTime) {
		this.waitTime = waitTime;
	}

	@Override
	public String serialize() {
		return "/~pause:" + waitTime;
	}

	@Override
	public String toString() {
		return "Pause for seconds: " + waitTime;
	}

	@Override
	public String getEditLabel() {
		return "Length of time to wait";
	}

	@Override
	public String getCurrentValue() {
		return waitTime;
	}

	@Override
	public void setCurrentValue(String waitTime) {
		this.waitTime = waitTime;
	}
	
	@Override
	public PlayerCommand editCommand(String waitTime, int selectedIndex) {
		Object value;
	    value = JOptionPane.showInputDialog(null, "Select the Port number for server creation", "Server Connection\n", JOptionPane.OK_CANCEL_OPTION, null, null, waitTime);
	    this.waitTime= (String) value;
	    return this;
	 
	}
	
	public String getWaitTime()
	{
		return this.waitTime;
	}
	
	public void setWaitTime(String time)
	{
		this.waitTime = time ;
	}
	
	
}
