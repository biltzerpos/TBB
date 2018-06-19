package commands;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import commands.PlayerCommand;
import org.apache.commons.io.FilenameUtils;
import authoring.GUI;
import authoring.QuestionWindow;



	public class QuestionCommand implements PlayerCommand{
		
		ArrayList<PlayerCommand> questionCommands = new ArrayList<>(); // list to store all commands
		private String textToSay = "";
		private String question="";
		private String display="";
		private String wrongText="";
		private String rightText="";
		private String introAudio= "";
		private String introSound= "";
		private String correctAudio= "";
		private String correctSound= "";
		private String incorrectAudio= "";
		private String incorrectSound= "";
		private String noButton="";
		private int correctButton;
		public String a ="";
	
	//	private GUI gui = new GUI();
	
	public QuestionCommand(String question,String introAudio, String introSound, String display, String wrongText, String incorrectAudio, String incorrectSound,  String rightText, String correctAudio, String correctSound, int correctButton, String button) {
			this.question = question;
			this.display = display;
			this.introAudio= introAudio;
			this.introSound= introSound;
			this.incorrectAudio = incorrectAudio;
			this.incorrectSound= incorrectSound;
			this.correctAudio= correctAudio;
			this.correctSound = correctSound;
			this.wrongText = wrongText;
			this.rightText= rightText;
			this.correctButton= correctButton+1;
			this.noButton= button;
		}
	
	
		public void addCommand(PlayerCommand q) // add commands in list
		{
			questionCommands.add(q);
		}
		@Override
		public String toString() {  // this will be printed on Left Panel at one index

			return 	"<html>"+ "<html><font color=\"blue\">" + "Display on Braille cells: " + "</font>" + "<html><font color=\"red\">"+ display + "</font>"+
					"<br>" + "<html><font color=\"blue\">"+ "Correct button: " + "</font>"+"<html><font color=\"red\">"+  correctButton + "</font>"+
					"<br>"+ "<html><font color=\"green\">"+ "____Question to Ask____" +"</font>"+ 
					"<br>" + "Text: " +"<html><font color=\"red\">"+ question + "</font>"+
					"<br>" + "Recorded Audio: "+ "<html><font color=\"red\">"+  introAudio+ "</font>"+
					"<br>" + "Selected Audio: "+ "<html><font color=\"red\">"+  introSound+ "</font>"+
				//	"<br>" + "Pause for seconds: "+ "<html><font color=\"red\">"+  "1"+ "</font>"+
					//"<br>" + "Wait for user input" + 
					"<br>"+"<html><font color=\"green\">"+  "___On Wrong Answer____" + "</font>"+
					"<br>"+ "Text: " + "<html><font color=\"red\">"+ wrongText + "</font>"+
					"<br>" + "Recorded Audio: "+ "<html><font color=\"red\">"+  incorrectAudio+ "</font>"+
					"<br>" + "Selected Audio: "+ "<html><font color=\"red\">"+  incorrectSound+ "</font>"+
					"<br>"+"<html><font color=\"green\">"+ "___On Right Answer____" + "</font>" +
					"<br>" + "Text: "+ "<html><font color=\"red\">"+  rightText +"</font>"+
					"<br>" + "Recorded Audio: "+ "<html><font color=\"red\">"+  correctAudio+ "</font>"+
					"<br>" + "Selected Audio: "+ "<html><font color=\"red\">"+  correctSound+ "</font>";
		}
	
		@Override
		public String serialize() { // this will be stored in file
			a="";
			for (PlayerCommand pc : questionCommands) {
				
			//	System.out.println(pc.getClass()+"\n");
			a =  a + pc.serialize() + "\n";
			}
			a = a.substring(0, a.length() - 1);
			System.out.println(a);
			return a;
			
		}
	
		@Override
		public String getEditLabel() {
			return "question";
		}
	
		@Override
		public String getCurrentValue() {
			return question;
		}
	
		@Override
		public void setCurrentValue(String textToSay) {
			this.question = textToSay;
		}
		
	
		public void display()
		{
			for (PlayerCommand pc : questionCommands) {
				this.a= pc.serialize();
				this.toString();
			}
		}
		
		@Override
		public void editCommand() {
		QuestionWindow ques= new QuestionWindow(this);
			ques.frame.addWindowListener(new WindowAdapter()
			{

			@Override
		        public void windowClosed(WindowEvent e)
	        	{
	        	
	    		setAll(ques.getIntroField().getText(), ques.getBrailleField().getText(),ques.getCorrectField().getText(), ques.getRepeatField().getText(), ques.getButton().getSelectedIndex());
		        }
		    });
			
			
		}
			
		public String getIntroField()
		{
			return this.question;
		}
		
		public String getBrailleField()
		{
			return this.display;
		}
		
		public String getCorrectField()
		{
			return this.rightText;
		}
		
		public String getRepeatField()
		{
			return this.wrongText;
		}
		
		public int getCorrectButton()
		{
			return this.correctButton;
		}
		
		public String getTotalButtons()
		{
			return this.noButton;
		}
		
		public void setAll(String question, String display, String rightText, String wrongText, int correctButton)
		{
			this.question= question;
			this.display= display;
			this.rightText= rightText;
			this.wrongText=wrongText;
			this.correctButton= correctButton;
		}
}
