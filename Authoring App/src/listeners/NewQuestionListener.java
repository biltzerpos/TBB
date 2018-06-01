package listeners;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.accessibility.Accessible;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import authoring.ColourMapper;
import authoring.GUI;
import authoring.QuestionWindow;        
import commands.GoHereCommand;
import commands.PauseCommand;
import commands.PlayerCommand;
import commands.ResetButtonCommand;
import commands.SetStringCommand;
import commands.SkipButtonCommand;
import commands.SkipCommand;
import commands.TTSCommand;
import commands.UserInputCommand;
import commands.QuestionCommand;
/**
 * This class is used as an action listener whenever the "New Question" button
 * is clicked. It serves as a way to create question by asking user about
 * introduction text, braille text, repeating text, correct button, text for
 * incorrect.
 *
 * @author Dilshad Khatri, Alvis Koshy, Drew Noel, Jonathan Tung
 * @version 1.0
 * @since 4/3/2017
 *
 */
public class NewQuestionListener extends JPanel implements ActionListener, Accessible{
	private static final long serialVersionUID = 7443038348707836054L;
	private GUI gui;
	QuestionWindow ques;
	
	
/*	private JComboBox<String> buttons;
	private JTextArea introField = new JTextArea(5,18);
	private JTextField brailleField;
	private JTextArea repeatField= new JTextArea(5,18);
	private JTextField buttonField;*/
	private ColourMapper mapper;
	public static Thread threadObject;

	/**
 * Create the NewQuestionListener with a reference to the current GUI
 * object. Access to this object is required in order to access the left
 * panel
 *
 * @param gui
 *            Instance of currently running GUI
 * @param mapper
 *            Reference to the common instance of the colourmapper
 */
public NewQuestionListener(GUI gui, ColourMapper mapper) {
	this.gui = gui;
	this.mapper = mapper;

	this.getAccessibleContext().setAccessibleDescription("");}
	
	/*GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
   
    setLayout(gridBagLayout);

	JLabel introLabel = new JLabel("Introduction :");
	introLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
	GridBagConstraints gbc_introLabel = new GridBagConstraints();
	gbc_introLabel.anchor = GridBagConstraints.WEST;
	gbc_introLabel.insets = new Insets(0, 0, 5, 5);
	gbc_introLabel.gridx = 2;
	gbc_introLabel.gridy = 1;
	add(introLabel, gbc_introLabel);
	
	
	JLabel brailleLabel = new JLabel("Braille Text:");
	brailleLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
	GridBagConstraints gbc_BrailleText = new GridBagConstraints();
	gbc_BrailleText.anchor = GridBagConstraints.WEST;
	gbc_BrailleText.insets = new Insets(0, 0, 0, 5);
	gbc_BrailleText.gridx = 2;
	gbc_BrailleText.gridy = 3;
	add(brailleLabel, gbc_BrailleText);
	
	
	JLabel correctLabel = new JLabel("Correct Button:");
	correctLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
	GridBagConstraints gbc_correctLabel = new GridBagConstraints();
	gbc_correctLabel.anchor = GridBagConstraints.WEST;
	gbc_correctLabel.insets = new Insets(0, 0, 5, 5);
	gbc_correctLabel.gridx = 2;
	gbc_correctLabel.gridy = 5;
	add(correctLabel, gbc_correctLabel);
	
	
	JLabel incorrectLabel = new JLabel("For Incorrect Answer:");
	incorrectLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
	GridBagConstraints gbc_incorrectLabel  = new GridBagConstraints();
	gbc_incorrectLabel.anchor = GridBagConstraints.WEST;
	gbc_incorrectLabel .insets = new Insets(0, 0, 5, 5);
	gbc_incorrectLabel .gridx = 2;
	gbc_incorrectLabel .gridy = 7;
	add(incorrectLabel , gbc_incorrectLabel );
	
	

	JButton text= new JButton("Enter Text");
	text.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
			// Creates a text area that wraps properly and scrolls vertically only
		
			introField.setAutoscrolls(true);
			introField.setWrapStyleWord(true); 
			JScrollPane introPane = new JScrollPane(introField);
			introField.getAccessibleContext().setAccessibleDescription("This is where you ask the question");
			patch(introField);
			
			JOptionPane.showMessageDialog(null, introPane, "Enter introduction text",  
					JOptionPane.PLAIN_MESSAGE);
		
			//Set initial focus to introField
			introField.addAncestorListener(new AncestorListener(){

				@Override
				public void ancestorAdded(AncestorEvent event) {
					// TODO Auto-generated method stub
					introField.requestFocusInWindow();			
				}

				@Override
				public void ancestorMoved(AncestorEvent event) {
					// TODO Auto-generated method stub

				}

				@Override
				public void ancestorRemoved(AncestorEvent event) {
					// TODO Auto-generated method stub

				}

			});
			
			
		}
	});
	
	GridBagConstraints gbc_text = new GridBagConstraints();
	gbc_text.fill = GridBagConstraints.HORIZONTAL;
	gbc_text.insets = new Insets(0, 0, 5, 5);
	gbc_text.gridx = 4;
	gbc_text.gridy = 1;
	add(text, gbc_text);
	
	
	
	JButton record= new JButton("Record Audio");
	record.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	GridBagConstraints gbc_record = new GridBagConstraints();
	gbc_record.anchor = GridBagConstraints.WEST;
	gbc_record.insets = new Insets(0, 0, 5, 5);
	gbc_record.gridx = 5;
	gbc_record.gridy = 1;
	add(record, gbc_record);
	
	
	
	JButton play= new JButton("Play Audio");
	play.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	GridBagConstraints gbc_play = new GridBagConstraints();
	gbc_play.anchor = GridBagConstraints.WEST;
	gbc_play.insets = new Insets(0, 0, 5, 5);
	gbc_play.gridx = 6;
	gbc_play.gridy = 1;
	add(play, gbc_play);
	
	
	
	JButton textIncorrect= new JButton("Enter Text");
	textIncorrect.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			repeatField.setLineWrap(true);
			repeatField.setWrapStyleWord(true);
			repeatField.setAutoscrolls(true);
			JScrollPane repeatPane= new JScrollPane(repeatField);
			repeatField.getAccessibleContext().setAccessibleDescription("This is what is said everytime an incorrect answer is given");
			patch(repeatField);
			
			JOptionPane.showMessageDialog(null,repeatPane, "Enter Text for Incorrect Answer", JOptionPane.PLAIN_MESSAGE);
		}
	});
	GridBagConstraints gbc_textIncorrect = new GridBagConstraints();
	gbc_textIncorrect.fill = GridBagConstraints.HORIZONTAL;
	gbc_textIncorrect.insets = new Insets(0, 0, 5, 5);
	gbc_textIncorrect.gridx = 4;
	gbc_textIncorrect.gridy = 7;
	add(textIncorrect, gbc_textIncorrect);
	
	JButton recordIncorrect= new JButton("Record Audio");
	recordIncorrect.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	GridBagConstraints gbc_recordIncorrect = new GridBagConstraints();
	gbc_recordIncorrect.anchor = GridBagConstraints.WEST;
	gbc_recordIncorrect.insets = new Insets(0, 0, 5, 5);
	gbc_recordIncorrect.gridx = 5;
	gbc_recordIncorrect.gridy = 7;
	add(recordIncorrect, gbc_recordIncorrect);
	
	JButton playIncorrect= new JButton("Play Audio");
	playIncorrect.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	GridBagConstraints gbc_playIncorrect = new GridBagConstraints();
	gbc_playIncorrect.anchor = GridBagConstraints.WEST;
	gbc_playIncorrect.insets = new Insets(0, 0, 5, 5);
	gbc_playIncorrect.gridx = 6;
	gbc_playIncorrect.gridy = 7;
	add(playIncorrect, gbc_playIncorrect);


	brailleField = new JTextField();
	brailleField.getAccessibleContext().setAccessibleDescription("This is what will be displayed on the braille cells");
	GridBagConstraints gbc_brailleField = new GridBagConstraints();
	gbc_brailleField.fill = GridBagConstraints.HORIZONTAL;
	gbc_brailleField.insets = new Insets(0, 0, 5, 5);
	gbc_brailleField.gridx = 4;
	gbc_brailleField.gridy = 3;
	add(brailleField, gbc_brailleField);
	brailleField.setColumns(10);
	
	
	buttonField = new JTextField();
	this.buttons = new JComboBox<String>();
	this.buttons.getAccessibleContext().setAccessibleDescription("This is the correct answer button for this question");
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.gridx = 4;
	gbc_comboBox.gridy = 5;
	add(buttons, gbc_comboBox);
	
	
	brailleField.setMinimumSize(new Dimension(200, 15));
	buttonField.setMinimumSize(new Dimension(200, 15));
	buttons.setMinimumSize(new Dimension(200, 15));

	/*c.insets = new Insets(5, 0, 5, 0);
	 c.gridwidth = GridBagConstraints.REMAINDER;
     c.fill = GridBagConstraints.BOTH;
     
	add(text,gbc);
	gbc.gridx++;

	add(record,gbc);
	gbc.gridx++;
	add(play,gbc);
	
	gbc.gridx--;
	gbc.gridx--;
	*/
//	gbc.gridy++;
//	add(brailleField, gbc);

//	gbc.gridy++;
//	add(buttons, gbc);

	//gbc.gridy++;
	//add(repeatPane, gbc);


	/*gui.addWindowFocusListener(new WindowAdapter() {
	    public void windowGainedFocus(WindowEvent e) {
	        introField.requestFocusInWindow();
	    }
	});*/
//}

/*
public static void patch(Component c) {
	c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
	c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
}
*/
@Override
public void actionPerformed(ActionEvent arg0) {
	gui.logger.log(Level.INFO, "User has clicked New Question button.");
	gui.counterMap.put("New Question", gui.counterMap.get("New Question") + 1);
	// Generate a psuedorandom identifier

	ques= new QuestionWindow("newQues");
	/*try {
		ques.t.wait();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ques.t.notify();*/

 //   ques.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //  ques.pack();
   // ques.setLocationRelativeTo(null);
    //ques.setVisible(true);
    
//	ques.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  //  ques.setVisible(true);
 
//	JDialog dialog= new JDialog(new QuestionWindow(""),"", true);

	ques.frame.addWindowListener(new WindowAdapter()
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            System.out.println("Closed 11");
           // e.getWindow().dispose();
            
        	
        	Random r = new Random();
        	
        	String randomLabel = "" + r.nextInt(500);
        	String strNumOfButtons = gui.getSettingsPanel().getButtonField();
        	if (strNumOfButtons == null || strNumOfButtons.isEmpty()) {
        		return;
        	}

        	int numOfButtons = Integer.parseInt(strNumOfButtons);
        	ques.getButton().removeAllItems();
        	for (int i = 0; i < numOfButtons; i++) {
        		ques.getButton().addItem("Button " + (i + 1));
        	}

        	ArrayList<PlayerCommand> questionCommands = new ArrayList<>();

        	// qc is object of QuestionCommand class
        	QuestionCommand qc= new QuestionCommand(ques.getIntroField().getText(),ques.getBrailleField().getText(),ques.getRepeatField().getText());
        	questionCommands.add(qc);
        	
        	
        	qc.addCommand(new ResetButtonCommand(""));
        	qc.addCommand(new TTSCommand(ques.getIntroField().getText()));
        	qc.addCommand(new PauseCommand("1"));
        	qc.addCommand(new SetStringCommand(ques.getBrailleField().getText()));
//        	qc.addCommand(new GoHereCommand(randomLabel + "-start"));
//        	qc.addCommand(new TTSCommand(repeatField.getText()));

        	// Loop through all the buttons defined

        	PlayerCommand holder;
        for (int i = 0; i < numOfButtons; i++) {
        		if (i != ques.getButton().getSelectedIndex()) {
        			// All buttons that are wrong will just repeat the question
        			// (bad)
        			holder = new SkipButtonCommand("" + i + " " + randomLabel + "-bad");
        			qc.addCommand(holder);
        		} else {
        			// The correct button skips to the end
        			holder = new SkipButtonCommand("" + i + " " + randomLabel + "-good");
        			qc.addCommand(holder);
        		}
        	}

        	// Adds UserInputCommand to wait for button presses
        		qc.addCommand(new UserInputCommand());
        	// Labels for bad
        		qc.addCommand(new GoHereCommand("" + randomLabel + "-bad"));
        		qc.addCommand(new TTSCommand(ques.getRepeatField().getText()));
        		qc.addCommand(new SkipCommand(randomLabel + "-start"));
        	// Label for good
        		holder = new GoHereCommand("" + randomLabel + "-good");
        		qc.addCommand(holder);
        	
        	
        	// Set the colors
        	mapper.addColourMapping(questionCommands);

        		for (PlayerCommand pc : questionCommands) {
        		gui.getLeftPanel().addItem(pc);
        	}
            
        }
    });

		
	}
}




