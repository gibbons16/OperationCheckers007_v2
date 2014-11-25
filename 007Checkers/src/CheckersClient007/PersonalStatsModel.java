package CheckersClient007;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PersonalStatsModel {
	private ArrayList<PersonalStats> views;
	private File statsFile;
	private String numberOfKingsAllowedStr = "Number of Kings Allowed";
	private int numberOfKingsAllowed;
	private String numberOfKingsEarnedStr = "Number of Kings Earned";
	private int numberOfKingsEarned;
	private String numberOfPiecesTakenStr = "Number of pieces taken";
	private int numberOfPiecesTaken;
	private String numberOfPiecesLostStr = "Number of pieces you've lost";
	private int numberOfPiecesLost;
	private String numberOfGamesWonStr = "Number of games won";
	private int numberOfGamesWon;
	private String numberOfGamesLostStr = "Number of games lost";
	private int numberOfGamesLost;
//	private String numberOfForfeitsStr = "Number of times opponent forfeited";
//	private int numberOfForfeits;
//	private String numberOfTimesYouForfeited = "Number of times forfeitted";
//	private int numberOfTimesForefeited;
	private final String STATS_DIR = "Stats";
	public PersonalStatsModel(String userName)
	{
		System.out.println("Username: " + userName);
		views = new ArrayList<>();
		statsFile = new File(STATS_DIR + "/" + userName +".bin");
		if(checkExistingUserStats())
		{
			loadStatsFile();
		}
		else
		{
			initDefaultVals();
		}
	}
	private boolean checkExistingUserStats()
	{
		return statsFile.exists();
	}
	private void initDefaultVals()
	{
		 numberOfKingsAllowed = 0;
		 numberOfKingsEarned = 0;
		 numberOfPiecesTaken = 0;
		 numberOfPiecesLost =0;
		 numberOfGamesWon = 0;
		 numberOfGamesLost = 0;
//		 numberOfForfeits = 0;
//		 numberOfTimesForefeited = 0;
	}
	public int getNumberOfKingsAllowed() {
		return numberOfKingsAllowed;
	}
	public void setNumberOfKingsAllowed(int numberOfKingsTaken) {
		this.numberOfKingsAllowed = numberOfKingsTaken;
		this.updateViews();
	}
	public int getNumberOfKingsEarned() {
		return numberOfKingsEarned;
	}
	public void setNumberOfKingsEarned(int numberOfKingsEarned) {
		this.numberOfKingsEarned = numberOfKingsEarned;
		this.updateViews();
	}
	public int getNumberOfPiecesTaken() {
		return numberOfPiecesTaken;
	}
	public void setNumberOfPiecesTaken(int numberOfPiecesTaken) {
		this.numberOfPiecesTaken = numberOfPiecesTaken;
		this.updateViews();
	}
	public int getNumberOfPiecesLost() {
		return numberOfPiecesLost;
	}
	public void setNumberOfPiecesLost(int numberOfPiecesEarned) {
		this.numberOfPiecesLost = numberOfPiecesEarned;
		this.updateViews();
	}
	public int getNumberOfGamesWon() {
		return numberOfGamesWon;
	}
	public void setNumberOfGamesWon(int numberOfGamesWon) {
		this.numberOfGamesWon = numberOfGamesWon;
		this.updateViews();
	}
	public int getNumberOfGamesLost() {
		return numberOfGamesLost;
	}
	public void setNumberOfGamesLost(int numberOfGamesLost) {
		this.numberOfGamesLost = numberOfGamesLost;
		this.updateViews();
	}
//	public int getNumberOfForfeits() {
//		return numberOfForfeits;
//	}
//	public void setNumberOfForfeits(int numberOfForfeits) {
//		this.numberOfForfeits = numberOfForfeits;
//	}
//	public int getNumberOfTimesForefeited() {
//		return numberOfTimesForefeited;
//	}
//	public void setNumberOfTimesForefeited(int numberOfTimesForefeited) {
//		this.numberOfTimesForefeited = numberOfTimesForefeited;
//	}
	
	public void updateStatsEntries() 
	{
		try {
			FileOutputStream o = new FileOutputStream(statsFile);
			// write out bytes
			o.write((numberOfKingsAllowedStr +": " + numberOfKingsAllowed + "\n").getBytes());
			o.write((numberOfKingsEarnedStr +": " + numberOfKingsEarned + "\n").getBytes());
			o.write((numberOfPiecesTakenStr +": " + numberOfPiecesTaken + "\n").getBytes());
			o.write((numberOfPiecesLostStr +": " + numberOfPiecesLost + "\n").getBytes());
			o.write((numberOfGamesWonStr +": " + numberOfGamesWon + "\n").getBytes());
			o.write((numberOfGamesLostStr +": " + numberOfGamesLost + "\n").getBytes());
//			o.write((numberOfForfeitsStr +": " + numberOfForfeits + "\n").getBytes());
//			o.write((numberOfTimesYouForfeited +": " + numberOfTimesForefeited + "\n").getBytes());
			o.flush();
			o.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadStatsFile()
	{
		FileInputStream fis;
		try {
			fis = new FileInputStream(statsFile);
			
			StringBuilder builder = new StringBuilder();
			int ch;
			while((ch = fis.read()) != -1){
			    builder.append((char)ch);
			}
			
			fis.close();

			String fullString = builder.toString();
			for(String newLine: fullString.split("\n"))
			{
				String[] lineEntries = newLine.split(":");
				String tag = lineEntries[0];
				int val = Integer.parseInt(lineEntries[1].trim());
				
				if(tag.equals(numberOfKingsAllowedStr))
				{
					this.setNumberOfKingsAllowed(val);
				}
				else if(tag.equals(numberOfKingsEarnedStr))
				{
					this.setNumberOfKingsEarned(val);
				}
				else if(tag.equals(numberOfPiecesTakenStr))
				{
					this.setNumberOfPiecesTaken(val);
				}
				else if(tag.equals(numberOfPiecesLostStr))
				{
					this.setNumberOfPiecesLost(val);
				}
				else if(tag.equals(numberOfGamesWonStr))
				{
					this.setNumberOfGamesWon(val);
				}
				else if (tag.equals(numberOfGamesLostStr))
				{
					this.setNumberOfGamesLost(val);
				}
//				else if (tag.equals(numberOfForfeitsStr))
//				{
//					this.setNumberOfForfeits(val);
//				}
//				else if (tag.equals(numberOfTimesYouForfeited))
//				{
//					this.setNumberOfTimesForefeited(val);
//				}	
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateViews();
	}
	public void addView (PersonalStats view)
	{
		this.views.add(view);
	}
	public void updateViews()
	{
		String t = getString();
		for(PersonalStats view : views)
		{
			view.setStats(getString().split("\n"));
		}
	}
	
	
	public String getString() {
		String returnStr = "";
		returnStr = returnStr + numberOfKingsAllowedStr +": " + numberOfKingsAllowed + "\n";
		returnStr = returnStr + numberOfKingsEarnedStr +": " + numberOfKingsEarned + "\n";
		returnStr = returnStr + numberOfPiecesTakenStr +": " + numberOfPiecesTaken + "\n";
		returnStr = returnStr + numberOfPiecesLostStr +": " + numberOfPiecesLost + "\n";
		returnStr = returnStr + numberOfGamesWonStr +": " + numberOfGamesWon + "\n";
		returnStr = returnStr + numberOfGamesLostStr +": " + numberOfGamesLost + "\n";
		return returnStr;
	}
	
}
