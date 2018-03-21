package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import authoring.GUI;
import commands.CellCharCommand;
import commands.CellLowerCommand;
import commands.CellRaiseCommand;
import commands.ClearAllCommand;
import commands.ClearCellCommand;
import commands.GoHereCommand;
import commands.PauseCommand;
import commands.RepeatButtonCommand;
import commands.RepeatCommand;
import commands.ResetButtonCommand;
import commands.SetPinsCommand;
import commands.SetStringCommand;
import commands.SetVoiceCommand;
import commands.SkipButtonCommand;
import commands.SkipCommand;
import commands.SoundCommand;
import commands.TTSCommand;
import commands.UserInputCommand;

/**
 * This class is used as an action listener whenever the "New Item" button is
 * clicked. It enables the user to set items from dialog box with their value.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 4/3/2017
 *
 */
public class NewButtonListener implements ActionListener {

	private GUI gui;

	/**
	 * Create the NewButtonListener with a reference to the base GUI object
	 * (required to access the left panel)
	 *
	 * @param gui
	 *            Instance of currently running GUI
	 */
	public NewButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.logger.log(Level.INFO, "User has clicked New Item button.");
		// Show the Add Item dialog
		String[] possibilities = { "Pause", "Text-to-speech", "Display String", "Repeat", "Button Repeat",
				"Button Location", "User Input", "Sound", "Reset Buttons", "Go To Location", "Clear All", "Clear Cell",
				"Set Pins", "Set Character", "Raise Pin", "Lower Pin", "Set Voice", "Location Tag" };
		Object value;
		
		String answer;
		answer = (String) JOptionPane.showInputDialog(gui, "Select the type of the item.", "Add Item",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
		if (answer != null) {
			gui.logger.log(Level.INFO, "User has chosen:" + answer + ".");
		}
		processAnswer(answer);

	}

	public void processAnswer(String answer) {
		Object value;
		if (answer != null) {
			switch (answer) {
			case "Pause":
				value = JOptionPane.showInputDialog(gui, "Length of time to wait", "Edit Item Details",
					JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != ""){gui.getLeftPanel().addItem(new PauseCommand((String)value));
				this.gui.counterMap.put("Pause", gui.counterMap.get("Pause") + 1);
				}
				break;
			case "Text-to-speech":
				value = JOptionPane.showInputDialog(gui, "Text to say", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new TTSCommand((String) value));
					gui.counterMap.put("Text-to-speech", gui.counterMap.get("Text-to-speech") + 1);
				}
				break;
			case "Display String":
				value = JOptionPane.showInputDialog(gui, "String to display", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SetStringCommand((String) value));
					gui.counterMap.put("Display String", gui.counterMap.get("Display String") + 1);
				}
				break;
			case "Repeat":
				value = JOptionPane.showInputDialog(gui, "Text to be repeated", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new RepeatCommand((String) value));
					gui.counterMap.put("Repeat", gui.counterMap.get("Repeat") + 1);
				}
				break;
			case "Button Repeat":
				value = JOptionPane.showInputDialog(gui, "Button to use for repeating", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new RepeatButtonCommand((String) value));
					gui.counterMap.put("Button Repeat", gui.counterMap.get("Button Repeat") + 1);
				}
				break;
			case "Button Location":
				value = JOptionPane.showInputDialog(gui, "Button and identifier (space separated)", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SkipButtonCommand((String) value));
					gui.counterMap.put("Button Location", gui.counterMap.get("Button Location") + 1);
				}
				break;
			case "User Input":
				gui.getLeftPanel().addItem(new UserInputCommand());
				gui.counterMap.put("User Input", gui.counterMap.get("User Input") + 1);
				break;
			case "Sound":
				value = JOptionPane.showInputDialog(gui, "File path: ", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SoundCommand((String) value));
					gui.counterMap.put("Sound", gui.counterMap.get("Sound") + 1);
				}
				break;
			case "Reset Buttons":
				gui.getLeftPanel().addItem(new ResetButtonCommand(""));
				gui.counterMap.put("Reset Buttons", gui.counterMap.get("Reset Buttons") + 1);
				break;
			case "Go To Location":
				value = JOptionPane.showInputDialog(gui, "Enter location to go to", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SkipCommand((String) value));
					gui.counterMap.put("Go To Location", gui.counterMap.get("Go To Location") + 1);
				}
				break;
			case "Clear All":
				gui.getLeftPanel().addItem(new ClearAllCommand(""));
				gui.counterMap.put("Clear All", gui.counterMap.get("Clear All") + 1);
				break;
			case "Clear Cell":
				value = JOptionPane.showInputDialog(gui, "Cell number", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new ClearCellCommand((String) value));
					gui.counterMap.put("Clear Cell", gui.counterMap.get("Clear Cell") + 1);
				}
				break;
			case "Set Pins":
				value = JOptionPane.showInputDialog(gui, "Cell and pins (space separated)", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SetPinsCommand((String) value));
					gui.counterMap.put("Set Pins", gui.counterMap.get("Set Pins") + 1);
				}
				break;
			case "Set Character":
				value = JOptionPane.showInputDialog(gui, "Cell and character (space seperated)", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new CellCharCommand((String) value));
					gui.counterMap.put("Set Character", gui.counterMap.get("Set Character") + 1);
				}
				break;
			case "Raise Pin":
				value = JOptionPane.showInputDialog(gui, "Cell and Pin to raise (space separated)", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new CellRaiseCommand((String) value));
					gui.counterMap.put("Raise Pin", gui.counterMap.get("Raise Pin") + 1);
				}
				break;
			case "Lower Pin":
				value = JOptionPane.showInputDialog(gui, "Cell and Pin to lower (space separated)", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new CellLowerCommand((String) value));
					gui.counterMap.put("Lower Pin", gui.counterMap.get("Lower Pin") + 1);
				}
				break;
			case "Set Voice":
				value = JOptionPane.showInputDialog(gui, "Enter a voice number", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SetVoiceCommand((String) value));
					gui.counterMap.put("Set Voice", gui.counterMap.get("Set Voice") + 1);
				}
				break;
			case "Location Tag":
				value = JOptionPane.showInputDialog(gui, "Enter name of location", "Edit Item Details",
						JOptionPane.PLAIN_MESSAGE, null, null, "");
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new GoHereCommand((String) value));
					gui.counterMap.put("Location Tag", gui.counterMap.get("Location Tag") + 1);
				}
				break;
			default:
				break;
			}
		}
	}

}
