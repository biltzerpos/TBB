package authoring;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import listeners.NewQuestionListener;

public class QuestionWindow extends JFrame{

	public JFrame frame= new JFrame();
	private JTextField textField= new JTextField();
	private JComboBox<String> buttons;
	private JTextArea introField = new JTextArea(5,18);
	private JTextField brailleField  = new JTextField();
	private JTextArea repeatField= new JTextArea(5,18);
	private JTextArea correctField= new JTextArea(5,18);
	private JTextField buttonField = new JTextField();
	private JLabel introLabel = new JLabel("Introduction :");
	private JLabel brailleLabel= new JLabel("Braille Text:");
	private JLabel correctLabel= new JLabel("Correct Button:");
	private JLabel correctLabel1= new JLabel("For Correct Answer:");
	private JLabel incorrectLabel= new JLabel("For Incorrect Answer:");
	JButton text= new JButton("Enter Text");
	JButton play= new JButton("Select Audio");
	JButton record= new JButton("Record Audio");
	JButton textIncorrect= new JButton("Enter Text");
	JButton recordIncorrect= new JButton("Record Audio");
	JButton playIncorrect= new JButton("Select Audio");
	JButton textCorrect= new JButton("Enter Text");
	JButton recordCorrect= new JButton("Record Audio");
	JButton playCorrect= new JButton("Select Audio");
	
	public boolean check=false;
	private JButton ok;
	private JButton cancel;
	public int set=0;
	
	public Thread t;
	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public QuestionWindow(String flag){

		t=new Thread();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	public void initialize()  {

		frame.addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             System.out.println("Closed");
	             e.getWindow().dispose();
	         }
	     });
		

		System.out.println("in iniltialize");
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);	
		frame.setPreferredSize(new Dimension(630,280));
		frame.setMaximumSize(new Dimension(630,280));
		frame.setMinimumSize(new Dimension(630,280));
		frame.setLocation(300,300);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	   
		frame.getContentPane().setLayout(gridBagLayout);
		
		/**
		 * Introduction label
		 */
	
		introLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
		GridBagConstraints gbc_introLabel = new GridBagConstraints();
		gbc_introLabel.anchor = GridBagConstraints.WEST;
		gbc_introLabel.insets = new Insets(10, 10, 5, 5);
		gbc_introLabel.gridx = 3;
		gbc_introLabel.gridy = 1;
		frame.getContentPane().add(introLabel, gbc_introLabel);
		
		
		/**
		 * Braille Text label
		 */
		
		brailleLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
		GridBagConstraints gbc_BrailleText = new GridBagConstraints();
		gbc_BrailleText.anchor = GridBagConstraints.WEST;
		gbc_BrailleText.insets = new Insets(10, 10, 0, 5);
		gbc_BrailleText.gridx = 3;
		gbc_BrailleText.gridy = 3;
		frame.getContentPane().add(brailleLabel, gbc_BrailleText);
		
		/**
		 * Correct Button label
		 */
		
	
		correctLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
		GridBagConstraints gbc_correctLabel = new GridBagConstraints();
		gbc_correctLabel.anchor = GridBagConstraints.WEST;
		gbc_correctLabel.insets = new Insets(10, 10, 5, 5);
		gbc_correctLabel.gridx = 3;
		gbc_correctLabel.gridy = 5;
		frame.getContentPane().add(correctLabel, gbc_correctLabel);
		
		/**
		 * Incorrect Button label
		 */
		
		incorrectLabel.setFont(new Font("Sitka Banner", Font.BOLD, 18));
		GridBagConstraints gbc_incorrectLabel  = new GridBagConstraints();
		gbc_incorrectLabel.anchor = GridBagConstraints.WEST;
		gbc_incorrectLabel .insets = new Insets(10, 10, 5, 5);
		gbc_incorrectLabel .gridx = 3;
		gbc_incorrectLabel .gridy = 7;
		frame.getContentPane().add(incorrectLabel , gbc_incorrectLabel );
		
		
		/**
		 * Correct Button label
		 */
		
		correctLabel1.setFont(new Font("Sitka Banner", Font.BOLD, 18));
		GridBagConstraints gbc_correctLabel1  = new GridBagConstraints();
		gbc_correctLabel1.anchor = GridBagConstraints.WEST;
		gbc_correctLabel1 .insets = new Insets(10, 10, 5, 5);
		gbc_correctLabel1 .gridx = 3;
		gbc_correctLabel1 .gridy = 8;
		frame.getContentPane().add(correctLabel1 , gbc_correctLabel1 );
		
		
		/**
		 * Enter Text Button for Introduction
		 */
		
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
		gbc_text.insets = new Insets(10, 10, 5, 5);
		gbc_text.gridx = 4;
		gbc_text.gridy = 1;
		frame.getContentPane().add(text, gbc_text);
		
		
		
		/**
		 * Record Audio Button
		 */
		
		
		record.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_record = new GridBagConstraints();
		gbc_record.anchor = GridBagConstraints.WEST;
		gbc_record.insets = new Insets(10, 10, 5, 5);
		gbc_record.gridx = 5;
		gbc_record.gridy = 1;
		frame.getContentPane().add(record, gbc_record);
		
		/**
		 * Play Audio Button
		 */
		
		
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_play = new GridBagConstraints();
		gbc_play.anchor = GridBagConstraints.WEST;
		gbc_play.insets = new Insets(10, 10, 5, 5);
		gbc_play.gridx = 6;
		gbc_play.gridy = 1;
		frame.getContentPane().add(play, gbc_play);
		

		/**
		 * Enter Text Button for incorrect text
		 */
		
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
		gbc_textIncorrect.insets = new Insets(10, 10, 5, 5);
		gbc_textIncorrect.gridx = 4;
		gbc_textIncorrect.gridy = 7;
		frame.getContentPane().add(textIncorrect, gbc_textIncorrect);
		
		
		/**
		 * Record Audio Button
		 */
		
		
		recordIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_recordIncorrect = new GridBagConstraints();
		gbc_recordIncorrect.anchor = GridBagConstraints.WEST;
		gbc_recordIncorrect.insets = new Insets(10, 10, 5, 5);
		gbc_recordIncorrect.gridx = 5;
		gbc_recordIncorrect.gridy = 7;
		frame.getContentPane().add(recordIncorrect, gbc_recordIncorrect);
		
		
		/**
		 * Play Audio Button
		 */
		
		playIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_playIncorrect = new GridBagConstraints();
		gbc_playIncorrect.anchor = GridBagConstraints.WEST;
		gbc_playIncorrect.insets = new Insets(10, 10, 5, 5);
		gbc_playIncorrect.gridx = 6;
		gbc_playIncorrect.gridy = 7;
		frame.getContentPane().add(playIncorrect, gbc_playIncorrect);


		
		
		/**
		 * Enter Text Button for correct text
		 */
		
		textCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				correctField.setLineWrap(true);
				correctField.setWrapStyleWord(true);
				correctField.setAutoscrolls(true);
				JScrollPane correctPane= new JScrollPane(correctField);
				correctField.getAccessibleContext().setAccessibleDescription("This is what is said everytime an incorrect answer is given");
				patch(correctField);
				
				JOptionPane.showMessageDialog(null,correctPane, "Enter Text for Incorrect Answer", JOptionPane.PLAIN_MESSAGE);
			}
		});
		GridBagConstraints gbc_textCorrect = new GridBagConstraints();
		gbc_textCorrect.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCorrect.insets = new Insets(10, 10, 5, 5);
		gbc_textCorrect.gridx = 4;
		gbc_textCorrect.gridy = 8;
		frame.getContentPane().add(textCorrect, gbc_textCorrect);
		
		
		/**
		 * Record Audio Button
		 */
		
		
		recordCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_recordCorrect = new GridBagConstraints();
		gbc_recordCorrect.anchor = GridBagConstraints.WEST;
		gbc_recordCorrect.insets = new Insets(10, 10, 5, 5);
		gbc_recordCorrect.gridx = 5;
		gbc_recordCorrect.gridy = 8;
		frame.getContentPane().add(recordCorrect, gbc_recordCorrect);
		
		
		/**
		 * Play Audio Button
		 */
	
		playCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_playCorrect = new GridBagConstraints();
		gbc_playCorrect.anchor = GridBagConstraints.WEST;
		gbc_playCorrect.insets = new Insets(10, 10, 5, 5);
		gbc_playCorrect.gridx = 6;
		gbc_playCorrect.gridy = 8;
		frame.getContentPane().add(playCorrect, gbc_playCorrect);
		
	
		
		/**
		 * This is what will be displayed on the braille cells
		 */

		brailleField.getAccessibleContext().setAccessibleDescription("This is what will be displayed on the braille cells");
		GridBagConstraints gbc_brailleField = new GridBagConstraints();
		gbc_brailleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_brailleField.insets = new Insets(0, 10, 5, 5);
		gbc_brailleField.gridx = 4;
		gbc_brailleField.gridy = 3;
		frame.getContentPane().add(brailleField, gbc_brailleField);
		brailleField.setColumns(10);
		
		/**
		 * This is the correct answer button for this question
		 */
		
		buttonField = new JTextField();
		this.buttons = new JComboBox<String>();
		this.buttons.getAccessibleContext().setAccessibleDescription("This is the correct answer button for this question");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 10, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 5;
		frame.getContentPane().add(buttons, gbc_comboBox);
		
		
		brailleField.setMinimumSize(new Dimension(200, 15));
		buttonField.setMinimumSize(new Dimension(200, 15));
		buttons.setMinimumSize(new Dimension(200, 15));


	
	ok = new JButton("OK");
	ok.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			set=1;
			 check=true;
			 System.out.println("in");
			 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			
		}
	});
	GridBagConstraints gbc_ok = new GridBagConstraints();
	gbc_ok.insets = new Insets(10, 10, 5, 5);
	gbc_ok.gridx = 4;
	gbc_ok.gridy = 12;
	frame.getContentPane().add(ok, gbc_ok);
	
	cancel = new JButton("Cancel");
	cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			check=true;
			set=0;
			frame.setVisible(false);
		}
	});
	
	
	GridBagConstraints gbc_cancel = new GridBagConstraints();
	gbc_cancel.insets = new Insets(10, 10, 5, 5);
	gbc_cancel.gridx = 5;
	gbc_cancel.gridy = 12;
	frame.getContentPane().add(cancel, gbc_cancel);
	
}
	
public static void patch(Component c) {
		c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
	}
		

	public JComboBox<String> getButton()
	{
		return buttons;
	}
	
	public JTextField getBrailleField()
	{
		return brailleField;
	}
	
	
	public JTextArea getRepeatField()
	{
		return repeatField;
	}
	
	public JTextArea getIntroField()
	{
		return introField;
	}
	
	public JTextField getButtonField()
	{
		return buttonField;
	}
	
	
	//set intoField
	public void setIntroField(String intro)
	{
		getIntroField().setText(intro);;
	}
	//set repeatField
	public void getRepeatField(String repeat)
	{
		getRepeatField().setText(repeat);;
	}
	//set brailleField
	public void getBrailleField(String braille)
	{
		getBrailleField().setText(braille);;
	}
	//set buttonField
	public void getButtonField(String button)
	{
		getButtonField().setText(button);;
	}
	


	
}
