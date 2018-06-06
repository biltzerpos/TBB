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
		private String question= "";
		private String display="";
		private String wrongText="";
		private String rightText="";
		private int correctButton;
		public String a ="";
		public static final String ANSI_PURPLE = "\u001B[35m";
	//	private GUI gui = new GUI();
	
	public QuestionCommand(String question, String display, String wrongText, String rightText, int correctButton) {
			this.question = question;
			this.display = display;
			this.wrongText = wrongText;
			this.rightText= rightText;
			this.correctButton= correctButton+1;
		}
	
	
		public void addCommand(PlayerCommand q) // add commands in list
		{
			questionCommands.add(q);
		}
		@Override
		public String toString() {  // this will be printed on Left Panel at one index
			return "<html>Question to ask: " +"<html><font color=\"red\">"+ question + "</font>"+
					"<br>" + "Pause for seconds: "+ "<html><font color=\"red\">"+  "1"+ "</font>"+
					"<br>" + "Display on Braille cells: " +"<html><font color=\"red\">"+ display + "</font>"+
					"<br>" + "Correct button: " +"<html><font color=\"red\">"+  correctButton + "</font>"+
					"<br>" + "Wait for user input" + 
					"<br>"+ "On Wrong answer: "+"<html><font color=\"red\">"+ wrongText + "</font>"+
					"<br>" + "On Right answer: "+ "<html><font color=\"red\">"+  rightText +"</font>"; 
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
			this.question = question;
		}
		
	
		public void display()
		{
			for (PlayerCommand pc : questionCommands) {
				this.a= pc.serialize();
				this.toString();
			}
		}
		
		@Override
		public void editCommand(String waitTime , int seletedIndex) {
//		///	QuestionWindow ques= new QuestionWindow(waitTime);
//			ques.frame.addWindowListener(new WindowAdapter()
//		    {
//		        @Override
//		        public void windowClosing(WindowEvent e)
//		        {
//		        	
//		        	
//		        }
//		    });
			
			
		}
			
}
