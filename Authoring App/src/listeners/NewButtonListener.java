package listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import authoring.GUI;
import authoring.ThreadRunnable;
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
	ThreadRunnable thread = null;
	File file = null;
	

	/**
	 * Create the NewButtonListener with a reference to the base GUI object
	 * (required to access the left panel)
	 *
	 * @param gui
	 *            Instance of currently running GUI
	 */
	public NewButtonListener(GUI gui) {
		this.gui = gui;
		file = null;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.logger.log(Level.INFO, "User has clicked New Item button.");
		// Show the Add Item dialog
		String[] possibilities = { "Pause", "Text-to-speech", "Display String", "Record Audio", "Repeat", "Button Repeat",
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
				JFileChooser load = new JFileChooser();
				FileNameExtensionFilter wavFileFilter = new FileNameExtensionFilter("wav files (*.wav)", "wav");
				load.addChoosableFileFilter(wavFileFilter);
				load.setFileFilter(wavFileFilter);				
				load.showOpenDialog(null);
				file = load.getSelectedFile();
				if (file != null)
				{
					gui.getLeftPanel().addItem(new SoundCommand(file.toString()));
					gui.counterMap.put("Sound", gui.counterMap.get("Sound") + 1);					
				}
				break;
			case "Record Audio":
				recordAudio();
				if (file != null)
				{
					gui.getLeftPanel().addItem(new SoundCommand(file.toString()));
					gui.counterMap.put("Record Audio", gui.counterMap.get("Record Audio") + 1);
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
				String[] voices = {"1. male","2. female","3. male","4. male"};
				value = JOptionPane.showInputDialog(gui, "Enter a voice number", "Edit Item Details",
					JOptionPane.PLAIN_MESSAGE, null, voices, voices[0]);
				if (value != null && value != "") {
					gui.getLeftPanel().addItem(new SetVoiceCommand(value.toString().substring(0, 1)));
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
	
	private void recordAudio()
	{
		
		
		JDialog recordDialog = new JDialog(gui, "Record Audio");
		recordDialog.setModal(true);
		JPanel panel = new JPanel();
		recordDialog.setSize(200, 160);
		recordDialog.setResizable(false);
		recordDialog.setLocationRelativeTo(gui);
		JLabel label = new JLabel("Press Record button to start recording, Stop button to stop and save, and Cancel button to canel recording");
		JButton recordButton = new JButton("Record");
		recordButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				gui.logger.log(Level.INFO, "Recording Started");
				label.setText("Recording started...");
				thread = new ThreadRunnable();
				thread.start();
				
				
			}
			
			
			
		});
		
		
		
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.logger.log(Level.INFO, "Recording Stopped");
				label.setText("Recording stopped...");
				file = thread.stopRecording();
				recordDialog.setVisible(false);
				
				
				
				
				
				
			}
			
		});
		
		
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gui.logger.log(Level.INFO, "Recording Cancelled");
				label.setText("Recording cancelled...");
				thread.cancel();
				recordDialog.setVisible(false);
				
			}
			
		});
		
		
		
		recordDialog.setLayout(new BorderLayout());
		panel.add(recordButton);
		panel.add(stopButton);
		panel.add(cancelButton);
		recordDialog.add(panel, BorderLayout.CENTER);
		recordDialog.add(label, BorderLayout.SOUTH);
		recordDialog.setVisible(true);
		
		
		
		
		
		
		
		
		
	}
	

}
