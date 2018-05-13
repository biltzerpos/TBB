package commands;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



	public class QuestionCommand implements PlayerCommand{
		
		ArrayList<PlayerCommand> questionCommands = new ArrayList<>(); // list to store all commands
		private String textToSay = "";
		private String question= "";
		private String display="";
		private String wrongText="";
		public String a ="";
	//	private GUI gui = new GUI();
	
	public QuestionCommand(String question, String display, String wrongText) {
			this.question = question;
			this.display = display;
			this.wrongText = wrongText;
		}
	
	
		public void addCommand(PlayerCommand q) // add commands in list
		{
			questionCommands.add(q);
		}
		@Override
		public String toString() {  // this will be printed on Left Panel at one index
			return "<html>Question to ask: " + question +
					"<br" + "Pause for sconds: 1"+
					"<br>" + "Display on Brille Cell: " + display + 
					"<br>" + "Wait for user input" + 
					"<br>"+ "On Wrong answer: "+ wrongText;
		}
	
		@Override
		public String serialize() { // this will be stored in file
			for (PlayerCommand pc : questionCommands) {
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
		
}
